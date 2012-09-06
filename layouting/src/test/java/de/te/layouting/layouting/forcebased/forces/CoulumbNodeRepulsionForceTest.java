package de.te.layouting.layouting.forcebased.forces;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.te.layouting.geometry.IPoint;
import de.te.layouting.geometry.Point;
import de.te.layouting.geometry.Vector;
import de.te.layouting.layouting.forcebased.model.PhysicalNode;

public class CoulumbNodeRepulsionForceTest {

	private CoulumbNodeRepulsionForce repulsionForce;

	@Before
	public void setUp() throws Exception {
		this.repulsionForce = new CoulumbNodeRepulsionForce();

		this.repulsionForce.setPrefferedDistance(10);
	}

	@Test
	public void testComputeRepulsion() {
		IPoint p1 = new Point();
		IPoint p2 = new Point(1, 0);

		Vector v = this.repulsionForce.computeRepulsion(p1, p2);
		assertEquals(100, v.getLength(), 0.005);
		assertEquals(100, v.getX(), 0.005);
		assertEquals(0, v.getY(), 0.005);

		p2.setX(5);
		v = this.repulsionForce.computeRepulsion(p1, p2);
		assertEquals(4, v.getLength(), 0.005);
		assertEquals(4, v.getX(), 0.005);
		assertEquals(0, v.getY(), 0.005);

	}

	@Test
	public void testComputeForce() {
		IPoint p1 = new Point();
		IPoint p2 = new Point(1, 0);

		PhysicalNode[] points = new PhysicalNode[2];
		points[0] = new PhysicalNode();
		points[0].setPosition(p1);
		points[1] = new PhysicalNode();
		points[1].setPosition(p2);
		this.repulsionForce.computeForce(points, null);

		Vector v = points[0].getImpulse();
		assertEquals(100, v.getLength(), 0.005);
		assertEquals(-100, v.getX(), 0.005);
		assertEquals(0, v.getY(), 0.005);

		v = points[1].getImpulse();
		assertEquals(100, v.getLength(), 0.005);
		assertEquals(100, v.getX(), 0.005);
		assertEquals(0, v.getY(), 0.005);
	}

}
