package com.github.mysqlbinlog.common.model.event.extra;

import java.util.Date;

public final class TimestampColumn extends Column {
	private static final long serialVersionUID = -6578645723394215001L;
	private final Date value;

	private TimestampColumn(java.sql.Timestamp value) {
		this.value = value;
	}

	public Date getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "TimestampColumn [value=" + value + "]";
	}
}
