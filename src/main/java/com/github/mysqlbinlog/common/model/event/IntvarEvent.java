package com.github.mysqlbinlog.common.model.event;

import java.math.BigInteger;

/*
 * INTVAR_EVENT
 *
 * Written every time a statement uses an AUTO_INCREMENT column or the LAST_INSERT_ID() function; precedes other events for the statement. 
 * This is written only before a QUERY_EVENT and is not used with row-based logging. An INTVAR_EVENT is written with a "subtype" in the event data part:
 *
 *  INSERT_ID_EVENT indicates the value to use for an AUTO_INCREMENT column in the next statement.
 *
 *  LAST_INSERT_ID_EVENT indicates the value to use for the LAST_INSERT_ID() function in the next statement.
 */
public final class IntvarEvent extends BinlogEvent {
	private static final long serialVersionUID = -8777784212561872473L;

	private int type;
	private BigInteger value;

	public IntvarEvent() {
		
	}

	public IntvarEvent(BinlogEventHeader header, byte[] rawData) {
		super(header, rawData);
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public BigInteger getValue() {
		return value;
	}
	public void setValue(BigInteger value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "IntvarEvent [type=" + type + ", value=" + value + "]";
	}
}
