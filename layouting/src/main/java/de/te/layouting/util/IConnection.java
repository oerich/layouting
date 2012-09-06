package de.te.layouting.util;

public interface IConnection<T> {

	public INode getFrom();

	public INode getTo();

	public void setFrom(INode vertex);

	public void setTo(INode vertex);

	public void setType(T type);

	public T getType();
}
