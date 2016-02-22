package com.github.mysqlbinlog.common.model.variable;

public class QCharsetDatabaseCode extends StatusVariable {
	private static final long serialVersionUID = 4824253711827366340L;
	private final int collationDatabase;

	public QCharsetDatabaseCode(int collationDatabase) {
		this.collationDatabase = collationDatabase;
	}

	public int getCollationDatabase() {
		return collationDatabase;
	}

	@Override
	public String toString() {
		return "QCharsetDatabaseCode [collationDatabase=" + collationDatabase
				+ "]";
	}
}
