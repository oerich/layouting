package de.te.layouting.util;

import java.awt.geom.Point2D;

public class LayoutUtilities {

	public static Point2D addVectors(Point2D v1, Point2D v2) {
		return new Point2D.Double(v1.getX() + v2.getX(), v1.getY() + v2.getY());
	}

	public static Point2D scaleVector(Point2D vector, double factor) {
		return new Point2D.Double(vector.getX() * factor, vector.getY()
				* factor);
	}

	public static double getLength(Point2D impulse) {
		return Math.sqrt(impulse.getX() * impulse.getX() + impulse.getY()
				* impulse.getY());
	}

	public static Point2D getVectorBetween(Point2D p1, Point2D p2) {
		return new Point2D.Double(p2.getX() - p1.getX(), p2.getY() - p1.getY());
	}

	public static Point2D getUnitVector(Point2D vector) {
		return scaleVector(vector, 1 / getLength(vector));
	}
}
