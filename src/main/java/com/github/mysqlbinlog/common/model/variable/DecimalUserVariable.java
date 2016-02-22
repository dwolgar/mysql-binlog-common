package com.github.mysqlbinlog.common.model.variable;

import java.util.Arrays;

public class DecimalUserVariable extends UserVariable {
	private static final long serialVersionUID = -5651588798134897979L;
	private final byte[] value;
	
	public DecimalUserVariable(byte[] value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "DecimalUserVariable [value=" + Arrays.toString(value) + "]";
	}

}
