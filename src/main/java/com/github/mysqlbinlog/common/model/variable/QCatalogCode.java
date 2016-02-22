package com.github.mysqlbinlog.common.model.variable;

public class QCatalogCode extends StatusVariable {
	private static final long serialVersionUID = 4775655802075523104L;
	private final String catalogName;

	public QCatalogCode(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getCatalogName() {
		return catalogName;
	}
	
	@Override
	public String toString() {
		return "QCatalogCode [catalogName=" + catalogName + "]";
	}
}
