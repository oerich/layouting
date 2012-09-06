package de.te.layouting.layouting.forcebased.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import de.te.layouting.geometry.IPoint;
import de.te.layouting.geometry.Vector;
import de.te.layouting.layouting.forcebased.ForceBasedLayouter;
import de.te.layouting.layouting.forcebased.model.PhysicalNode;
import de.te.layouting.util.IConnection;
import de.te.layouting.util.INode;

public class ForceBasedLayoutVisualizer extends JLabel {
	private static final long serialVersionUID = 1L;
	private ForceBasedLayouter<?, ?, ?> layouter;

	public ForceBasedLayoutVisualizer(ForceBasedLayouter<?, ?, ?> layouter) {
		this.layouter = layouter;
	}

	@Override
	public void paint(Graphics g) {
		// Paint all vertices
		g.setColor(Color.BLACK);
		for (PhysicalNode v : this.layouter.getNodes()) {
			if (v.isHiglighted())
				g.fillOval(((int) v.getPosition().getX()) - 5,
						((int) v.getPosition().getY()) - 5, 10,
						10);
			else
				g.drawOval(((int) v.getPosition().getX()) - 5,
						((int) v.getPosition().getY()) - 5, 10,
						10);
		}

		// Paint all connections
		for (IConnection<?> c : this.layouter.getConnections()) {
			INode v1 = c.getFrom();
			INode v2 = c.getTo();
			g.drawLine((int) v1.getPosition().getX(), (int) v1.getPosition()
					.getY(), (int) v2.getPosition().getX(), (int) v2
					.getPosition().getY());
			g.drawLine((int) v1.getPosition().getX(), (int) v1.getPosition()
					.getY(), (int) v2.getPosition().getX(), (int) v2
					.getPosition().getY());
		}

		// Paint all forces
		g.setColor(Color.blue);
		for (PhysicalNode v : this.layouter.getNodes()) {
			IPoint p1 = v.getPosition();
			Vector vect = new Vector(p1.getX(),p1.getY());
			vect.add(v.getImpulse());
			g.drawLine((int) p1.getX(), (int) p1.getY(), (int) vect.getX(),
					(int) vect.getY());
		}
	}

	public PhysicalNode getVertex(int x, int y) {
		for (PhysicalNode v : this.layouter.getNodes()) {
			if (v.getPosition().getX() - 5 < x
					&& v.getPosition().getX() + 5 > x
					&& v.getPosition().getY() - 5 < y
					&& v.getPosition().getX() + 5 > y) {
				return v;
			}
		}
		return null;
	}
}
