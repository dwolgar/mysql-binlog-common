package com.github.mysqlbinlog.common.model.event.extra;

import java.math.BigDecimal;

public final class DecimalColumn extends Column {
	private static final long serialVersionUID = 3991981843076262734L;
	
	private final BigDecimal value;
	private final int precision;
	private final int scale;

	private DecimalColumn(BigDecimal value, int precision, int scale) {
		this.value = value;
		this.scale = scale;
		this.precision = precision;
	}

	public BigDecimal getValue() {
		return this.value;
	}

	public int getPrecision() {
		return precision;
	}

	public int getScale() {
		return scale;
	}

	@Override
	public String toString() {
		return "DecimalColumn [value=" + value + ", precision=" + precision + ", scale=" + scale + "]";
	}

}
