package com.github.mysqlbinlog.common.model.variable;

import java.io.Serializable;

public abstract class UserVariable implements Serializable {
	private static final long serialVersionUID = 751148292043967346L;

	public UserVariable() {
		
	}
	
	public abstract Object getValue();
}
