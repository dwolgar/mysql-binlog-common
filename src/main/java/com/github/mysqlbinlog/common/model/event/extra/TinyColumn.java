package com.github.mysqlbinlog.common.model.event.extra;

public final class TinyColumn extends Column {
	private static final long serialVersionUID = -1634843688155863232L;
	public static final int MIN_VALUE = -128;
	public static final int MAX_VALUE = 127;

	private final int value;

	private TinyColumn(int value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "TinyColumn [value=" + value + "]";
	}
}
