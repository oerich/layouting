package de.te.layouting.layouting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import de.te.layouting.geometry.IBounds;
import de.te.layouting.geometry.ISize;
import de.te.layouting.geometry.Size;

public class ClassStructureProvider implements IGraphProvider<Class<?>, Class<?>, Class<?>> {

	public static final ClassStructureProvider INSTANCE = new ClassStructureProvider();

	@Override
	public List<Class<?>> getNodes(Class<?> graph) {
		List<Class<?>> classes = new ArrayList<Class<?>>();

		Queue<Class<?>> queuedClass = new LinkedList<Class<?>>();
		queuedClass.add(graph);
		classes.add(graph);

		while (!queuedClass.isEmpty()) {
			Class<?> current = queuedClass.remove();

			Class<?> superclass = current.getSuperclass();
			if (!classes.contains(superclass)) {
				classes.add(superclass);
				queuedClass.add(superclass);
			}
			for (Class<?> interfaceclass : current.getInterfaces()) {
				if (!classes.contains(interfaceclass)) {
					classes.add(interfaceclass);
					queuedClass.add(interfaceclass);
				}
			}
		}
		return classes;
	}

	@Override
	public List<Class<?>> getSuccessors(Class<?> graph, Class<?> node) {
		List<Class<?>> classes = new ArrayList<Class<?>>();

		classes.add(node.getSuperclass());

		for (Class<?> interfaceclass : node.getInterfaces()) {
			classes.add(interfaceclass);
		}
		return classes;
	}

	@Override
	public Class<?> getConnectionTarget(Class<?> graph, Class<?> connection) {
		return connection;
	}

	@Override
	public ISize getPreferredSize(Class<?> graph, Class<?> state) {
		return new Size(10, 10);
	}

	@Override
	public void placeNode(Class<?> graph, Class<?> node, IBounds bounds) {
		//TODO: Muss an das Modell weitergereicht werden um ein Layouting darzustellen
	}

	@Override
	public void hideNode(Class<?> graph, Class<?> node) {
		//TODO: Muss an das Modell weitergereicht werden um ein Layouting darzustellen
	}

	@Override
	public IGeometricalLayoutConstraint[] getLayoutConstraints(Class<?> graph,
			Class<?> node) {
		// TODO Auto-generated method stub
		return null;
	}

}
