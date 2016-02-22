package com.github.mysqlbinlog.common.model.event;


import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

import com.github.mysqlbinlog.common.model.event.extra.Row;

/**
 * WRITE_ROWS_EVENT
 *
 * Used for row-based binary logging. This event logs inserts of rows in a single table.
 */
public final class WriteRowsEvent extends RowEvent {
	private static final long serialVersionUID = 1L;
	private int extraInfoLength;
	private byte extraInfo[];
	private long columnCount;
	private BitSet usedColumns;
	private List<Row> rows;

	public WriteRowsEvent() {
	}

	public WriteRowsEvent(BinlogEventHeader header, byte[] rawData) {
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
		return "WriteRowsEventV2 [extraInfoLength=" + extraInfoLength
				+ ", extraInfo=" + Arrays.toString(extraInfo)
				+ ", columnCount=" + columnCount + ", usedColumns="
				+ usedColumns + ", rows=" + rows + "]";
	}
	
	
}
