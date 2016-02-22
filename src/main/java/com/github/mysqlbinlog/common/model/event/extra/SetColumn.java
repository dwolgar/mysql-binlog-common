package com.github.mysqlbinlog.common.model.event.extra;

public final class SetColumn extends Column {
	private static final long serialVersionUID = -4284581552411985097L;
	private final long value;

	private SetColumn(long value) {
		this.value = value;
	}
	public Long getValue() {
		return this.value;
	}
	@Override
	public String toString() {
		return "SetColumn [value=" + value + "]";
	}
}
