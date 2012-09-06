package de.te.layouting.layouting;

public interface ILayoutConstraint {

	public static final int OPTIONAL = -1;
	public static final int LOW_PRIORITY = 10;
	public static final int NORMAL_PRIORITY = 100;
	public static final int HIGH_PRIORITY = 1000;
	public static final int MANDATORY = Integer.MAX_VALUE;

	int getPriority();

}
