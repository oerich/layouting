package de.te.layouting.layouting.forcebased.forces;

import java.awt.Dimension;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import de.te.layouting.geometry.Point;
import de.te.layouting.layouting.forcebased.model.DefaultConnection;
import de.te.layouting.layouting.forcebased.model.DefaultNode;
import de.te.layouting.layouting.forcebased.model.PhysicalNode;
import de.te.layouting.util.IConnection;

public class ForceBasedSceneFactory<T> {

	private Dimension dimension;
	private PhysicalNode[] nodes;
	private IConnection<T>[] connections;

	/**
	 * Sets the Size of the scene
	 * 
	 * @param d
	 */
	public void setDimension(Dimension d) {
		this.dimension = d;
	}

	public Dimension getDimension() {
		return this.dimension;
	}

	public PhysicalNode[] getVertexes() {
		return this.nodes;
	}

	public IConnection<T>[] getConnections() {
		return this.connections;
	}

	@SuppressWarnings("unchecked")
	public void createRandomScene(double conProb, int n) {
		Random r = new Random();
		this.nodes = new PhysicalNode[n];
		for (int i = 0; i < this.nodes.length; i++) {
			// if (r.nextDouble() > 0.75)
			// this.nodes[i] = new PhysicalBorderVertex();
			// else
			this.nodes[i] = new PhysicalNode(new DefaultNode());
			this.nodes[i].setPosition(new Point(
					r.nextInt(this.dimension.width), r
							.nextInt(this.dimension.height)));
			// if (r.nextDouble()>0.75)
			// this.vertexes[i].setIsOutsider(true);
		}

		List<IConnection<T>> cons = new Vector<IConnection<T>>();
		for (int i = 0; i < this.nodes.length; i++) {
			for (int j = i + 1; j < this.nodes.length; j++) {
				if (r.nextDouble() < conProb) {
					DefaultConnection<T> c = new DefaultConnection<T>();
					c.setFrom(this.nodes[i]);
					c.setTo(this.nodes[j]);
					cons.add(c);
				}
			}
		}

		this.connections = cons.toArray(new IConnection[0]);
	}

	public void addConnection(T from, T to) {

	}

}
