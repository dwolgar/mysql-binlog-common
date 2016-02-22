package com.github.mysqlbinlog.common.model.event;


/**
* XID_EVENT
*
* Generated for a commit of a transaction that modifies one or more tables of an XA-capable storage engine. 
* Normal transactions are implemented by sending a QUERY_EVENT containing a BEGIN statement and a QUERY_EVENT containing a COMMIT statement 
* (or a ROLLBACK statement if the transaction is rolled back). 
*/
public final class XidEvent extends BinlogEvent {
	private static final long serialVersionUID = 7450729555051563514L;

	private long xid;

	public XidEvent() {}

	public XidEvent(BinlogEventHeader header, byte[] rawData) {
		super(header, rawData);
	}

	public long getXid() {
		return xid;
	}
	public void setXid(long xid) {
		this.xid = xid;
	}

	@Override
	public String toString() {
		return "XidEvent [xid=" + xid + "]";
	}
}
