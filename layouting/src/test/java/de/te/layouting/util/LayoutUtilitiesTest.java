package de.te.layouting.util;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.Test;

public class LayoutUtilitiesTest {

	@Test
	public void testAddVectors() {
		Point2D t1 = new Point2D.Double(1, 2);
		assertEquals(t1, LayoutUtilities.addVectors(t1, new Point2D.Double()));
		assertEquals(new Point2D.Double(2, 4), LayoutUtilities.addVectors(t1,
				t1));
	}

}
