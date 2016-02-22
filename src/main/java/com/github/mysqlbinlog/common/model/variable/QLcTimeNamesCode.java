package com.github.mysqlbinlog.common.model.variable;

public class QLcTimeNamesCode extends StatusVariable {
	private static final long serialVersionUID = 3944457493730034933L;
	private final int lcTimeNames;

	public QLcTimeNamesCode(int lcTimeNames) {
		this.lcTimeNames = lcTimeNames;
	}

	public int getLcTimeNames() {
		return lcTimeNames;
	}

	@Override
	public String toString() {
		return "QLcTimeNamesCode [lcTimeNames=" + lcTimeNames + "]";
	}
}
