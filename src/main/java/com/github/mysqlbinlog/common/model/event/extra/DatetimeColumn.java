package com.github.mysqlbinlog.common.model.event.extra;

import java.util.Date;

public final class DatetimeColumn extends Column {
	private static final long serialVersionUID = 9119310818026556288L;
	private final Date value;

	private DatetimeColumn(Date value) {
		this.value = value;
	}

	@Override
	public Date getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "Datetime2Column [value=" + value + "]";
	}
}
