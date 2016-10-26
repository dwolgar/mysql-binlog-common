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

import com.github.mysqlbinlog.event.checksum.MysqlChecksum;
import com.github.mysqlbinlog.model.event.TableMapEvent;

public class SimpleBinlogUnmarshallerContextImpl implements BinlogUnmarshallerContext {
    private final Map<Long, TableMapEvent> tableMapEvents;
    private MysqlChecksum checksum;
    
    public SimpleBinlogUnmarshallerContextImpl() {
        tableMapEvents = new HashMap<Long, TableMapEvent>();
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
}
