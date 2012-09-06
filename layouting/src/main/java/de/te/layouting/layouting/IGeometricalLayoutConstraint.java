package de.te.layouting.layouting;

import de.te.layouting.geometry.IBounds;

public interface IGeometricalLayoutConstraint {

	/**
	 * Converts the <code>currentBounds</code> into reasonable legal bounds. If
	 * <code>currentBounds</code> is legal, than it should be returned without
	 * change.
	 * 
	 * @param currentBounds
	 *            - the bounds that a given Object should have.
	 * @return
	 */
	IBounds getLegalBounds(IBounds currentBounds);

	/**
	 * Checks, wheter the Bounds are legal with respect to this constraint.
	 * 
	 * @param currentBounds
	 * @return true, if everything is alright.
	 */
	boolean isLegal(IBounds currentBounds);
}
