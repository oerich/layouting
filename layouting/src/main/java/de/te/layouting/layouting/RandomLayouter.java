package de.te.layouting.layouting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import de.te.layouting.geometry.Bounds;
import de.te.layouting.geometry.IBounds;

public class RandomLayouter<GraphType, ConnectionType, NodeType> implements
		IGraphLayouter<GraphType, ConnectionType, NodeType> {

	private GraphType graph;
	private IGraphProvider<GraphType, ConnectionType, NodeType> graphProvider;
	private IBounds fullBounds;
	private Random random;

	private List<LayoutNode> nodes;

	@Override
	public void initialize(GraphType graph,
			IGraphProvider<GraphType, ConnectionType, NodeType> graphProvider,
			IBounds bounds, int prefferedSize) {
		this.graph = graph;
		this.graphProvider = graphProvider;
		this.fullBounds = bounds;
		this.random = new Random();

		loadGraph();
	}

	private void loadGraph() {
		Map<NodeType, LayoutNode> nodeMap = new HashMap<NodeType, LayoutNode>();
		// Knoten einlesen
		nodes = new ArrayList<LayoutNode>();

		for (NodeType modelNode : graphProvider.getNodes(graph)) {
			LayoutNode node = new LayoutNode(modelNode);
			nodes.add(node);
			nodeMap.put(modelNode, node);
			node.bounds.place(fullBounds);
			node.bounds
					.resize(graphProvider.getPreferredSize(graph, modelNode));
		}

		// Kanten einlesen
		for (LayoutNode node : nodes) {
			for (ConnectionType connection : graphProvider.getSuccessors(graph,
					node.modelNode)) {
				node.successors.add(nodeMap.get(graphProvider
						.getConnectionTarget(graph, connection)));
			}
		}
	}

	@Override
	public boolean layout(int steps) {
		boolean finished = false;
		for (int i = 0; i < steps || steps == INFINITY; i++) {
			if (layoutStep()) {
				finished = true;
				break;
			}
		}

		transferLayout();
		return finished;
	}

	private void transferLayout() {
		for (LayoutNode node : nodes) {
			graphProvider.placeNode(graph, node.modelNode, node.bounds);
		}
	}

	private boolean layoutStep() {
		if (calculatedConflicted() == 0)
			return true;
		LayoutNode selected = selectMaxConflicted();

		do {
			selected.place();
		} while (selected.isAdvanced());

		return false;
	}

	private int calculatedConflicted() {
		int conflicts = 0;
		for (LayoutNode node : nodes) {
			node.conflicted = 0;
		}

		for (int i = 0; i < nodes.size(); i++) {
			LayoutNode nodeI = nodes.get(i);

			for (int j = i + 1; j < nodes.size(); j++) {
				LayoutNode nodeJ = nodes.get(j);

				if (nodeI.bounds.overlap(nodeJ.bounds)) {
					nodeI.conflicted++;
					nodeJ.conflicted++;
					conflicts++;
				}
			}
		}
		return conflicts;
	}

	private LayoutNode selectMaxConflicted() {
		int maxConflicted = 0;
		int maxCount = 0;
		for (LayoutNode node : nodes) {
			if (node.conflicted > maxConflicted) {
				maxCount = 1;
				maxConflicted = node.conflicted;
			} else if (node.conflicted == maxConflicted) {
				maxCount++;
			}
		}

		int index = random.nextInt() % maxCount;
		for (LayoutNode node : nodes) {
			if (node.conflicted == maxConflicted) {
				if (index == 0)
					return node;
				index--;
			}
		}
		return null;
	}

	private class LayoutNode {
		public NodeType modelNode;
		public IBounds bounds;
		public int conflicted;

		public List<LayoutNode> successors;

		public LayoutNode(NodeType modelNode) {
			this.modelNode = modelNode;
			this.successors = new ArrayList<LayoutNode>();
			this.bounds = new Bounds();
		}

		public boolean isAdvanced() {
			int conflicts = 0;
			for (LayoutNode node : nodes) {
				if (node != this && node.bounds.overlap(bounds)) {
					conflicts++;
				}
			}
			return conflicts < conflicted;
		}

		public void place() {
			double x = fullBounds.getX() + random.nextInt()
					% (fullBounds.getWidth() - bounds.getWidth() + 1);
			double y = fullBounds.getY() + random.nextInt()
					% (fullBounds.getHeight() - bounds.getHeight() + 1);

			bounds.place(x, y);
		}
	}

}
