package com.github.mysqlbinlog.common.model.event.extra;

public final class LongLongColumn extends Column {
	private static final long serialVersionUID = 257255728789041973L;
	public static final long MIN_VALUE = Long.MIN_VALUE;
	public static final long MAX_VALUE = Long.MAX_VALUE;

	private final long value;

	private LongLongColumn(int type, long value) {
		super(type);
		this.value = value;
	}

	public Long getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "LongLongColumn [value=" + value + "]";
	}
}
