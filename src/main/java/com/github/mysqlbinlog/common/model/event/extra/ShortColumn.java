package com.github.mysqlbinlog.common.model.event.extra;

public final class ShortColumn extends Column {
	private static final long serialVersionUID = -775325593580046117L;
	public static final int MIN_VALUE = Short.MIN_VALUE;
	public static final int MAX_VALUE = Short.MAX_VALUE;

	private final int value;

	private ShortColumn(int value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "ShortColumn [value=" + value + "]";
	}
}
