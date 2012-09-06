package de.te.layouting.layouting.forcebased.forces;

import de.te.layouting.geometry.IPoint;
import de.te.layouting.geometry.Vector;
import de.te.layouting.layouting.forcebased.IForce;
import de.te.layouting.layouting.forcebased.model.PhysicalNode;
import de.te.layouting.util.IConnection;

public class CoulumbNodeRepulsionForce implements IForce {

	private double prefferedDistance;

	@Override
	public void computeForce(PhysicalNode[] nodes, IConnection<?>[] connections) {
		for (int i = 0; i < nodes.length; i++) {
			PhysicalNode v1 = nodes[i];

			for (int j = i + 1; j < nodes.length; j++) {
				PhysicalNode v2 = nodes[j];
				Vector impulse = computeRepulsion(v1.getPosition(),
						v2.getPosition());
				v2.addImpulse(impulse);
				v1.addImpulse(impulse.scale(-1));
			}
		}
	}

	Vector computeRepulsion(IPoint p1, IPoint p2) {
		Vector vect = new Vector(p1, p2);
		double d = vect.getLength();

		double f = (this.prefferedDistance * this.prefferedDistance) / (d * d);

		// System.out.println(d + "/" + f);
		return vect.getUnitVector().scale(f);
	}

	@Override
	public double getPrefferedDistance() {
		return this.prefferedDistance;
	}

	@Override
	public void setPrefferedDistance(double prefferedDistance) {
		this.prefferedDistance = prefferedDistance;
	}

}
