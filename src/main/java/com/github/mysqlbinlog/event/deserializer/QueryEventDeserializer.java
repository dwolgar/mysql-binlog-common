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
import java.util.ArrayList;
import java.util.List;

import com.github.mysql.constant.MysqlConstants;
import com.github.mysql.io.MysqlBinlogByteArrayInputStream;
import com.github.mysqlbinlog.model.event.BinlogEvent;
import com.github.mysqlbinlog.model.event.QueryEvent;
import com.github.mysqlbinlog.model.variable.QAutoIncrement;
import com.github.mysqlbinlog.model.variable.QCatalogCode;
import com.github.mysqlbinlog.model.variable.QCatalogNzCode;
import com.github.mysqlbinlog.model.variable.QCharsetCode;
import com.github.mysqlbinlog.model.variable.QCharsetDatabaseCode;
import com.github.mysqlbinlog.model.variable.QFlags2Code;
import com.github.mysqlbinlog.model.variable.QInvoker;
import com.github.mysqlbinlog.model.variable.QLcTimeNamesCode;
import com.github.mysqlbinlog.model.variable.QMasterDataWrittenCode;
import com.github.mysqlbinlog.model.variable.QMicroseconds;
import com.github.mysqlbinlog.model.variable.QSQLModeCode;
import com.github.mysqlbinlog.model.variable.QTableMapForUpdateCode;
import com.github.mysqlbinlog.model.variable.QTimeZoneCode;
import com.github.mysqlbinlog.model.variable.QUpdatedDBNames;
import com.github.mysqlbinlog.model.variable.StatusVariable;

public class QueryEventDeserializer implements BinlogEventDeserializer<QueryEvent> {

    public BinlogEvent deserialize(QueryEvent event, MysqlBinlogByteArrayInputStream is, BinlogDeserializerContext context) throws IOException {
        
        event.setThreadId(is.readLong(4, true));
        event.setElapsedTime(is.readLong(4, true));

        //TODO: DB name length (not used?)
        is.readInt(1, true);
        
        event.setErrorCode(is.readInt(2, true));
        
        int statusVarLength = is.readInt(2, true);
        
        event.setStatusVariables(parseStatusVariables(is.read(statusVarLength)));
        
        event.setDatabaseName(is.readNullTerminatedString());
        event.setSql(is.readString(is.available()));
        
        
        return event;
    }

    private  List<StatusVariable> parseStatusVariables(byte[] data) throws IOException {
        final List<StatusVariable> list = new ArrayList<StatusVariable>();
        final MysqlBinlogByteArrayInputStream is = new MysqlBinlogByteArrayInputStream(new ByteArrayInputStream(data));
        
        boolean stop = false;
        while(!stop && is.available() > 0) {
            final int type = is.readInt(1, true);
            switch (type) {
            case MysqlConstants.Q_AUTO_INCREMENT:
                list.add( new QAutoIncrement(is.readInt(2,  true), is.readInt(2, true)) );
                break;
            case MysqlConstants.Q_CATALOG_CODE:
                is.readInt(1, true);
                list.add(new QCatalogCode(is.readNullTerminatedString()));
                break;
            case MysqlConstants.Q_CATALOG_NZ_CODE:
                final int length = is.readInt(1, true);
                list.add(new QCatalogNzCode(is.readString(length)));
                break;
            case MysqlConstants.Q_CHARSET_CODE:
                final int characterSetClient = is.readInt(2, true);
                final int collationConnection = is.readInt(2, true);
                final int collationServer = is.readInt(2, true);
                list.add(new QCharsetCode(characterSetClient, collationConnection, collationServer));
                break;
            case MysqlConstants.Q_CHARSET_DATABASE_CODE:
                final int collationDatabase = is.readInt(2, true);
                list.add(new QCharsetDatabaseCode(collationDatabase));
                break;
            case MysqlConstants.Q_FLAGS2_CODE:
                list.add(new QFlags2Code(is.readInt(4, true)));
                break;
            case MysqlConstants.Q_LC_TIME_NAMES_CODE:
                list.add(new QLcTimeNamesCode(is.readInt(2, true)));
                break;
            case MysqlConstants.Q_SQL_MODE_CODE:
                list.add(new QSQLModeCode(is.readLong(8, true)));
                break;
            case MysqlConstants.Q_TABLE_MAP_FOR_UPDATE_CODE:
                list.add(new QTableMapForUpdateCode(is.readLong(8, true)));
                break;
            case MysqlConstants.Q_TIME_ZONE_CODE:
                list.add(new QTimeZoneCode(is.readString(is.readInt(1, true))));
                break;
            case MysqlConstants.Q_MASTER_DATA_WRITTEN_CODE:
                list.add(new QMasterDataWrittenCode(is.readInt(4, true)));                  
                break;
            case MysqlConstants.Q_INVOKER:
                final int userLength = is.readInt(1, true);
                final String user = is.readString(userLength);
                final int hostLength = is.readInt(1, true);
                final String host = is.readString(hostLength);
                list.add(new QInvoker(user, host));
                break;
            case MysqlConstants.Q_UPDATED_DB_NAMES:
                int accessedDbCount = is.readInt(1, true);
                String accessedDbs[] = null;
                if (accessedDbCount > MysqlConstants.MAX_DBS_IN_EVENT_MTS) {
                    accessedDbCount = MysqlConstants.OVER_MAX_DBS_IN_EVENT_MTS;
                } else {
                    accessedDbs = new String[accessedDbCount];
                    for (int i = 0; i < accessedDbCount; i++) {
                        accessedDbs[i] = is.readNullTerminatedString();
                    }
                }
                list.add(new QUpdatedDBNames(accessedDbCount, accessedDbs));
                break;
            case MysqlConstants.Q_MICROSECONDS:
                list.add(new QMicroseconds(is.readInt(3, true)));
                break;
            default:
                stop = true;
                break;
            }
        }
        
        is.close();
        
        return list;
    }
}
