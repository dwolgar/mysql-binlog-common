package com.github.mysqlbinlog.common.model.event.extra;

import java.util.Date;

public final class TimeColumn extends Column {
	private static final long serialVersionUID = 2730289197270466966L;
	private final Date value;

	private TimeColumn(Date value) {
		this.value = value;
	}

	public Date getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "TimeColumn [value=" + value + "]";
	}
}
