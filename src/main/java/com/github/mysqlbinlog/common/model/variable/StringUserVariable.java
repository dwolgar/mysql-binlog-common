package com.github.mysqlbinlog.common.model.variable;

public class StringUserVariable extends UserVariable {
	private static final long serialVersionUID = 9163197926731959697L;
	private final int collation;
	private final String value;
	
	public StringUserVariable(int collation, String value) {
		this.collation = collation;
		this.value = value;
	}
	@Override
	public Object getValue() {
		return value;
	}
	public int getCollation() {
		return collation;
	}

	@Override
	public String toString() {
		return "StringUserVariable [collation=" + collation + ", value=" + value + "]";
	}
}
