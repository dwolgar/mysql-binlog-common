package com.github.mysqlbinlog.common.model.variable;

public class IntUserVariable extends UserVariable {
	private static final long serialVersionUID = -2247053839809095871L;
	private final long value;
	
	public IntUserVariable(long value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "IntUserVariable [value=" + value + "]";
	}

}
