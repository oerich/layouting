package de.te.layouting.layouting.forcebased.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import de.te.layouting.geometry.Point;
import de.te.layouting.layouting.forcebased.model.PhysicalNode;

public class MouseTool implements MouseListener {

	private int x, y;

	@Override
	public void mouseClicked(MouseEvent event) {
	}

	private ForceBasedLayoutVisualizer getCanvas(MouseEvent event) {
		ForceBasedLayoutVisualizer c = (ForceBasedLayoutVisualizer) event
				.getSource();
		return c;
	}

	@Override
	public void mouseEntered(MouseEvent event) {
	}

	@Override
	public void mouseExited(MouseEvent event) {
	}

	@Override
	public void mousePressed(MouseEvent event) {
		this.x = event.getX();
		this.y = event.getY();
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		PhysicalNode vertex = getCanvas(event).getVertex(this.x, this.y);
		if (vertex == null)
			return;

		vertex.setPosition(new Point(event.getX(), event.getY()));
		getCanvas(event).repaint();
	}

}
