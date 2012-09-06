package de.te.layouting.util;

import java.util.List;
import java.util.Random;
import java.util.Vector;

import de.te.layouting.geometry.Bounds;
import de.te.layouting.geometry.ISize;
import de.te.layouting.geometry.Point;
import de.te.layouting.geometry.IPoint;
import de.te.layouting.layouting.HorizontallyOutsideAreaConstraint;
import de.te.layouting.layouting.IGeometricalLayoutConstraint;
import de.te.layouting.layouting.InsideAreaConstraint;
import de.te.layouting.layouting.forcebased.model.DefaultConnection;
import de.te.layouting.layouting.forcebased.model.DefaultNode;

public class RandomGraph {

	private List<INode> nodes;
	private ISize size;
	private Random random = new Random();
	private double connectionProbability;
	private int nodeCount;

	public int getNodeCount() {
		return nodeCount;
	}

	public void setNodeCount(int nodeCount) {
		this.nodeCount = nodeCount;
	}

	public List<INode> getNodes() {
		this.nodes = new Vector<INode>();
		for (int i = 0; i < this.nodeCount; i++) {
			DefaultNode node = new DefaultNode();
			node.setPosition(new Point(random.nextInt((int)this.size
					.getWidth()), random.nextInt((int)this.size.getHeight())));
			this.nodes.add(node);
		}
		return this.nodes;
	}

	public ISize getPrefferedsize() {
		return this.size;
	}

	public List<IConnection<String>> getSuccessors(INode node) {
		List<IConnection<String>> ret = new Vector<IConnection<String>>();
		for (INode v : this.nodes) {
			if (this.random.nextDouble() < this.connectionProbability
					&& !v.equals(node)) {
				DefaultConnection<String> c = new DefaultConnection<String>();
				c.setFrom(node);
				c.setTo(v);
				ret.add(c);
			}
		}
		return ret;
	}

	public void setSize(ISize size) {
		this.size = size;
	}

	public void setConnectionProbability(double p) {
		this.connectionProbability = p;
	}

	public IGeometricalLayoutConstraint[] getConstraints(INode node) {
		IGeometricalLayoutConstraint[] ret = new IGeometricalLayoutConstraint[1];

		Bounds b = new Bounds(50, 50, this.size.getWidth() - 100, this.size
				.getHeight() - 100);
		Bounds bc = null;
		if (this.random.nextDouble() < 0.75) {
			bc = new InsideAreaConstraint();
		} else {
			bc = new HorizontallyOutsideAreaConstraint();
			node.setHighlighted(true);
		}
		bc.place(b);
		ret[0] = (IGeometricalLayoutConstraint) bc;

		return new IGeometricalLayoutConstraint[0];
//		return ret;
	}
}
