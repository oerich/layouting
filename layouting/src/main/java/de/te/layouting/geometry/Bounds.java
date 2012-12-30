package de.te.layouting.geometry;

public class Bounds implements IBounds {

	private double x;
	private double y;
	private double width;
	private double height;

	public Bounds() {
	}

	public Bounds(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public double getY() {
		return this.y;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public double getWidth() {
		return this.width;
	}

	@Override
	public void setWidth(double width) {
		this.width = width;
	}

	@Override
	public double getHeight() {
		return this.height;
	}

	@Override
	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public IBounds place(double x, double y) {
		this.x = x;
		this.y = y;
		return this;
	}

	@Override
	public IBounds place(IPoint point) {
		return place(point.getX(), point.getY());
	}

	@Override
	public IBounds resize(double width, double height) {
		this.width = width;
		this.height = height;
		return this;
	}

	@Override
	public IBounds resize(ISize size) {
		return resize(size.getWidth(), size.getHeight());
	}

	@Override
	public IBounds place(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		return this;
	}

	@Override
	public IBounds place(IBounds bounds) {
		return place(bounds.getX(), bounds.getY(), bounds.getWidth(),
				bounds.getHeight());
	}

	@Override
	public boolean overlap(IBounds bounds) {
		return (x <= (bounds.getX() + bounds.getWidth()))
				&& (bounds.getX() <= (x + width))
				&& (y <= (bounds.getY() + bounds.getHeight()))
				&& (bounds.getY() <= (y + height));
	}

	@Override
	public IBounds clone() {
		return new Bounds(this.x, this.y, this.width, this.height);
	}

	@Override
	public String toString() {
		return getClass().getName() + "[x=" + x + ",y=" + y + ",width=" + width
				+ ",height=" + height + "]";
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof IBounds))
			return false;

		IBounds b = (IBounds) o;
		if (b.getX() != x)
			return false;
		if (b.getY() != y)
			return false;
		if (b.getWidth() != width)
			return false;
		if (b.getHeight() != height)
			return false;
		return true;
	}

}
