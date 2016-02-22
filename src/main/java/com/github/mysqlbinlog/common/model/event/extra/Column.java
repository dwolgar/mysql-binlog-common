package com.github.mysqlbinlog.common.model.event.extra;

import java.io.Serializable;

public abstract class Column implements Serializable {
	private static final long serialVersionUID = -8352738500192634887L;

	abstract public Object getValue();
}
