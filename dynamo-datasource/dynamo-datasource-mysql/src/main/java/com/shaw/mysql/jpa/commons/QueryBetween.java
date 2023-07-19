package com.shaw.mysql.jpa.commons;

/**
 * @author shaw
 * @date 2023/7/18
 */
public class QueryBetween<T extends Comparable<?>> {

	public T before;
	public T after;

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

}
