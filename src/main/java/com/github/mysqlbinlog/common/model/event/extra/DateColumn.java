package com.github.mysqlbinlog.common.model.event.extra;

import java.util.Date;

public final class DateColumn extends Column {
	private static final long serialVersionUID = 4577403839374409200L;
	private final Date value;

	private DateColumn(Date value) {
		this.value = value;
	}
	
	@Override
	public Date getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return "DateColumn [value=" + value + "]";
	}

}
