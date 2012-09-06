package de.te.layouting.layouting.forcebased.forces;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.te.layouting.geometry.Bounds;
import de.te.layouting.geometry.Point;
import de.te.layouting.layouting.forcebased.model.PhysicalNode;
import de.te.layouting.util.IConnection;

public class BorderRepulsionForceTest {

	private BorderRepulsionForce borderRepulsion;

	@Before
	public void setUp() throws Exception {
		// this.borderRepulsion = new BorderRepulsionForce(new Dimension(100,
		// 100));
		this.borderRepulsion = new BorderRepulsionForce(new Bounds(0, 0, 100,
				100));
		this.borderRepulsion.setPrefferedDistance(10);
	}

	@Test
	public void testInsideRepulsion() {
		PhysicalNode n = new PhysicalNode();
		assertEquals(0, n.getCineticEnergy(), 0.005);
		n.setPosition(new Point(10, 50));
		this.borderRepulsion.computeForce(new PhysicalNode[] { n },
				new IConnection<?>[0]);
		assertEquals(10, n.getPosition().getX(), 0.005);
		assertEquals(50, n.getPosition().getY(), 0.005);
		assertEquals(0.99, n.getCineticEnergy(), 0.005);
		assertEquals(0.99, n.getImpulse().getX(), 0.005);
		assertEquals(0, n.getImpulse().getY(), 0.005);
	}

	@Test
	public void testLeftOutsideRepulsion() {
		PhysicalNode n = new PhysicalNode();
		assertEquals(0, n.getCineticEnergy(), 0.005);
		n.setPosition(new Point(-10, 50));
		this.borderRepulsion.computeForce(new PhysicalNode[] { n },
				new IConnection<?>[0]);
		assertEquals(-10, n.getPosition().getX(), 0.005);
		assertEquals(50, n.getPosition().getY(), 0.005);
		assertEquals(12, n.getCineticEnergy(), 0.005);
		assertEquals(12, n.getImpulse().getX(), 0.005);
		assertEquals(0, n.getImpulse().getY(), 0.005);
	}

	@Test
	public void testRightOutsideRepulsion() {
		PhysicalNode n = new PhysicalNode();
		assertEquals(0, n.getCineticEnergy(), 0.005);
		n.setPosition(new Point(110, 50));
		this.borderRepulsion.computeForce(new PhysicalNode[] { n },
				new IConnection<?>[0]);
		assertEquals(110, n.getPosition().getX(), 0.005);
		assertEquals(50, n.getPosition().getY(), 0.005);
		assertEquals(12, n.getCineticEnergy(), 0.005);
		assertEquals(-12, n.getImpulse().getX(), 0.005);
		assertEquals(0, n.getImpulse().getY(), 0.005);
	}

	@Test
	public void testTopOutsideRepulsion() {
		PhysicalNode n = new PhysicalNode();
		assertEquals(0, n.getCineticEnergy(), 0.005);
		n.setPosition(new Point(50, -10));
		this.borderRepulsion.computeForce(new PhysicalNode[] { n },
				new IConnection<?>[0]);
		assertEquals(50, n.getPosition().getX(), 0.005);
		assertEquals(-10, n.getPosition().getY(), 0.005);
		assertEquals(12, n.getCineticEnergy(), 0.005);
		assertEquals(0, n.getImpulse().getX(), 0.005);
		assertEquals(12, n.getImpulse().getY(), 0.005);
	}

	@Test
	public void testBottomOutsideRepulsion() {
		PhysicalNode n = new PhysicalNode();
		assertEquals(0, n.getCineticEnergy(), 0.005);
		n.setPosition(new Point(50, 110));
		this.borderRepulsion.computeForce(new PhysicalNode[] { n },
				new IConnection<?>[0]);
		assertEquals(50, n.getPosition().getX(), 0.005);
		assertEquals(110, n.getPosition().getY(), 0.005);
		assertEquals(12, n.getCineticEnergy(), 0.005);
		assertEquals(0, n.getImpulse().getX(), 0.005);
		assertEquals(-12, n.getImpulse().getY(), 0.005);
	}
}
