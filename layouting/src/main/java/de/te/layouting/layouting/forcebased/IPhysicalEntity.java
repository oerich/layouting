package de.te.layouting.layouting.forcebased;

import de.te.layouting.geometry.Vector;

public interface IPhysicalEntity {

	public double getMass();

	public double getCineticEnergy();

	public Vector getImpulse();

	public void setImpulse(Vector vector);

	public void addImpulse(Vector vector);

	public void scaleImpulse(double factor);

	void setMass(double i);
}
