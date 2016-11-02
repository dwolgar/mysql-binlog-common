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


import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.github.mysql.constant.MysqlConstants;
import com.github.mysql.io.MysqlBinlogByteArrayInputStream;
import com.github.mysqlbinlog.model.event.BinlogEvent;
import com.github.mysqlbinlog.model.event.TableMapEvent;
import com.github.mysqlbinlog.model.event.extra.Metadata;

public class TableMapEventDeserializer implements BinlogEventDeserializer<TableMapEvent> {

    public BinlogEvent unmarshal(TableMapEvent event, MysqlBinlogByteArrayInputStream is, BinlogDeserializerContext context) throws IOException {
        
        event.setTableId(is.readLong(6, true));
        event.setReserved(is.readInt(2, true));
        
        int dbNameLength = is.readInt(1, true);
        event.setDatabaseName(is.readNullTerminatedString());
        int tableNameLength = is.readInt(1, true);
        event.setTableName(is.readNullTerminatedString());
        
        event.setColumnCount(is.readMysqlPackedNumber().longValue());
        event.setColumnTypes(is.read((int)event.getColumnCount()));
        event.setColumnMetadataCount(is.readMysqlPackedNumber().longValue());
        event.setColumnMetadata(readMetadata(event.getColumnTypes(), is.read((int)event.getColumnMetadataCount())));
        event.setColumnNullabilities(is.readBitSet((int)event.getColumnCount(), true));
        
        context.setTableMapEvent(event);
        
        return event;
    }
    
    private static Metadata readMetadata(byte[] type, byte[] data) throws IOException {
        final int[] metadata = new int[type.length];
        final MysqlBinlogByteArrayInputStream is = new MysqlBinlogByteArrayInputStream(new ByteArrayInputStream(data));
        for (int i = 0; i < type.length; i++) {
            switch (type[i] & 0xFF) {
            case MysqlConstants.TYPE_FLOAT:
            case MysqlConstants.TYPE_DOUBLE:
            case MysqlConstants.TYPE_TINY_BLOB:
            case MysqlConstants.TYPE_BLOB:
            case MysqlConstants.TYPE_MEDIUM_BLOB:
            case MysqlConstants.TYPE_LONG_BLOB:
                metadata[i] = is.readInt(1, true);
                break;
            case MysqlConstants.TYPE_BIT:
            case MysqlConstants.TYPE_VARCHAR:
            case MysqlConstants.TYPE_NEWDECIMAL:
                metadata[i] = is.readInt(2, true); 
                break;
            case MysqlConstants.TYPE_SET:
            case MysqlConstants.TYPE_ENUM:
            case MysqlConstants.TYPE_STRING:
                metadata[i] = is.readInt(2, false); // Big-endian
                break;
            case MysqlConstants.TYPE_TIME2:
            case MysqlConstants.TYPE_DATETIME2:
            case MysqlConstants.TYPE_TIMESTAMP2:
                metadata[i] = is.readInt(1, true);
                break;
            default:
                metadata[i] = 0;
            }
        }
        is.close();
        return new Metadata(type, metadata);
    }

}
