package com.github.mysqlbinlog.common.model.variable;

public class QTimeZoneCode extends StatusVariable {
	private static final long serialVersionUID = 4090142522267686378L;
	private final String timeZone;

	public QTimeZoneCode(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getTimeZone() {
		return timeZone;
	}

	@Override
	public String toString() {
		return "QTimeZoneCode [timeZone=" + timeZone + "]";
	}
}
