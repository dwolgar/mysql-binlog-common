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

/*
 * INCIDENT_EVENT
 *
 * Used to log an out of the ordinary event that occurred on the master. 
 * It notifies the slave that something happened on the master that might cause data to be in an inconsistent state.
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
