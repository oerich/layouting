package de.te.layouting.layouting.forcebased.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.te.layouting.geometry.IPoint;
import de.te.layouting.geometry.Point;
import de.te.layouting.geometry.Vector;
import de.te.layouting.util.INode;

public class DefaultVertexTest {

	private INode vertex;

	@Before
	public void setup() {
		this.vertex = new DefaultNode();
	}

	@Test
	public void testAddToPosition() {
		assertEquals(0, this.vertex.getPosition().getX(), 0.01);
		assertEquals(0, this.vertex.getPosition().getY(), 0.01);
		Vector vector = new Vector(3, -1);
		IPoint position = new Point(3, -1);
		this.vertex.addToPosition(vector);
		assertEquals(position, this.vertex.getPosition());
		this.vertex.addToPosition(vector);
		assertEquals(new Point(6, -2), this.vertex.getPosition());
	}

	@Test
	public void testGetPosition() {
		assertEquals(new Point(), this.vertex.getPosition());
		IPoint position = new Point(3, -1);
		this.vertex.setPosition(position);
		assertEquals(position, this.vertex.getPosition());
	}

	@Test
	public void testHighlighting() {
		assertFalse(vertex.isHiglighted());
		vertex.setHighlighted(true);
		assertTrue(vertex.isHiglighted());
		vertex.setHighlighted(false);
		assertFalse(vertex.isHiglighted());
	}

}
