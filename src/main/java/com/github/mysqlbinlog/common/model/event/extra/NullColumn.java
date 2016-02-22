package com.github.mysqlbinlog.common.model.event.extra;

public final class NullColumn extends Column {
	private static final long serialVersionUID = -3548523546268990115L;
	private final int type;

	private NullColumn(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public String toString() {
		return "NullColumn [type=" + type + "]";
	}

}
