package de.te.layouting.layouting.forcebased.forces;

import java.awt.Dimension;

import de.te.layouting.geometry.Bounds;
import de.te.layouting.geometry.IBounds;
import de.te.layouting.geometry.IPoint;
import de.te.layouting.geometry.Point;
import de.te.layouting.layouting.forcebased.IForce;
import de.te.layouting.layouting.forcebased.model.PhysicalNode;
import de.te.layouting.util.IConnection;

public class BorderRepulsionForce implements IForce {

	private CoulumbNodeRepulsionForce delegate = new CoulumbNodeRepulsionForce();
	private HookeConnectionForce delegate2 = new HookeConnectionForce();
	private IBounds dimension;

	public BorderRepulsionForce(Dimension dim, double offset) {
		this(new Bounds(0 - offset, 0 - offset, dim.width + offset, dim.height
				+ offset));
	}

	public BorderRepulsionForce(IBounds dimension2) {
		this.dimension = dimension2;
	}

	public void setBounds(IBounds bounds) {
		this.dimension = bounds;
	}

	public IBounds getBounds() {
		return this.dimension;
	}

	@Override
	public void computeForce(PhysicalNode[] nodes, IConnection<?>[] connections) {
		for (PhysicalNode v : nodes) {
			computeBoundaryRepulsion(v);
		}
	}

	private void computeBoundaryRepulsion(PhysicalNode v) {
		// Compute the forces from the Boundaries:
		IPoint left = new Point(this.dimension.getX(), v.getPosition().getY());
		IPoint right = new Point(this.dimension.getX()
				+ this.dimension.getWidth(), v.getPosition().getY());
		IPoint top = new Point(v.getPosition().getX(), this.dimension.getY());
		IPoint bottom = new Point(v.getPosition().getX(), this.dimension.getY()
				+ this.dimension.getHeight());

		// We want to draw in points from the outside.
		if (v.getPosition().getX() < this.dimension.getX()
				|| v.getPosition().getX() > (this.dimension.getX() + this.dimension
						.getWidth())) {
			v.addImpulse(delegate2.computeAttraction(v.getPosition(), left));
			v.addImpulse(delegate2.computeAttraction(v.getPosition(), right));
		} else {
			v.addImpulse(delegate.computeRepulsion(left, v.getPosition()));
			v.addImpulse(delegate.computeRepulsion(right, v.getPosition()));
		}
		if (v.getPosition().getY() < this.dimension.getY()
				|| v.getPosition().getY() > (this.dimension.getY() + this.dimension
						.getHeight())) {
			v.addImpulse(delegate2.computeAttraction(v.getPosition(), top));
			v.addImpulse(delegate2.computeAttraction(v.getPosition(), bottom));
		} else {
			v.addImpulse(delegate.computeRepulsion(top, v.getPosition()));
			v.addImpulse(delegate.computeRepulsion(bottom, v.getPosition()));
		}
	}

	@Override
	public double getPrefferedDistance() {
		return delegate.getPrefferedDistance();
	}

	@Override
	public void setPrefferedDistance(double prefferedDistance) {
		this.delegate.setPrefferedDistance(prefferedDistance);
		this.delegate2.setPrefferedDistance(prefferedDistance);
	}

}
