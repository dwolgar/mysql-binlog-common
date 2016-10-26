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

package com.github.mysqlbinlog.event.unmarshaller;

import java.util.HashMap;
import java.util.Map;

import com.github.mysql.constant.MysqlConstants;
import com.github.mysqlbinlog.model.event.BinlogEventHeader;

@SuppressWarnings({ "rawtypes", "serial" })
public class SimpleBinlogEventUnmarshallerFactoryImpl implements BinlogEventUnmarshallerFactory {
    
    private Map<Integer, BinlogEventUnmarshaller> unmarshallers;

    public SimpleBinlogEventUnmarshallerFactoryImpl() {
    }
    
    private void createDefaultUnmarshallers() {
        unmarshallers = new HashMap<Integer, BinlogEventUnmarshaller>() {{
            put(MysqlConstants.QUERY_EVENT, new QueryEventUnmarshaller());
            put(MysqlConstants.ROTATE_EVENT, new RotateEventUnmarshaller());
            put(MysqlConstants.INTVAR_EVENT, new IntvarEventUnmarshaller());
            put(MysqlConstants.RAND_EVENT, new RandEventUnmarshaller());
            put(MysqlConstants.USER_VAR_EVENT, new UserVarEventUnmarshaller());
            put(MysqlConstants.FORMAT_DESCRIPTION_EVENT, new FormatDescriptionEventUnmarshaller());
            put(MysqlConstants.XID_EVENT, new XidEventUnmarshaller());
            put(MysqlConstants.TABLE_MAP_EVENT, new TableMapEventUnmarshaller());
            put(MysqlConstants.WRITE_ROWS_EVENT, new WriteRowsEventUnmarshaller(false));
            put(MysqlConstants.UPDATE_ROWS_EVENT, new UpdateRowsEventUnmarshaller(false));
            put(MysqlConstants.DELETE_ROWS_EVENT, new DeleteRowsEventUnmarshaller(false));
            put(MysqlConstants.INCIDENT_EVENT, new IncidentEventUnmarshaller());
            put(MysqlConstants.WRITE_ROWS_EVENT_V2, new WriteRowsEventUnmarshaller(true));
            put(MysqlConstants.UPDATE_ROWS_EVENT_V2, new UpdateRowsEventUnmarshaller(true));
            put(MysqlConstants.DELETE_ROWS_EVENT_V2, new DeleteRowsEventUnmarshaller(true));
            put(MysqlConstants.GTID_LOG_EVENT, new GtidEventUnmarshaller());
            put(MysqlConstants.STOP_EVENT, new StopEventUnmarshaller());
        }};
    }

    @Override
    public BinlogEventUnmarshaller<?> getBinlogEventUnmarshaller(BinlogEventHeader eventHeader) {
        if (this.unmarshallers == null) {
            createDefaultUnmarshallers();
        }
        return unmarshallers.get(eventHeader.getEventType());
    }

}
