package com.github.mysqlbinlog.common.model.event.extra;


import java.io.Serializable;

public final class Pair<T> implements Serializable {
	private static final long serialVersionUID = 7552018678346075096L;
	
	private T before;
	private T after;

	public Pair() {}

	public Pair(T before, T after) {
		this.before = before;
		this.after = after;
	}

	public T getBefore() {
		return before;
	}
	public void setBefore(T before) {
		this.before = before;
	}

	public T getAfter() {
		return after;
	}
	public void setAfter(T after) {
		this.after = after;
	}

	public void swap() {
		final T t = this.before;
		this.before = this.after;
		this.after = t;
	}
}
