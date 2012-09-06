package de.te.layouting.util;

import de.te.layouting.geometry.IPoint;
import de.te.layouting.geometry.Vector;

public interface INode {

	public IPoint getPosition();

	public void addToPosition(Vector vector);

	public void setPosition(IPoint position);

	public boolean isHiglighted();

	public void setHighlighted(boolean arg);
}
