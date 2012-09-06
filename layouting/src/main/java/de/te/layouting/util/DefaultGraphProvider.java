package de.te.layouting.util;

import java.util.List;

import de.te.layouting.geometry.IBounds;
import de.te.layouting.geometry.ISize;
import de.te.layouting.geometry.Point;
import de.te.layouting.geometry.Size;
import de.te.layouting.layouting.IGeometricalLayoutConstraint;
import de.te.layouting.layouting.IGraphProvider;

public class DefaultGraphProvider implements
		IGraphProvider<RandomGraph, IConnection<String>, INode> {

	@Override
	public INode getConnectionTarget(RandomGraph graph,
			IConnection<String> connection) {
		return connection.getTo();
	}

	@Override
	public List<INode> getNodes(RandomGraph graph) {
		return graph.getNodes();
	}

	@Override
	public ISize getPreferredSize(RandomGraph graph, INode state) {
		return new Size(5, 5);
	}

	@Override
	public List<IConnection<String>> getSuccessors(RandomGraph graph, INode node) {
		return graph.getSuccessors(node);
	}

	@Override
	public void hideNode(RandomGraph graph, INode node) {
	}

	@Override
	public void placeNode(RandomGraph graph, INode node, IBounds bounds) {
		node.setPosition(new Point(bounds.getX(), bounds.getY()));
	}

	@Override
	public IGeometricalLayoutConstraint[] getLayoutConstraints(
			RandomGraph graph, INode node) {
		return graph.getConstraints(node);
	}

}
