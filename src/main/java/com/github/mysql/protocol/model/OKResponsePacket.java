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

package com.github.mysql.protocol.model;


public class OKResponsePacket implements ResponsePacket {
    private static final long serialVersionUID = -1409055054761882713L;

    public static final byte HEADER = (byte)0x00;

    private Long affectedRows;
    private Long insertId;
    private int serverStatus;
    private int warningCount;
    private String message;

    public OKResponsePacket() {
        
    }
    
    @Override
    public String toString() {
        return "MysqlOKResponsePacket [affectedRows=" + affectedRows
                + ", insertId=" + insertId + ", serverStatus=" + serverStatus
                + ", warningCount=" + warningCount + ", message=" + message
                + "]";
    }

    public Long getAffectedRows() {
        return affectedRows;
    }
    
    public void setAffectedRows(Long affectedRows) {
        this.affectedRows = affectedRows;
    }

    public Long getInsertId() {
        return insertId;
    }
    
    public void setInsertId(Long insertId) {
        this.insertId = insertId;
    }

    public int getServerStatus() {
        return serverStatus;
    }
    
    public void setServerStatus(int serverStatus) {
        this.serverStatus = serverStatus;
    }

    public int getWarningCount() {
        return warningCount;
    }
    
    public void setWarningCount(int warningCount) {
        this.warningCount = warningCount;
    }

    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
