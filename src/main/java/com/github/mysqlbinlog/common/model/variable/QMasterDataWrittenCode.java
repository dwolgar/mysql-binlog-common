package com.github.mysqlbinlog.common.model.variable;

public class QMasterDataWrittenCode extends StatusVariable {
	private static final long serialVersionUID = -1546781433147355795L;
	private final int value;

	public QMasterDataWrittenCode(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "QMasterDataWrittenCode [value=" + value + "]";
	}
}
