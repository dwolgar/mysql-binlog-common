package com.github.mysqlbinlog.common.model.variable;

public class RealUserVariable extends UserVariable {
	private static final long serialVersionUID = -3162081338200172139L;
	private final double value;
	
	public RealUserVariable(double value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "RealUserVariable [value=" + value + "]";
	}

}
