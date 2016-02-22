package com.github.mysqlbinlog.common.model.variable;


public class QAutoIncrement extends StatusVariable {
	private static final long serialVersionUID = -2189013512878709870L;
	private final int autoIncrementIncrement;
	private final int autoIncrementOffset;

	public QAutoIncrement(int autoIncrementIncrement, int autoIncrementOffset) {
		this.autoIncrementIncrement = autoIncrementIncrement;
		this.autoIncrementOffset = autoIncrementOffset;
	}

	public int getAutoIncrementIncrement() {
		return autoIncrementIncrement;
	}

	public int getAutoIncrementOffset() {
		return autoIncrementOffset;
	}
}
