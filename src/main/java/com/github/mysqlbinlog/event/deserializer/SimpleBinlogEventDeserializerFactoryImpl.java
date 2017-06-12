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

import com.github.mysql.constant.MysqlConstants;
import com.github.mysqlbinlog.model.event.BinlogEventHeader;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({ "rawtypes", "serial" })
public class SimpleBinlogEventDeserializerFactoryImpl implements BinlogEventDeserializerFactory {
    private static final BinlogEventDeserializer<?> rawEventDeserializer = new RawBinglogEventDeserializer();
    private Map<Integer, BinlogEventDeserializer> deserializers;

    public SimpleBinlogEventDeserializerFactoryImpl() {
    }

    private void createDefaultDeserializers() {
        deserializers = new HashMap<Integer, BinlogEventDeserializer>() { 
            {
                put(MysqlConstants.QUERY_EVENT, new QueryEventDeserializer());
                put(MysqlConstants.ROTATE_EVENT, new RotateEventDeserializer());
                put(MysqlConstants.INTVAR_EVENT, new IntvarEventDeserializer());
                put(MysqlConstants.RAND_EVENT, new RandEventDeserializer());
                put(MysqlConstants.USER_VAR_EVENT, new UserVarEventDeserializer());
                put(MysqlConstants.FORMAT_DESCRIPTION_EVENT, new FormatDescriptionEventDeserializer());
                put(MysqlConstants.XID_EVENT, new XidEventDeserializer());
                put(MysqlConstants.TABLE_MAP_EVENT, new TableMapEventDeserializer());
                put(MysqlConstants.WRITE_ROWS_EVENT, new WriteRowsEventDeserializer(false));
                put(MysqlConstants.UPDATE_ROWS_EVENT, new UpdateRowsEventDeserializer(false));
                put(MysqlConstants.DELETE_ROWS_EVENT, new DeleteRowsEventDeserializer(false));
                put(MysqlConstants.INCIDENT_EVENT, new IncidentEventDeserializer());
                put(MysqlConstants.WRITE_ROWS_EVENT_V2, new WriteRowsEventDeserializer(true));
                put(MysqlConstants.UPDATE_ROWS_EVENT_V2, new UpdateRowsEventDeserializer(true));
                put(MysqlConstants.DELETE_ROWS_EVENT_V2, new DeleteRowsEventDeserializer(true));
                put(MysqlConstants.GTID_LOG_EVENT, new GtidEventDeserializer());
                put(MysqlConstants.STOP_EVENT, new StopEventDeserializer());
                put(MysqlConstants.PREVIOUS_GTIDS_LOG_EVENT, new PreviousGtidsEventDeserializer());
                put(MysqlConstants.ANONYMOUS_GTID_LOG_EVENT, new AnonymousGtidEventDeserializer());
            } 
        };
    }

    @Override
    public BinlogEventDeserializer<?> getBinlogEventDeserializer(BinlogEventHeader eventHeader) {
        if (this.deserializers == null) {
            createDefaultDeserializers();
        }

        BinlogEventDeserializer<?> deserializer = deserializers.get(eventHeader.getEventType());

        return deserializer == null ? SimpleBinlogEventDeserializerFactoryImpl.rawEventDeserializer : deserializer;
    }

}
