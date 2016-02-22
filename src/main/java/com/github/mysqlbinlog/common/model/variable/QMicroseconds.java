package com.github.mysqlbinlog.common.model.variable;

public class QMicroseconds extends StatusVariable {
	private static final long serialVersionUID = 6825412529792413534L;
	private final int startUsec;

	public QMicroseconds(int startUsec) {
		this.startUsec = startUsec;
	}

	public int getStartUsec() {
		return startUsec;
	}

	@Override
	public String toString() {
		return "QMicroseconds [startUsec=" + startUsec + "]";
	}
}
