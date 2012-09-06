package de.te.layouting.layouting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.te.layouting.geometry.Bounds;

public class InsideAreaConstraintTest {
	private InsideAreaConstraint constraint = new InsideAreaConstraint(10, 10,
			10, 10);

	@Test
	public void testDisjoint() {
		Bounds b = new Bounds(2, 2, 2, 2);
		this.constraint.getLegalBounds(b);
		assertEquals(10, b.getX(), 0.01);
		assertEquals(10, b.getY(), 0.01);
		assertEquals(2, b.getWidth(), 0.01);
		assertEquals(2, b.getHeight(), 0.01);
	}

	@Test
	public void testLeftOverLap() {
		Bounds b = new Bounds(5, 15, 8, 2);
		this.constraint.getLegalBounds(b);
		assertEquals(10, b.getX(), 0.01);
		assertEquals(15, b.getY(), 0.01);
		assertEquals(8, b.getWidth(), 0.01);
		assertEquals(2, b.getHeight(), 0.01);
	}

	@Test
	public void testRightOverLap() {
		Bounds b = new Bounds(15, 15, 8, 2);
		this.constraint.getLegalBounds(b);
		assertEquals(12, b.getX(), 0.01);
		assertEquals(15, b.getY(), 0.01);
		assertEquals(8, b.getWidth(), 0.01);
		assertEquals(2, b.getHeight(), 0.01);
	}

	@Test
	public void testIncluded() {
		Bounds b = new Bounds(12, 12, 5, 5);
		this.constraint.getLegalBounds(b);
		assertEquals(12, b.getX(), 0.01);
		assertEquals(12, b.getY(), 0.01);
		assertEquals(5, b.getWidth(), 0.01);
		assertEquals(5, b.getHeight(), 0.01);

		b = new Bounds(13, 13, 5, 5);
		this.constraint.getLegalBounds(b);
		assertEquals(13, b.getX(), 0.01);
		assertEquals(13, b.getY(), 0.01);
		assertEquals(5, b.getWidth(), 0.01);
		assertEquals(5, b.getHeight(), 0.01);
	}

	@Test
	public void testIsLegal() {
		Bounds b = new Bounds(12, 12, 5, 5);
		Bounds b2 = new Bounds(15, 15, 8, 2);

		assertTrue(this.constraint.isLegal(b));
		assertFalse(this.constraint.isLegal(b2));
	}
}
