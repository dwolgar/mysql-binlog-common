package com.github.mysqlbinlog.common.model.variable;

public class QTableMapForUpdateCode extends StatusVariable {
	private static final long serialVersionUID = 6882715742517683829L;
	private final long tableMap;

	public QTableMapForUpdateCode(long tableMap) {
		this.tableMap = tableMap;
	}

	public long getTableMap() {
		return tableMap;
	}

	@Override
	public String toString() {
		return "QTableMapForUpdateCode [tableMap=" + tableMap + "]";
	}
}
