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

import com.github.mysql.constant.MysqlConstants;
import com.github.mysql.io.MysqlBinlogByteArrayOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class BinlogDumpCmdPacket implements CmdPacket {
    private final long serverId;
    private final int binlogFlag;
    private final long binlogPosition;
    private final String binlogFileName;
    
    public BinlogDumpCmdPacket(long serverId, int binlogFlag, long binlogPosition, String binlogFileName) {
        this.serverId = serverId;
        this.binlogFlag = binlogFlag;
        this.binlogPosition = binlogPosition;
        this.binlogFileName = binlogFileName; 
    }


    @Override
    public byte[] getBody() throws IOException {
        MysqlBinlogByteArrayOutputStream os = new MysqlBinlogByteArrayOutputStream(new ByteArrayOutputStream());
        
        os.writeInteger(MysqlConstants.COM_BINLOG_DUMP, 1);
        os.writeLong(this.binlogPosition, 4);
        os.writeInteger(this.getBinlogFlag(), 2);
        os.writeLong(this.serverId, 4);
        os.writeString(this.binlogFileName);
        os.close();
        
        return ((ByteArrayOutputStream)(os.getOutputStream())).toByteArray();
    }


    public long getServerId() {
        return serverId;
    }
    
    public int getBinlogFlag() {
        return binlogFlag;
    }

    public long getBinlogPosition() {
        return binlogPosition;
    }

    public String getBinlogFileName() {
        return binlogFileName;
    }

}
