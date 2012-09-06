package de.te.layouting.layouting;

import de.te.layouting.geometry.IBounds;

/**
 * 
 * @author Torben Wiechers and Eric Knauss
 * 
 * @param <GraphType>
 *            the model that should be interpreted as graph
 * @param <ConnectionType>
 *            the type of connections that exist between elements of the
 *            GraphType
 * @param <NodeType>
 *            the type of model elements that should be interpreted as nodes of
 *            the graph
 * @see IGraphProvider
 */
public interface IGraphLayouter<GraphType, ConnectionType, NodeType> {

	/**
	 * Let the layouter run until the result is perfect.
	 * 
	 * @see IGraphLayouter#layout(int)
	 */
	public static final int INFINITY = -1;

	/**
	 * Converts the graph in the internal structure needed for layouting.
	 * 
	 * @param graph
	 * @param graphProvider
	 * @param bounds
	 */
	void initialize(GraphType graph,
			IGraphProvider<GraphType, ConnectionType, NodeType> graphProvider,
			IBounds bounds, int preferredDistance);

	/**
	 * Computes the layout of the graph.
	 * <ul>
	 * <li><b>Pre-Condition:</b> layouter is initialized.</li>
	 * <li><b>Post-Condition:</b> layout has improved by <code>steps</code>
	 * steps.</li>
	 * </ul>
	 * 
	 * After the computation, the
	 * {@link IGraphProvider#placeNode(Object, Object, IBounds)} operation is
	 * executed for all nodes.
	 * 
	 * @param steps
	 *            - the number of steps that should be computed. Use
	 *            {@link #INFINITY} or {@link Integer#MAX_VALUE}, if you want
	 *            the best possible layout.
	 * @return
	 */
	boolean layout(int steps);

}
