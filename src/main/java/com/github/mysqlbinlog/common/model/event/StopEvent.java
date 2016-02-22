package com.github.mysqlbinlog.common.model.event;


/* 
 * STOP_EVENT
 *
 * Written when mysqld stops.
 */
public final class StopEvent extends BinlogEvent {
	private static final long serialVersionUID = 6503190230211068434L;

	public StopEvent() {}

	public StopEvent(BinlogEventHeader header, byte[] rawData) {
		super(header, rawData);
	}
}
