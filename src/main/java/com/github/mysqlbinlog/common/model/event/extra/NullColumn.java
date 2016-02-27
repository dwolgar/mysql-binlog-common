package com.github.mysqlbinlog.common.model.event.extra;

public final class NullColumn extends Column {
	private static final long serialVersionUID = -3548523546268990115L;
	private final int columnType;

	public NullColumn(int type, int columnType) {
		super(type);
		this.columnType = columnType;
	}

	public int getColumnType() {
		return this.columnType;
	}

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public String toString() {
		return "NullColumn [type=" + this.columnType + "]";
	}

}
