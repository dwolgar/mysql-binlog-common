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

public class EOFResponsePacket implements ResponsePacket {
    private static final long serialVersionUID = -412332492116642260L;

    public static final byte HEADER = (byte)0xFE;
    
    private int warningCount;
    private int serverStatus;

    public EOFResponsePacket() {
        
    }

    public int getWarningCount() {
        return warningCount;
    }
    
    public void setWarningCount(int warningCount) {
        this.warningCount = warningCount;
    }

    public int getServerStatus() {
        return serverStatus;
    }
    
    public void setServerStatus(int serverStatus) {
        this.serverStatus = serverStatus;
    }

    @Override
    public String toString() {
        return "MysqlEOFResponsePacket [warningCount=" + warningCount
                + ", serverStatus=" + serverStatus + "]";
    }
}
