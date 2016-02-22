package com.github.mysqlbinlog.common.model.event.extra;

public final class FloatColumn extends Column {
	private static final long serialVersionUID = -1484429414871149683L;
	private final float value;

	private FloatColumn(float value) {
		this.value = value;
	}

	public Float getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return "FloatColumn [value=" + value + "]";
	}
}