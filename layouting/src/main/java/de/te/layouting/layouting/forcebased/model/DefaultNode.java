package de.te.layouting.layouting.forcebased.model;

import de.te.layouting.geometry.IPoint;
import de.te.layouting.geometry.Vector;
import de.te.layouting.util.INode;

public class DefaultNode implements INode {

	private Vector position = new Vector();
	private boolean isHighlighted;

	@Override
	public void addToPosition(Vector vector) {
		this.position.add(vector);
	}

	@Override
	public IPoint getPosition() {
		return this.position.getPoint();
	}

	@Override
	public void setPosition(IPoint position) {
		this.position = new Vector(position);
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
