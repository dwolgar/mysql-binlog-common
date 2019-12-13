/*
 *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
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

/*
 *  GTIDEVENT
 *    
 *    Body for Gtid_event
 * Name                                Format              Description
 * GTID_FLAGS                          1 byte              00000001 = Transaction may have changes logged with SBR. In 5.6, 5.7.0-5.7.18, and 8.0.0-8.0.1, this flag is always set. Starting in 5.7.19 and 8.0.2, this flag is cleared if the transaction only contains row events. It is set if any part of the transaction is written in statement format.
 * SID                                 16 byte sequence    UUID representing the SID
 * GNO                                 8 byte integer      Group number, second component of GTID.
 * logical clock timestamp typecode    1 byte integer      The type of logical timestamp used in the logical clock fields.
 * last_committed                      8 byte integer      Store the transaction's commit parent sequence_number
 * sequence_number                     8 byte integer      The transaction's logical timestamp assigned at prepare phase
 * immediate_commit_timestamp          7 byte integer      Timestamp of commit on the immediate master
 * original_commit_timestamp           7 byte integer      Timestamp of commit on the originating master
 * transaction_length                  1 to 9 byte integer // See net_length_size(ulonglong num)   The packed transaction's length in bytes, including the Gtid
 * immediate_server_version            4 byte integer      Server version of the immediate server
 * original_server_version             4 byte integer      Version of the server where the transaction was originally executed
 *   
 */
public class AnonymousGtidEvent extends BinlogEvent {
    private static final long serialVersionUID = 2485262423513374073L;
    
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
    

    public AnonymousGtidEvent() {

    }

    public AnonymousGtidEvent(BinlogEventHeader header, byte[] rawData) {
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
        return "AnonymousGtidEvent [flags=" + flags + ", sourceId="
                + DatatypeConverter.printHexBinary(sourceId) + ", transactionId(gno)=" + transactionId
                + ", logicalCockTimestampTypeCode="
                + logicalCockTimestampTypeCode + ", lastCommited="
                + lastCommited + ", sequenceNumber=" + sequenceNumber
                + ", immidiateCommitTimestamp=" +  immidiateCommitTimestamp
                + ", originalCommitTimestamp=" + originalCommitTimestamp
                + ", transactionLength=" + transactionLength
                + ", immidiateServerVersion=" + immidiateServerVersion
                + ", originalServerVersion=" + originalServerVersion + "]";
    }
    
   
}
