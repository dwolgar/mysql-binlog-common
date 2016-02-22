package com.github.mysqlbinlog.common.model.event;

public abstract class RowEvent extends BinlogEvent {
	private static final long serialVersionUID = 1033129061104761546L;
	protected long tableId;
	protected int reserved;

	protected String databaseName;
	protected String tableName;
	
	public RowEvent() {
	}

	public RowEvent(BinlogEventHeader header, byte[] rawData) {
		super(header, rawData);
	}
	
	public long getTableId() {
		return tableId;
	}
	public void setTableId(long tableId) {
		this.tableId = tableId;
	}

	public int getReserved() {
		return reserved;
	}
	public void setReserved(int reserved) {
		this.reserved = reserved;
	}
	
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}
