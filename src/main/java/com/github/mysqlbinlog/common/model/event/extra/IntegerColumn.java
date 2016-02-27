package com.github.mysqlbinlog.common.model.event.extra;

public final class LongColumn extends Column {
	private static final long serialVersionUID = -4325613881837023445L;
	public static final int MIN_VALUE = Integer.MIN_VALUE;
	public static final int MAX_VALUE = Integer.MAX_VALUE;

	private final int value;

	private LongColumn(int value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "LongColumn [value=" + value + "]";
	}
}
