package com.github.mysqlbinlog.common.model.variable;

public class QSQLModeCode extends StatusVariable {
	private static final long serialVersionUID = -5188207999030982340L;
	private final long sqlMode;

	public QSQLModeCode(long sqlMode) {
		this.sqlMode = sqlMode;
	}

	public long getSqlMode() {
		return sqlMode;
	}

	@Override
	public String toString() {
		return "QSQLModeCode [sqlMode=" + sqlMode + "]";
	}
}
