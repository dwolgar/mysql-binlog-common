package com.github.mysqlbinlog.common.model.event;


import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

import com.github.mysqlbinlog.common.model.event.extra.Pair;
import com.github.mysqlbinlog.common.model.event.extra.Row;

/**
 * UPDATE_ROWS_EVENT
 *
 * Used for row-based binary logging. This event logs updates of rows in a single table.
 */
public final class UpdateRowsEvent extends RowEvent {
	private static final long serialVersionUID = -5780778688824330051L;
	
	private int extraInfoLength;
	private byte extraInfo[];
	private long columnCount;
	private BitSet usedColumnsBefore;
	private BitSet usedColumnsAfter;
	private List<Pair<Row>> rows;

	public UpdateRowsEvent() {
	}

	public UpdateRowsEvent(BinlogEventHeader header, byte[] rawData) {
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

	public BitSet getUsedColumnsBefore() {
		return usedColumnsBefore;
	}
	public void setUsedColumnsBefore(BitSet usedColumnsBefore) {
		this.usedColumnsBefore = usedColumnsBefore;
	}

	public BitSet getUsedColumnsAfter() {
		return usedColumnsAfter;
	}
	public void setUsedColumnsAfter(BitSet usedColumnsAfter) {
		this.usedColumnsAfter = usedColumnsAfter;
	}

	public List<Pair<Row>> getRows() {
		return rows;
	}
	public void setRows(List<Pair<Row>> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "UpdateRowsEvent [extraInfoLength=" + extraInfoLength
				+ ", extraInfo=" + Arrays.toString(extraInfo)
				+ ", columnCount=" + columnCount + ", usedColumnsBefore="
				+ usedColumnsBefore + ", usedColumnsAfter=" + usedColumnsAfter
				+ ", rows=" + rows + "]";
	}
}
