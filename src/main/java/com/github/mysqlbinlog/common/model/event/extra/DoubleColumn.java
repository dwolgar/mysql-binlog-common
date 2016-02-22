package com.github.mysqlbinlog.common.model.event.extra;

public final class DoubleColumn extends Column {
	private static final long serialVersionUID = -3292030373367000473L;
	private final double value;

	private DoubleColumn(double value) {
		this.value = value;
	}

	public Double getValue() {
		return this.value;
	}

	public static final DoubleColumn valueOf(double value) {
		return new DoubleColumn(value);
	}

	@Override
	public String toString() {
		return "DoubleColumn [value=" + value + "]";
	}
}
