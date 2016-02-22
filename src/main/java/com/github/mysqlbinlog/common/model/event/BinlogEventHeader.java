package com.github.mysqlbinlog.common.model.event;

import java.io.Serializable;

public class BinlogEventHeader implements Serializable {
	private static final long serialVersionUID = 259642975170644898L;

	//(MySQL 3.23)
    private long timestamp;
    private int eventType;
    private long serverId;
	private long eventLength;
    //(MySQL 4.0.2-4.1)
    private long nextPosition;
    private int flags;
    
    public BinlogEventHeader() {
    	
    }
    
    
    public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public int getEventType() {
		return eventType;
	}
	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public long getServerId() {
		return serverId;
	}
	public void setServerId(long serverId) {
		this.serverId = serverId;
	}

	public long getEventLength() {
		return eventLength;
	}
	public void setEventLength(long eventLength) {
		this.eventLength = eventLength;
	}

	public long getNextPosition() {
		return nextPosition;
	}
	public void setNextPosition(long nextPosition) {
		this.nextPosition = nextPosition;
	}

	public int getFlags() {
		return flags;
	}
	public void setFlags(int flags) {
		this.flags = flags;
	}


	@Override
	public String toString() {
		return "BinlogEventHeader [timestamp=" + timestamp + ", eventType="
				+ eventType + ", serverId=" + serverId + ", eventLength="
				+ eventLength + ", nextPosition=" + nextPosition + ", flags="
				+ flags + "]";
	}
}
