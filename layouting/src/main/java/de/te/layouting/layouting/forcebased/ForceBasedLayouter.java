package de.te.layouting.layouting.forcebased;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import de.te.layouting.geometry.Bounds;
import de.te.layouting.geometry.IBounds;
import de.te.layouting.geometry.IPoint;
import de.te.layouting.geometry.Point;
import de.te.layouting.layouting.HorizontallyOutsideAreaConstraint;
import de.te.layouting.layouting.IGeometricalLayoutConstraint;
import de.te.layouting.layouting.IGraphLayouter;
import de.te.layouting.layouting.IGraphProvider;
import de.te.layouting.layouting.InsideAreaConstraint;
import de.te.layouting.layouting.forcebased.model.DefaultConnection;
import de.te.layouting.layouting.forcebased.model.PhysicalNode;
import de.te.layouting.util.IConnection;

public class ForceBasedLayouter<GraphType, ConnectionType, NodeType> implements
		IGraphLayouter<GraphType, ConnectionType, NodeType> {

	// The graph:
	private InsideAreaConstraint bounds = new InsideAreaConstraint();
	private double prefferedDistance = 200;
	private Map<NodeType, PhysicalNode> nodeMap = new HashMap<NodeType, PhysicalNode>();
	private Map<PhysicalNode, IGeometricalLayoutConstraint[]> constraintMap = new HashMap<PhysicalNode, IGeometricalLayoutConstraint[]>();
	private List<IConnection<ConnectionType>> connections = new Vector<IConnection<ConnectionType>>();

	// Configuration:
	private List<IForce> forces = new Vector<IForce>();
	private double damping = 0.4;
	private IGraphProvider<GraphType, ConnectionType, NodeType> graphProvider;
	private GraphType graph;

	@Override
	public void initialize(GraphType graph,
			IGraphProvider<GraphType, ConnectionType, NodeType> graphProvider,
			IBounds bounds, int prefferedDistance) {
		this.nodeMap.clear();
		this.connections.clear();

		this.graphProvider = graphProvider;
		this.graph = graph;
		this.bounds.place(bounds);
		this.prefferedDistance = prefferedDistance;

		Random r = new Random();

		for (NodeType n : graphProvider.getNodes(graph)) {
			PhysicalNode v = this.nodeMap.get(n);
			if (v == null) {
				v = new PhysicalNode();
				v.setPosition(new Point(
						r.nextInt((int) this.bounds.getWidth()), r
								.nextInt((int) this.bounds.getHeight())));
				this.nodeMap.put(n, v);
				IGeometricalLayoutConstraint[] layoutConstraints = graphProvider
						.getLayoutConstraints(graph, n);
				this.constraintMap.put(v, layoutConstraints);

				// debugging:
				if (layoutConstraints.length == 1
						&& layoutConstraints[0] instanceof HorizontallyOutsideAreaConstraint) {
					v.setIsOutsider(true);
				}
			}
		}

		for (NodeType n : this.nodeMap.keySet()) {
			for (ConnectionType c : graphProvider.getSuccessors(graph, n)) {
				DefaultConnection<ConnectionType> con = new DefaultConnection<ConnectionType>();
				con.setFrom(this.nodeMap.get(n));
				con.setTo(this.nodeMap.get(graphProvider.getConnectionTarget(
						graph, c)));
				this.connections.add(con);
			}
		}

	}

	@Override
	public boolean layout(int steps) {
		int i = 0;
		computeForces();
		if (steps == IGraphLayouter.INFINITY)
			steps = Integer.MAX_VALUE;
		// System.out.println(steps);
		while (i < steps && getTotalCineticEnergy() > 0.01) {
			computeForces();
			setPositions();
			i++;
		}
		for (NodeType n : this.nodeMap.keySet()) {
			IPoint position = this.nodeMap.get(n).getPosition();
			this.graphProvider.placeNode(this.graph, n,
					new Bounds(position.getX(), position.getY(), 0, 0));
		}

		// System.out.println("Layout after " + i + " steps; remaining energy: "
		// + getTotalCineticEnergy()
		// + "(lower is better, < 0.01 is good).");
		return getTotalCineticEnergy() < 0.01;
	}

	public void computeForces() {
		for (IForce f : this.forces) {
			if (this.prefferedDistance > 0)
				f.setPrefferedDistance(this.prefferedDistance);
			f.computeForce(this.nodeMap.values().toArray(new PhysicalNode[0]),
					this.connections.toArray(new IConnection<?>[0]));
		}
	}

	public double getTotalCineticEnergy() {
		double ret = 0;
		for (PhysicalNode v : this.nodeMap.values())
			ret += v.getCineticEnergy();
		return ret;
	}

	public void setPositions() {
		for (PhysicalNode v : this.nodeMap.values()) {
			v.addToPosition(v.getImpulse());

			applyConstraint(v, this.bounds);

			for (IGeometricalLayoutConstraint constraint : constraintMap.get(v)) {
				applyConstraint(v, constraint);
			}

		}
		damping();
	}

	private void applyConstraint(PhysicalNode v,
			IGeometricalLayoutConstraint constraint) {
		IPoint position = v.getPosition();
		IBounds.BOUNDS.place(position.getX(), position.getY());

		if (!constraint.isLegal(IBounds.BOUNDS)) {
			// System.err.print("Constraint: " + constraint
			// + " violated. Moving from (" + IBounds.BOUNDS.getX() + ","
			// + IBounds.BOUNDS.getY() + ")");
			constraint.getLegalBounds(IBounds.BOUNDS);
			// System.err.println(" to (" + IBounds.BOUNDS.getX() + ","
			// + IBounds.BOUNDS.getY() + ")");
			// Reset the forces
			v.setImpulse(new de.te.layouting.geometry.Vector());
			// set the constraint location:
			Random r = new Random();
			position.setX(r.nextInt((int) this.bounds.getWidth()));
			position.setY(r.nextInt((int) this.bounds.getHeight()));
			v.setPosition(position);

			// position.setX(IBounds.BOUNDS.getX());
			// position.setY(IBounds.BOUNDS.getY());
		}
	}

	private void damping() {
		for (PhysicalNode v : this.nodeMap.values())
			v.scaleImpulse(this.damping);
	}

	/**
	 * <p>
	 * Specifies the damping of the impulses, i.e. the constant that is used to
	 * scale the movement vectors of each node.
	 * </p>
	 * <p>
	 * A high value helps to overcome some local maxima in graph layout but can
	 * lead to a system where the powers do not decrease. A value of 1 will
	 * almost always lead to a break down.
	 * </p>
	 * <p>
	 * A low value will increase the reliability and decrease the runtime but
	 * might lead to sub-optimal results.
	 * </p>
	 * <p>
	 * Defaults to 0.4.
	 * </p>
	 * 
	 * @param d
	 *            should be between 0 - 1
	 */
	public void setDamping(double d) {
		this.damping = d;
	}

	/**
	 * Specifies the distance where the forces should be equal to 1. Defaults to
	 * 200. Will override the settings of the forces, if larger than 0.
	 * 
	 * @param prefferedDistance
	 */
	public void setPrefferedDistance(double prefferedDistance) {
		this.prefferedDistance = prefferedDistance;
	}

	public double getPrefferedDistance() {
		return prefferedDistance;
	}

	/**
	 * Adds some forces to the layouting. Without forces, the layouter will do
	 * nothing.
	 * 
	 * @param f
	 * @see BorderRepulsionForce, CoulumbNodeRepulsionForce,
	 *      HookeConnectionForce
	 */
	public void addForce(IForce f) {
		this.forces.add(f);
	}

	public void removeForce(IForce f) {
		this.forces.remove(f);
	}

	public IConnection<?>[] getConnections() {
		return this.connections.toArray(new IConnection<?>[0]);
	}

	public PhysicalNode[] getNodes() {
		return this.nodeMap.values().toArray(new PhysicalNode[0]);
	}
}
