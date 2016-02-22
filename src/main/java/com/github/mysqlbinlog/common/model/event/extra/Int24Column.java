package com.github.mysqlbinlog.common.model.event.extra;

public final class Int24Column extends Column {
	private static final long serialVersionUID = -1752999288892177047L;
	public static final int MIN_VALUE = -8388608;
	public static final int MAX_VALUE = 8388607;

	private final int value;

	private Int24Column(int value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "Int24Column [value=" + value + "]";
	}
}
