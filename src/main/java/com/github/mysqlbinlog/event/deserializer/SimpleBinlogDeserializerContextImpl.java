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


import com.github.mysqlbinlog.event.checksum.MysqlChecksum;
import com.github.mysqlbinlog.model.event.TableMapEvent;
import com.github.mysqlbinlog.model.event.extra.ColumnExtraData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleBinlogDeserializerContextImpl implements BinlogDeserializerContext {
    private final Map<Long, TableMapEvent> tableMapEvents;
    private final Map<String, List<ColumnExtraData>> columnExtraDataMap;
    private MysqlChecksum checksum;
    
    public SimpleBinlogDeserializerContextImpl() {
        tableMapEvents   = new HashMap<Long, TableMapEvent>();
        columnExtraDataMap = new HashMap<String, List<ColumnExtraData>>();
    }

    @Override
    public TableMapEvent getTableMapEvent(long tableId) {
        return tableMapEvents.get(tableId);
    }

    @Override
    public void setTableMapEvent(TableMapEvent event) {
        tableMapEvents.put(event.getTableId(), event);
    }

    @Override
    public MysqlChecksum getChecksum() {
        return this.checksum;
    }

    public void setChecksum(MysqlChecksum checksum) {
        this.checksum = checksum;
    }

    @Override
    public List<ColumnExtraData> getColumnExtra(String databaseName, String tableName) {
        return this.columnExtraDataMap.get(databaseName + "." + tableName);
    }
    
    public void addColumnExtra(String databaseName, String tableName, List<ColumnExtraData> items) {
        this.columnExtraDataMap.put(databaseName + "." + tableName, items);
    }
}
