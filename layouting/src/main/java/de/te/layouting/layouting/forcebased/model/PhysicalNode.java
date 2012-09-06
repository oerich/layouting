package de.te.layouting.layouting.forcebased.model;

import de.te.layouting.geometry.IPoint;
import de.te.layouting.geometry.Vector;
import de.te.layouting.layouting.forcebased.IPhysicalEntity;
import de.te.layouting.util.INode;

public class PhysicalNode implements INode, IPhysicalEntity {

	private INode delegate;
	private Vector impulse = new Vector();
	private double mass = 1;
	private boolean isHighlighted;// TODO rename getter / setter

	public PhysicalNode(INode delegate) {
		this.delegate = delegate;
	}

	public PhysicalNode() {
		this(new DefaultNode());
	}

	public boolean isOutsider() {
		return this.isHighlighted;
	}

	public void setIsOutsider(boolean arg) {
		this.isHighlighted = arg;
	}

	public void setDelegate(INode delegate) {
		this.delegate = delegate;
	}

	public INode getDelegate() {
		return this.delegate;
	}

	@Override
	public void addToPosition(Vector vector) {
		this.delegate.addToPosition(vector);
	}

	@Override
	public IPoint getPosition() {
		return this.delegate.getPosition();
	}

	@Override
	public void setPosition(IPoint position) {
		this.delegate.setPosition(position);
	}

	@Override
	public void addImpulse(Vector vector) {
		this.impulse.add(vector);
	}

	@Override
	public double getCineticEnergy() {
		return getMass() * this.impulse.getLength();
	}

	@Override
	public Vector getImpulse() {
		return this.impulse;
	}

	@Override
	public double getMass() {
		return this.mass;
	}

	@Override
	public void scaleImpulse(double factor) {
		this.impulse.scale(factor);
	}

	@Override
	public void setImpulse(Vector vector) {
		this.impulse = vector;
	}

	@Override
	public void setMass(double i) {
		this.mass = i;
	}

	@Override
	public boolean isHiglighted() {
		return this.isHighlighted;
	}

	@Override
	public void setHighlighted(boolean arg) {
		this.isHighlighted = arg;
	}

}
