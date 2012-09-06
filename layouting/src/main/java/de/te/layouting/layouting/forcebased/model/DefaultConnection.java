package de.te.layouting.layouting.forcebased.model;

import de.te.layouting.util.IConnection;
import de.te.layouting.util.INode;

public class DefaultConnection<T> implements IConnection<T> {

	private INode from = new DefaultNode();
	private INode to = new DefaultNode();
	private T type;

	@Override
	public INode getFrom() {
		return this.from;
	}

	@Override
	public INode getTo() {
		return this.to;
	}

	@Override
	public void setFrom(INode vertex) {
		this.from = vertex;
	}

	@Override
	public void setTo(INode vertex) {
		this.to = vertex;
	}

	@Override
	public T getType() {
		return this.type;
	}

	@Override
	public void setType(T type) {
		this.type = type;
	}

}
