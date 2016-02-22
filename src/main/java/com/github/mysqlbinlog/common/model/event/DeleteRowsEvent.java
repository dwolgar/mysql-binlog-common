package com.github.mysqlbinlog.common.model.event;


import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

import com.github.mysqlbinlog.common.model.event.extra.Row;


/**
 * DELETE_ROWS_EVENT
 *
 * Used for row-based binary logging. This event logs deletions of rows in a single table.
 */
public final class DeleteRowsEvent extends RowEvent {
	private static final long serialVersionUID = -5469591796507754469L;

	private int extraInfoLength;
	private byte extraInfo[];
	private long columnCount;
	private BitSet usedColumns;
	private List<Row> rows;

	public DeleteRowsEvent() {}

	public DeleteRowsEvent(BinlogEventHeader header, byte[] rawData) {
		super(header, rawData);
	}

	public int getExtraInfoLength() {
		return extraInfoLength;
	}
	public void setExtraInfoLength(int extraInfoLength) {
		this.extraInfoLength = extraInfoLength;
	}

	public byte[] getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(byte[] extraInfo) {
		this.extraInfo = extraInfo;
	}

	public long getColumnCount() {
		return columnCount;
	}
	public void setColumnCount(long columnCount) {
		this.columnCount = columnCount;
	}

	public BitSet getUsedColumns() {
		return usedColumns;
	}
	public void setUsedColumns(BitSet usedColumns) {
		this.usedColumns = usedColumns;
	}

	public List<Row> getRows() {
		return rows;
	}
	public void setRows(List<Row> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "DeleteRowsEvent [extraInfoLength=" + extraInfoLength
				+ ", extraInfo=" + Arrays.toString(extraInfo)
				+ ", columnCount=" + columnCount + ", usedColumns="
				+ usedColumns + ", rows=" + rows + "]";
	}
}
