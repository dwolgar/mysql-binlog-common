package com.github.mysqlbinlog.common.model.event.extra;

public final class StringColumn extends Column {
	private static final long serialVersionUID = 749371729285531226L;
	private final String value;

	private StringColumn(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "StringColumn [value=" + value + "]";
	}
}
