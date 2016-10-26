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

public class GreetingResponsePacket implements ResponsePacket {
    private static final long serialVersionUID = -4145886236491517018L;
    
    private int protocolVersion;
    private String serverVersion;
    private long threadId;
    private String scramble;
    private int serverCapabilities;
    private int serverCollation;
    private int serverStatus;
    private String pluginProvidedData;
    
    public GreetingResponsePacket() {
        
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public long getThreadId() {
        return threadId;
    }

    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public String getScramble() {
        return scramble;
    }

    public void setScramble(String scramble) {
        this.scramble = scramble;
    }

    public int getServerCapabilities() {
        return serverCapabilities;
    }

    public void setServerCapabilities(int serverCapabilities) {
        this.serverCapabilities = serverCapabilities;
    }

    public int getServerCollation() {
        return serverCollation;
    }

    public void setServerCollation(int serverCollation) {
        this.serverCollation = serverCollation;
    }

    public int getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(int serverStatus) {
        this.serverStatus = serverStatus;
    }

    public String getPluginProvidedData() {
        return pluginProvidedData;
    }

    public void setPluginProvidedData(String pluginProvidedData) {
        this.pluginProvidedData = pluginProvidedData;
    }

    @Override
    public String toString() {
        return "MysqlGreetingResponsePacket [protocolVersion="
                + protocolVersion + ", serverVersion=" + serverVersion
                + ", threadId=" + threadId + ", scramble=" + scramble
                + ", serverCapabilities=" + serverCapabilities
                + ", serverCollation=" + serverCollation + ", serverStatus="
                + serverStatus + ", pluginProvidedData=" + pluginProvidedData
                + "]";
    }

    
}
