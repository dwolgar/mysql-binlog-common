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

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.github.mysql.io.MysqlBinlogByteArrayInputStream;
import com.github.mysqlbinlog.model.event.BinlogEvent;
import com.github.mysqlbinlog.model.event.TableMapEvent;
import com.github.mysqlbinlog.model.event.UpdateRowsEvent;
import com.github.mysqlbinlog.model.event.extra.Pair;
import com.github.mysqlbinlog.model.event.extra.Row;

public class UpdateRowsEventUnmarshaller extends AbstractRowEventUnmarshaller<UpdateRowsEvent> {
    private boolean version2;
    
    public UpdateRowsEventUnmarshaller() {
        super();
    }
    
    public UpdateRowsEventUnmarshaller(boolean version2) {
        super();
        this.setVersion2(version2);
    }


    @Override
    public BinlogEvent unmarshal(UpdateRowsEvent event, MysqlBinlogByteArrayInputStream is, BinlogUnmarshallerContext context) throws IOException {

        final long tableId = is.readLong(6, true);
        final TableMapEvent tme = context.getTableMapEvent(tableId);
        
        
        event.setDatabaseName(tme.getDatabaseName());
        event.setTableName(tme.getTableName());

        event.setTableId(tableId);
        event.setReserved(is.readInt(2, true));
        
        if (this.isVersion2()) {
            event.setExtraInfoLength(is.readInt(2, true));
            if (event.getExtraInfoLength() > 2)
                event.setExtraInfo(is.read(event.getExtraInfoLength() - 2));
        }
        
        Number columnCount = is.readMysqlPackedNumber();
        event.setColumnCount((columnCount == null ? 0 : columnCount.intValue()));
        event.setUsedColumnsBefore(is.readBitSet((int)event.getColumnCount(), true));
        event.setUsedColumnsAfter(is.readBitSet((int)event.getColumnCount(), true));
        event.setRows(parseRows(is, tme, event));
        return event;
    }
    
    protected List<Pair<Row>> parseRows(MysqlBinlogByteArrayInputStream is, TableMapEvent tme, UpdateRowsEvent event) throws IOException {
        final List<Pair<Row>> row = new LinkedList<Pair<Row>>();
        while (is.available() > 0) {
            final Row before = parseRow(is, tme, event.getUsedColumnsBefore());
            final Row after = parseRow(is, tme, event.getUsedColumnsAfter());
            row.add(new Pair<Row>(before, after));
        }
        return row;
    }

    public boolean isVersion2() {
        return version2;
    }
    public void setVersion2(boolean version2) {
        this.version2 = version2;
    }
}
