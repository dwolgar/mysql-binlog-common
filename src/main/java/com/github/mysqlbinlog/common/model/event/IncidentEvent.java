package com.github.mysqlbinlog.common.model.event;

/*
 * INCIDENT_EVENT
 *
 * Used to log an out of the ordinary event that occurred on the master. It notifies the slave that something happened on the master that might cause data to be in an inconsistent state.
 */

public final class IncidentEvent extends BinlogEvent {
	private static final long serialVersionUID = 5692823047829346186L;

	private int incidentNumber;
	private int messageLength;
	private String message;

	public IncidentEvent() {
		
	}

	public IncidentEvent(BinlogEventHeader header, byte[] rawData) {
		super(header, rawData);
	}

	public int getIncidentNumber() {
		return incidentNumber;
	}
	public void setIncidentNumber(int incidentNumber) {
		this.incidentNumber = incidentNumber;
	}

	public int getMessageLength() {
		return messageLength;
	}
	public void setMessageLength(int messageLength) {
		this.messageLength = messageLength;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
