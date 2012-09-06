package de.te.layouting.layouting;

import de.te.layouting.geometry.Bounds;
import de.te.layouting.geometry.IBounds;

/**
 * This constraints allows nodes only left or right of the bounds of this
 * constraint.
 * 
 * @author Eric Knauss
 * 
 */
public class HorizontallyOutsideAreaConstraint extends Bounds implements
		IGeometricalLayoutConstraint {

	public HorizontallyOutsideAreaConstraint() {
		super();
	}

	public HorizontallyOutsideAreaConstraint(double x, double y, double width,
			double height) {
		super(x, y, width, height);
	}

	@Override
	public IBounds getLegalBounds(IBounds currentBounds) {
		if (isDisjoint(currentBounds)) {
			return currentBounds;
		}
		if (isLeftOverlap(currentBounds)) {
			currentBounds.setX(currentBounds.getX()
					+ (getX() - (currentBounds.getX() + currentBounds
							.getWidth())));
			return currentBounds;
		} else if (isRightOverlap(currentBounds)) {
			currentBounds.setX(getX() + getWidth());
			return currentBounds;
		}

		// So, basically we are in the middle.
		double currentMiddle = currentBounds.getX() + currentBounds.getWidth()
				/ 2;
		double myMiddle = getX() + getWidth() / 2;
		if (currentMiddle < myMiddle) {
			currentBounds.setX(currentBounds.getX()
					+ (getX() - (currentBounds.getX() + currentBounds
							.getWidth())));
		} else {
			currentBounds.setX(getX() + getWidth());
		}
		return currentBounds;
	}

	private boolean isDisjoint(IBounds currentBounds) {
		return (currentBounds.getX() + currentBounds.getWidth()) <= (getX())
				|| currentBounds.getX() >= (getX() + getWidth());
	}

	private boolean isRightOverlap(IBounds currentBounds) {
		return currentBounds.getX() > getX()
				&& (currentBounds.getX() + currentBounds.getWidth()) > (getX() + getWidth())
				&& currentBounds.getX() < (getX() + getWidth());
	}

	private boolean isLeftOverlap(IBounds currentBounds) {
		return currentBounds.getX() < getX()
				&& (currentBounds.getX() + currentBounds.getWidth()) > getX();
	}

	@Override
	public boolean isLegal(IBounds currentBounds) {
		return isDisjoint(currentBounds);
	}

}
