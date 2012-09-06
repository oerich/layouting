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

public class PhysicalNodeTest {

	private PhysicalNode physicalVertex;
	private INode vertex;

	@Before
	public void setUp() throws Exception {
		this.vertex = new DefaultNode();
		this.physicalVertex = new PhysicalNode(this.vertex);
	}

	@Test
	public void testAddToPosition() {
		IPoint p = new Point(5, 5);
		Vector v = new Vector(p);
		this.physicalVertex.setPosition(p);
		assertEquals(p, this.physicalVertex.getPosition());
		assertEquals(p, this.vertex.getPosition());

		this.physicalVertex.addToPosition(v);
		assertEquals(new Point(10, 10), this.physicalVertex.getPosition());
		assertEquals(new Point(10, 10), this.vertex.getPosition());
	}

	@Test
	public void testImpulse() {
		assertEquals(0, this.physicalVertex.getImpulse().getX(), 0.01);
		assertEquals(0, this.physicalVertex.getImpulse().getY(), 0.01);
		Vector vector = new Vector(7, 7);
		this.physicalVertex.setImpulse(vector);
		assertEquals(vector, this.physicalVertex.getImpulse());
		this.physicalVertex.addImpulse(new Vector(3, 3));
		assertEquals(10, this.physicalVertex.getImpulse().getX(), 0.01);
		assertEquals(10, this.physicalVertex.getImpulse().getY(), 0.01);
		this.physicalVertex.scaleImpulse(0.5);
		assertEquals(5, this.physicalVertex.getImpulse().getX(), 0.01);
		assertEquals(5, this.physicalVertex.getImpulse().getY(), 0.01);
	}

	@Test
	public void testGetCineticEnergy() {
		// This should be equal to the length of the impulse
		assertEquals(0, this.physicalVertex.getCineticEnergy(), 0.001d);
		this.physicalVertex.setImpulse(new Vector(3, 4));
		assertEquals(5, this.physicalVertex.getCineticEnergy(), 0.001d);

		// And it should relate to the mass
		this.physicalVertex.setMass(2);
		assertEquals(10, this.physicalVertex.getCineticEnergy(), 0.001d);
	}

	@Test
	public void testGetMass() {
		assertEquals(1, this.physicalVertex.getMass(), 0.001d);
		this.physicalVertex.setMass(3);
		assertEquals(3, this.physicalVertex.getMass(), 0.001d);
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
