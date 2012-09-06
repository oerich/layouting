package de.te.layouting.layouting;

import java.util.List;

import de.te.layouting.geometry.IBounds;
import de.te.layouting.geometry.ISize;

/**
 * A Graph Provider provides a graph for a given data structure.
 * 
 * @author Torben Wichers and Eric Knauss
 * 
 * @param <GraphType>
 *            this should be the model that should presented as a graph.
 * @param <ConnectionType>
 *            some kind of connection between nodes.
 * @param <NodeType>
 *            the types that should be represented as nodes.
 */
public interface IGraphProvider<GraphType, ConnectionType, NodeType> {

	/**
	 * Returns all nodes of the graph.
	 * 
	 * @param graph
	 * @return
	 */
	List<NodeType> getNodes(GraphType graph);

	/**
	 * Returns all outgoing connections for the given node.
	 * 
	 * @param graph
	 * @param node
	 * @return
	 */
	List<ConnectionType> getSuccessors(GraphType graph, NodeType node);

	NodeType getConnectionTarget(GraphType graph, ConnectionType connection);

	ISize getPreferredSize(GraphType graph, NodeType node);

	void placeNode(GraphType graph, NodeType node, IBounds bounds);

	void hideNode(GraphType graph, NodeType node);

	IGeometricalLayoutConstraint[] getLayoutConstraints(GraphType graph,
			NodeType node);

}
