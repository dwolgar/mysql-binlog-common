package com.github.mysqlbinlog.common.model.event;

import java.io.Serializable;

public abstract class BinlogEvent implements Serializable {
	private static final long serialVersionUID = -9088134799288816751L;

	private BinlogEventHeader header;
	private byte[] rawData;
	
	public BinlogEvent() {
	}

	public BinlogEvent(BinlogEventHeader header, byte[] rawData) {
		this.header = header;
		this.rawData = rawData;
	}

	public BinlogEventHeader getHeader() {
		return header;
	}
	public void setHeader(BinlogEventHeader header) {
		this.header = header;
	}

	public byte[] getRawData() {
		return rawData;
	}
	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
	}

	@Override
	public String toString() {
		return "BinlogEvent [header=" + header + "]";
	}
}
