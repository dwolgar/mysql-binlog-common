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

import java.util.Arrays;


/* 
 *  FORMAT_DESCRIPTION_EVENT
 *
 *  A descriptor event that is written to the beginning of the each binary log file. This event is used as of MySQL 5.0; it supersedes START_EVENT_V3.
 */
public final class FormatDescriptionEvent extends BinlogEvent {
    private static final long serialVersionUID = -3994405484057507955L;

    private int binlogVersion;
    private String serverVersion;
    private long createTimestamp;
    private int headerLength;
    private byte[] eventTypes;

    public FormatDescriptionEvent() {
    }

    public FormatDescriptionEvent(BinlogEventHeader header, byte[] rawData) {
        super(header, rawData);
    }


    public int getBinlogVersion() {
        return binlogVersion;
    }
    
    public void setBinlogVersion(int binlogVersion) {
        this.binlogVersion = binlogVersion;
    }

    public String getServerVersion() {
        return serverVersion;
    }
    
    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public long getCreateTimestamp() {
        return createTimestamp;
    }
    
    public void setCreateTimestamp(long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public int getHeaderLength() {
        return headerLength;
    }
    
    public void setHeaderLength(int headerLength) {
        this.headerLength = headerLength;
    }

    public byte[] getEventTypes() {
        return eventTypes;
    }
    
    public void setEventTypes(byte[] eventTypes) {
        this.eventTypes = eventTypes;
    }

    @Override
    public String toString() {
        return "FormatDescriptionEvent [binlogVersion=" + binlogVersion
                + ", serverVersion=" + serverVersion + ", createTimestamp="
                + createTimestamp + ", headerLength=" + headerLength
                + ", eventTypes=" + Arrays.toString(eventTypes) + "]";
    }
}
