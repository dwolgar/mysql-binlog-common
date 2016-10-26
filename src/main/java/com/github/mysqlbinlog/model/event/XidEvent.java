/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.github.mysqlbinlog.model.event;


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
