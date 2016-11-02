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

package com.github.mysqlbinlog.event.deserializer;

import java.io.IOException;

import com.github.mysql.io.MysqlBinlogByteArrayInputStream;
import com.github.mysql.protocol.model.OKResponsePacket;
import com.github.mysqlbinlog.model.event.BinlogEventHeader;

public class SimpleBinlogEventHeaderDeserializerImpl implements BinlogEventHeaderDeserializer {

    public BinlogEventHeader unmarshal(MysqlBinlogByteArrayInputStream is) throws IOException {
        BinlogEventHeader header = new BinlogEventHeader();
        
        int OK = is.readInt(1, true);
        if (OK != OKResponsePacket.HEADER) {
            throw new RuntimeException("Invalid BinLog Event Header");
        }
        
        header.setTimestamp(is.readLong(4, true) * 1000l);
        header.setEventType(is.readInt(1, true));
        header.setServerId(is.readLong(4, true));
        header.setEventLength(is.readLong(4, true));
        header.setNextPosition(is.readLong(4, true));
        header.setFlags(is.readInt(2, true));
        
        return header;
    }

}
