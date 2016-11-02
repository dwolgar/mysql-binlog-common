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
import com.github.mysqlbinlog.model.event.BinlogEvent;
import com.github.mysqlbinlog.model.event.BinlogEventHeader;
import com.github.mysqlbinlog.model.event.DeleteRowsEvent;
import com.github.mysqlbinlog.model.event.FormatDescriptionEvent;
import com.github.mysqlbinlog.model.event.GtidEvent;
import com.github.mysqlbinlog.model.event.IncidentEvent;
import com.github.mysqlbinlog.model.event.IntvarEvent;
import com.github.mysqlbinlog.model.event.QueryEvent;
import com.github.mysqlbinlog.model.event.RandEvent;
import com.github.mysqlbinlog.model.event.RawBinglogEvent;
import com.github.mysqlbinlog.model.event.RotateEvent;
import com.github.mysqlbinlog.model.event.StopEvent;
import com.github.mysqlbinlog.model.event.TableMapEvent;
import com.github.mysqlbinlog.model.event.UpdateRowsEvent;
import com.github.mysqlbinlog.model.event.UserVarEvent;
import com.github.mysqlbinlog.model.event.WriteRowsEvent;
import com.github.mysqlbinlog.model.event.XidEvent;

public class SimpleBinlogEventFactoryImpl implements BinlogEventFactory {

    @Override
    public BinlogEvent createBinlogEvent(BinlogEventHeader eventHeader, byte[] rawData) {
        BinlogEvent event = null;
        switch (eventHeader.getEventType()) {
        case MysqlConstants.QUERY_EVENT:
            event = new QueryEvent(eventHeader, rawData);
            break;
        case MysqlConstants.ROTATE_EVENT:
            event = new RotateEvent(eventHeader, rawData);
            break;
        case MysqlConstants.INTVAR_EVENT:
            event = new IntvarEvent(eventHeader, rawData);
            break;
        case MysqlConstants.RAND_EVENT:
            event = new RandEvent(eventHeader, rawData);
            break;
        case MysqlConstants.USER_VAR_EVENT:
            event = new UserVarEvent(eventHeader, rawData);
            break;
        case MysqlConstants.FORMAT_DESCRIPTION_EVENT:
            event = new FormatDescriptionEvent(eventHeader, rawData);
            break;
        case MysqlConstants.XID_EVENT:
            event = new XidEvent(eventHeader, rawData);
            break;
        case MysqlConstants.TABLE_MAP_EVENT:
            event = new TableMapEvent(eventHeader, rawData);
            break;
        case MysqlConstants.WRITE_ROWS_EVENT:
            event = new WriteRowsEvent(eventHeader, rawData);
            break;
        case MysqlConstants.UPDATE_ROWS_EVENT:
            event = new UpdateRowsEvent(eventHeader, rawData);
            break;
        case MysqlConstants.DELETE_ROWS_EVENT:
            event = new DeleteRowsEvent(eventHeader, rawData);
            break;
        case MysqlConstants.INCIDENT_EVENT:
            event = new IncidentEvent(eventHeader, rawData);
            break;
        case MysqlConstants.WRITE_ROWS_EVENT_V2:
            event = new WriteRowsEvent(eventHeader, rawData);
            break;
        case MysqlConstants.UPDATE_ROWS_EVENT_V2:
            event = new UpdateRowsEvent(eventHeader, rawData);
            break;
        case MysqlConstants.DELETE_ROWS_EVENT_V2:
            event = new DeleteRowsEvent(eventHeader, rawData);
            break;
        case MysqlConstants.GTID_LOG_EVENT:
            event = new GtidEvent(eventHeader, rawData);
            break;
        case MysqlConstants.STOP_EVENT:
            event = new StopEvent(eventHeader, rawData);
            break;

        case MysqlConstants.HEARTBEAT_LOG_EVENT:
        case MysqlConstants.UNKNOWN_EVENT:
        case MysqlConstants.START_EVENT_V3:
        case MysqlConstants.LOAD_EVENT:
        case MysqlConstants.SLAVE_EVENT:
        case MysqlConstants.CREATE_FILE_EVENT:
        case MysqlConstants.APPEND_BLOCK_EVENT:
        case MysqlConstants.EXEC_LOAD_EVENT:
        case MysqlConstants.DELETE_FILE_EVENT:
        case MysqlConstants.NEW_LOAD_EVENT:
        case MysqlConstants.BEGIN_LOAD_QUERY_EVENT:
        case MysqlConstants.EXECUTE_LOAD_QUERY_EVENT:
        case MysqlConstants.PRE_GA_WRITE_ROWS_EVENT:
        case MysqlConstants.PRE_GA_UPDATE_ROWS_EVENT:
        case MysqlConstants.PRE_GA_DELETE_ROWS_EVENT:
        case MysqlConstants.IGNORABLE_LOG_EVENT:
        case MysqlConstants.ROWS_QUERY_LOG_EVENT:
        case MysqlConstants.ANONYMOUS_GTID_LOG_EVENT:
        case MysqlConstants.PREVIOUS_GTIDS_LOG_EVENT:
        default:
            event = new RawBinglogEvent(eventHeader, rawData);

        }

        return event;
    }

}
