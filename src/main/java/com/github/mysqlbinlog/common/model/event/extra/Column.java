package com.github.mysqlbinlog.common.model.event.extra;

import java.io.Serializable;

public abstract class Column implements Serializable {
	private static final long serialVersionUID = -3829478841053596810L;
	private final int type;
	
	public Column(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
	
	abstract public Object getValue();
}
