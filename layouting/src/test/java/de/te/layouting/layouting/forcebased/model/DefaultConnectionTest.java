package de.te.layouting.layouting.forcebased.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.te.layouting.geometry.Point;
import de.te.layouting.util.IConnection;
import de.te.layouting.util.INode;

public class DefaultConnectionTest {

	private IConnection<Object> connection;

	@Before
	public void setUp() throws Exception {
		this.connection = new DefaultConnection<Object>();
	}

	@Test
	public void testFrom() {
		Point pos = new Point();
		assertEquals(pos, this.connection.getFrom().getPosition());

		INode v = new DefaultNode();
		v.setPosition(new Point(73, 115));
		this.connection.setFrom(v);
		assertEquals(v, this.connection.getFrom());
	}

	@Test
	public void testTo() {
		Point pos = new Point();
		assertEquals(pos, this.connection.getTo().getPosition());

		INode v = new DefaultNode();
		v.setPosition(new Point(73, 115));
		this.connection.setTo(v);
		assertEquals(v, this.connection.getTo());
	}

}
