package de.te.layouting.layouting.forcebased.forces;

import de.te.layouting.layouting.forcebased.IForce;
import de.te.layouting.layouting.forcebased.model.PhysicalNode;
import de.te.layouting.util.IConnection;

public class ConnectionMomentumForce implements IForce {

	private double preferredDistance;

	@Override
	public void computeForce(PhysicalNode[] nodes, IConnection<?>[] connections) {
		// for (IConnection<?> c : connections) {
		// TODO to should be right of from, force should be orthogonal to
		// connection
		// }
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public double getPrefferedDistance() {
		return this.preferredDistance;
	}

	@Override
	public void setPrefferedDistance(double prefferedDistance) {
		this.preferredDistance = prefferedDistance;
	}

}
