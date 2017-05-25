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

public class GtidEvent extends BinlogEvent {
    private static final long serialVersionUID = 1665195309515506304L;
    private byte flags;
    private byte[] sourceId;
    private long transactionId;

    public GtidEvent() {
        
    }
    
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
