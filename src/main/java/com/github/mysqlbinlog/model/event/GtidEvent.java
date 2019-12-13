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

import javax.xml.bind.DatatypeConverter;

import com.github.mysql.utils.MysqlUtils;

public class GtidEvent extends BinlogEvent {
    private static final long serialVersionUID = 1665195309515506304L;

    private int flags;
    private byte[] sourceId;
    private long transactionId;
    private int logicalCockTimestampTypeCode;
    private long lastCommited;
    private long sequenceNumber;
    private long immidiateCommitTimestamp;
    private long originalCommitTimestamp;
    private long transactionLength;
    private int immidiateServerVersion;
    private int originalServerVersion;
    
    public GtidEvent() {
        
    }
    
    public GtidEvent(BinlogEventHeader header, byte[] rawData) {
        super(header, rawData);
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public byte[] getSourceId() {
        return sourceId;
    }

    public void setSourceId(byte[] sid) {
        this.sourceId = sid;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public int getLogicalCockTimestampTypeCode() {
        return logicalCockTimestampTypeCode;
    }

    public void setLogicalCockTimestampTypeCode(int logicalCockTimestampTypeCode) {
        this.logicalCockTimestampTypeCode = logicalCockTimestampTypeCode;
    }

    public long getLastCommited() {
        return lastCommited;
    }

    public void setLastCommited(long lastCommited) {
        this.lastCommited = lastCommited;
    }


    public long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public long getImmidiateCommitTimestamp() {
        return immidiateCommitTimestamp;
    }

    public void setImmidiateCommitTimestamp(long immidiateCommitTimestamp) {
        this.immidiateCommitTimestamp = immidiateCommitTimestamp;
    }

    public long getOriginalCommitTimestamp() {
        return originalCommitTimestamp;
    }

    public void setOriginalCommitTimestamp(long originalCommitTimestamp) {
        this.originalCommitTimestamp = originalCommitTimestamp;
    }

    public long getTransactionLength() {
        return transactionLength;
    }

    public void setTransactionLength(long transactionLength) {
        this.transactionLength = transactionLength;
    }

    public int getImmidiateServerVersion() {
        return immidiateServerVersion;
    }

    public void setImmidiateServerVersion(int immidiateServerVersion) {
        this.immidiateServerVersion = immidiateServerVersion;
    }

    public int getOriginalServerVersion() {
        return originalServerVersion;
    }

    public void setOriginalServerVersion(int originalServerVersion) {
        this.originalServerVersion = originalServerVersion;
    }

    @Override
    public String toString() {
        return "GtidEvent [flags=" + flags + ", sourceId="
                + DatatypeConverter.printHexBinary(sourceId) + ", transactionId(gno)=" + transactionId
                + ", logicalCockTimestampTypeCode="
                + logicalCockTimestampTypeCode + ", lastCommited="
                + lastCommited + ", sequenceNumber=" + sequenceNumber
                + ", immidiateCommitTimestamp=" + immidiateCommitTimestamp
                + ", originalCommitTimestamp=" + originalCommitTimestamp
                + ", transactionLength=" + transactionLength
                + ", immidiateServerVersion=" + immidiateServerVersion
                + ", originalServerVersion=" + originalServerVersion + "]";
    }
    
   
}
