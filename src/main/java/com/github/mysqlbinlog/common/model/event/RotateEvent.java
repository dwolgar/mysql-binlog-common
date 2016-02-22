package com.github.mysqlbinlog.common.model.event;

/*
 * ROTATE_EVENT
 *
 * Written when mysqld switches to a new binary log file. 
 * This occurs when someone issues a FLUSH LOGS statement or the current binary log file becomes too large. 
 * The maximum size is determined by max_binlog_size.
 *
 */
public final class RotateEvent extends BinlogEvent {
	private static final long serialVersionUID = 2129997803902359868L;

	private long binlogPosition;
	private String binlogFileName;

	public RotateEvent() {
		
	}

	public RotateEvent(BinlogEventHeader header, byte[] rawData) {
		super(header, rawData);
	}

	public long getBinlogPosition() {
		return binlogPosition;
	}
	public void setBinlogPosition(long binlogPosition) {
		this.binlogPosition = binlogPosition;
	}

	public String getBinlogFileName() {
		return binlogFileName;
	}
	public void setBinlogFileName(String binlogFileName) {
		this.binlogFileName = binlogFileName;
	}

	@Override
	public String toString() {
		return "RotateEvent [binlogPosition=" + binlogPosition
				+ ", binlogFileName=" + binlogFileName + "]";
	}
}
