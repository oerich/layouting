package de.te.layouting.layouting;

import de.te.layouting.geometry.Bounds;
import de.te.layouting.geometry.IBounds;

public class InsideAreaConstraint extends Bounds implements
		IGeometricalLayoutConstraint {

	public InsideAreaConstraint() {
		super();
	}

	public InsideAreaConstraint(double x, double y, double width, double height) {
		super(x, y, width, height);
	}

	@Override
	public IBounds getLegalBounds(IBounds currentBounds) {

		if (currentBounds.getWidth() > getWidth()) {
			// Object is wider than area
			currentBounds.setWidth(getWidth());
			currentBounds.setX(getX());
		} else if (currentBounds.getX() < getX()) {
			// Object starts left outside of area
			currentBounds.setX(getX());
		} else if ((currentBounds.getX() + currentBounds.getWidth()) > (getX() + getWidth())) {
			// Object starts right outside of area
			currentBounds.setX(getX() + getWidth() - currentBounds.getWidth());
		}

		if (currentBounds.getHeight() > getHeight()) {
			// Object is higher than area
			currentBounds.setHeight(getHeight());
			currentBounds.setY(getY());
		} else if (currentBounds.getY() < getY()) {
			// Object starts above area
			currentBounds.setY(getY());
		} else if ((currentBounds.getY() + currentBounds.getHeight()) > (getY() + getHeight())) {
			// Object ends below area
			currentBounds
					.setY(getY() + getHeight() - currentBounds.getHeight());
		}

		return currentBounds;
	}

	@Override
	public boolean isLegal(IBounds currentBounds) {
		return currentBounds.getX() >= getX()
				&& (currentBounds.getX() + currentBounds.getWidth()) <= (getX() + getWidth())
				&& currentBounds.getY() >= getY()
				&& (currentBounds.getY() + currentBounds.getHeight()) <= (getY() + getHeight());
	}

}
