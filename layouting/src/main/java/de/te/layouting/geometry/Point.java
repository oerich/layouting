package de.te.layouting.geometry;

public class Point implements IPoint {

	private double x;
	private double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point() {
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public IPoint place(double x, double y) {
		this.x = x;
		this.y = y;
		return this;
	}

	@Override
	public IPoint place(IPoint point) {
		return place(point.getX(), point.getY());
	}

	@Override
	public IPoint clone() {
		return new Point(this.x, this.y);
	}

	@Override
	public String toString() {
		return getClass().getName() + "(" + this.x + "," + this.y + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Point)) {
			return false;
		}

		Point p = (Point) obj;

		if (this.x != p.x)
			return false;
		if (this.y != p.y)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

}
