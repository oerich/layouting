package de.te.layouting.geometry;

/**
 * Allows some simple vector-computations.
 *
 * @author Eric Knauss
 *
 */
public class Vector {

	private double x;
	private double y;

	/**
	 * Creates a vector between that leads from Point <code>p1</code> to Point
	 * <code>p2</code>.
	 *
	 * @param p1
	 *            - starting point of the vector
	 * @param p2
	 *            - target point of the vecotr
	 */
	public Vector(IPoint p1, IPoint p2) {
		this.x = p2.getX() - p1.getX();
		this.y = p2.getY() - p1.getY();
	}

	/**
	 * Creates a Vector that leads to the Point given.
	 *
	 * @param p
	 *            - target point of the vector
	 */
	public Vector(IPoint p) {
		this.x = p.getX();
		this.y = p.getY();
	}

	/**
	 * Creates a vector <code>(x,y)</code>.
	 *
	 * @param x
	 *            - the horizontal component of the vector
	 * @param y
	 *            - the vertical component of the vector
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Creates a vector <code>(0,0)</code>.
	 */
	public Vector() {
		this.x = 0;
		this.y = 0;
	}

	/**
	 * Returns the horizontal component of this vector.
	 *
	 * @return The horizontal component of this vector.
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Returns the vertical component of this vector.
	 *
	 * @return The vertical component of this vector.
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * Adds the <code>other</code> Vector to this one and returns this vector.
	 * If this vector is <code>(x1,y1)</code> and the other vector is
	 * <code>(x2,y2)</code>, than after this operation this vector is
	 * <code>(x1+x2,y1+y2)</code>.
	 *
	 * @param other
	 *            - the vector that should be added.
	 * @return This vector, after the other vector has been added.
	 */
	public Vector add(Vector other) {
		this.x += other.getX();
		this.y += other.getY();
		return this;
	}

	/**
	 * Scales this vector by <code>c</code> and returns it. If this vector is
	 * <code>(x,y)</code>, than after this operation it is
	 * <code>(c*x,c*y)</code>.
	 *
	 * @param c
	 *            - the scalar
	 * @return This vector after scaling.
	 */
	public Vector scale(double c) {
		this.x *= c;
		this.y *= c;
		return this;
	}

	/**
	 * Returns the length of this Vector.
	 *
	 * @return Returns the length of this Vector.
	 */
	public double getLength() {
		return Math.sqrt(getX() * getX() + getY() * getY());
	}

	/**
	 * Returns a duplicate of this vector.
	 *
	 * @return Returns a duplicate of this vector.
	 */
	public Vector clone() {
		return new Vector(getX(), getY());
	}

	/**
	 * Returns a (new) vector that points in the same direction as this vector
	 * and has the length 1.
	 *
	 * @return a (new) vector that points in the same direction as this vector
	 *         and has the length 1.
	 */
	public Vector getUnitVector() {
		return clone().scale(1 / getLength());
	}

	/**
	 * Returns the point this vector points to.
	 *
	 * @return the point this vector points to.
	 */
	public IPoint getPoint() {
		return new Point(this.x, this.y);
	}
}
