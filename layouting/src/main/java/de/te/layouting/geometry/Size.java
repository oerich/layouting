package de.te.layouting.geometry;

public class Size implements ISize {

	private double width;
	private double height;

	public Size() {
	}

	public Size(double width, double height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public double getWidth() {
		return this.width;
	}

	@Override
	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public void setWidth(double width) {
		this.width = width;
	}

	@Override
	public ISize resize(double width, double height) {
		this.width = width;
		this.height = height;
		return this;
	}

	@Override
	public ISize resize(ISize size) {
		return resize(size.getWidth(), size.getHeight());
	}

	@Override
	public ISize clone() {
		return new Size(this.width, this.height);
	}

}
