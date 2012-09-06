package de.te.layouting.layouting.forcebased.forces;

import de.te.layouting.geometry.IPoint;
import de.te.layouting.geometry.Vector;
import de.te.layouting.layouting.forcebased.IForce;
import de.te.layouting.layouting.forcebased.model.PhysicalNode;
import de.te.layouting.util.IConnection;
import de.te.layouting.util.INode;

public class HookeConnectionForce implements IForce {

	private double prefferedDistance;

	@Override
	public void computeForce(PhysicalNode[] nodes, IConnection<?>[] connections) {
		for (IConnection<?> c : connections) {
			INode from = c.getFrom();
			INode to = c.getTo();
			Vector vect = computeAttraction(from.getPosition(), to.getPosition());

			((PhysicalNode) c.getFrom()).addImpulse(vect);
			((PhysicalNode) c.getTo()).addImpulse(vect.scale(-1));
		}
	}

	Vector computeAttraction(IPoint from, IPoint to) {
		Vector vect = new Vector(from, to);
		double d = vect.getLength();

		// TODO scale by stiffness (or: spring-constant) of connection
		double f = d / this.prefferedDistance;

		vect = vect.getUnitVector().scale(f);
		return vect;
	}

	@Override
	public double getPrefferedDistance() {
		return prefferedDistance;
	}

	@Override
	public void setPrefferedDistance(double prefferedDistance) {
		this.prefferedDistance = prefferedDistance;
	}

}
