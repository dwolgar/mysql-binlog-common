package com.github.mysqlbinlog.common.model.variable;

import java.util.Arrays;

public class RowUserVariable extends UserVariable {
	private static final long serialVersionUID = 445206876121700314L;
	private final byte[] value;
	
	public RowUserVariable(byte[] value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "RowUserVariable [value=" + Arrays.toString(value) + "]";
	}

}
