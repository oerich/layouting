package de.te.layouting.layouting.forcebased;

import de.te.layouting.layouting.forcebased.model.PhysicalNode;
import de.te.layouting.util.IConnection;

public interface IForce {

	public void computeForce(PhysicalNode[] nodes, IConnection<?>[] connections);

	public double getPrefferedDistance();

	public void setPrefferedDistance(double prefferedDistance);
}
