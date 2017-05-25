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

public class InitDBCmdPacket implements CmdPacket {
    private final String schema;
    
    public InitDBCmdPacket(String schema) {
        this.schema = schema;
    }

    @Override
    public byte[] getBody() throws IOException {
        MysqlBinlogByteArrayOutputStream os = new MysqlBinlogByteArrayOutputStream(new ByteArrayOutputStream());
        
        os.writeInteger(MysqlConstants.COM_INIT_DB, 1);
        os.writeString(this.schema);
        os.close();
        
        return ((ByteArrayOutputStream)(os.getOutputStream())).toByteArray();
    }

    public String getSchema() {
        return schema;
    }

}
