package com.github.mysqlbinlog.common.model.event;

import java.util.Arrays;

public class GtidEvent extends BinlogEvent {
	private static final long serialVersionUID = 1665195309515506304L;
	private byte flags;
	private byte[] sourceId;
	private long transactionId;

	public GtidEvent(BinlogEventHeader header, byte[] rawData) {
		super(header, rawData);
	}

	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public void setSourceId(byte[] sourceId) {
		this.sourceId = sourceId;
	}
	public byte[] getSourceId() {
		return sourceId;
	}

	public byte getFlags() {
		return flags;
	}
	public void setFlags(byte flags) {
		this.flags = flags;
	}
	
	@Override
	public String toString() {
		return "GtidEvent [flags=" + flags + ", sourceId="
				+ Arrays.toString(sourceId) + ", transactionId="
				+ transactionId + "]";
	}
}
