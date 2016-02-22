package com.github.mysqlbinlog.common.model.event.extra;


import java.io.Serializable;
import java.util.List;

public class Row implements Serializable {
	private static final long serialVersionUID = -9169429176739667366L;

	private List<Column> columns;

	public Row() {}

	public Row(List<Column> columns) {
		this.columns = columns;
	}

	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return "Row [columns=" + columns + "]";
	}
}
