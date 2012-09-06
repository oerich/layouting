package de.te.layouting.geometry;

public interface ISize {

	public static final ISize SIZE = new Size();

	double getWidth();

	void setWidth(double width);

	double getHeight();

	void setHeight(double height);

	ISize resize(double width, double height);

	ISize resize(ISize size);

//	ISize clone();

}
