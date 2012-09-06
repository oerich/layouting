package de.te.layouting.geometry;

public interface IPoint {

	public static final IPoint POINT = new Point();

	double getX();

	void setX(double x);

	double getY();

	void setY(double y);

	IPoint place(double X, double Y);

	IPoint place(IPoint point);

//	IPoint clone();

}
