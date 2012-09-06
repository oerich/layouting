package de.te.layouting.geometry;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VectorTest {

	private Vector vector;

	@Before
	public void setUp() throws Exception {
		this.vector = new Vector();
	}

	@Test
	public void testAdd() {
		assertEquals(0, this.vector.getX(), 0.01);
		assertEquals(0, this.vector.getY(), 0.01);

		this.vector.add(new Vector(3, 4));
		assertEquals(3, this.vector.getX(), 0.01);
		assertEquals(4, this.vector.getY(), 0.01);
	}

	@Test
	public void testScale() {
		this.vector.add(new Vector(3, 4));
		assertEquals(3, this.vector.getX(), 0.01);
		assertEquals(4, this.vector.getY(), 0.01);

		this.vector.scale(2);
		assertEquals(6, this.vector.getX(), 0.01);
		assertEquals(8, this.vector.getY(), 0.01);
	}

	@Test
	public void testGetLength() {
		assertEquals(0, this.vector.getLength(), 0.01);
		this.vector.add(new Vector(4, 0));
		assertEquals(4, this.vector.getLength(), 0.01);
		this.vector.add(new Vector(0, 0));
		assertEquals(4, this.vector.getLength(), 0.01);
	}

	@Test
	public void testUnitVector() throws Exception {
		this.vector.add(new Vector(4, 0));
		Vector v = this.vector.getUnitVector();
		assertEquals(4, this.vector.getLength(), 0.01);
		assertEquals(1, v.getLength(), 0.01);
	}

	@Test
	public void testVectorBetween() throws Exception {
		IPoint p1 = new Point(1, 1);
		IPoint p2 = new Point(2, 2);

		Vector v = new Vector(p1, p2);
		assertEquals(1, v.getX(), 0.001);
		assertEquals(1, v.getY(), 0.001);
	}
}
