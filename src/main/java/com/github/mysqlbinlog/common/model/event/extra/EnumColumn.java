package com.github.mysqlbinlog.common.model.event.extra;

public final class EnumColumn extends Column {
	private static final long serialVersionUID = 1349490898147876486L;
	private final int value;

	public EnumColumn(int type, int value) {
		super(type);
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "EnumColumn [value=" + value + "]";
	}
}
