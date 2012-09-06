package de.te.layouting.geometry;

public interface IBounds extends IPoint, ISize {

	public static final IBounds BOUNDS = new Bounds();

	IBounds place(double x, double y, double width, double height);

	IBounds place(IBounds bounds);

//	IBounds clone();

	boolean overlap(IBounds bounds);
}
