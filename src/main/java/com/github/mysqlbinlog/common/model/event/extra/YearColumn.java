package com.github.mysqlbinlog.common.model.event.extra;

public final class YearColumn extends Column {
	private static final long serialVersionUID = 7077374968080317676L;
	private final int value;

	private YearColumn(int value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "YearColumn [value=" + value + "]";
	}
}
