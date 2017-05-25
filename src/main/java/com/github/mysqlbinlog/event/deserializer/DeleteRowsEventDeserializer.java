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


import com.github.mysql.io.MysqlBinlogByteArrayInputStream;
import com.github.mysqlbinlog.model.event.BinlogEvent;
import com.github.mysqlbinlog.model.event.DeleteRowsEvent;
import com.github.mysqlbinlog.model.event.TableMapEvent;
import com.github.mysqlbinlog.model.event.extra.Row;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DeleteRowsEventDeserializer extends AbstractRowEventDeserializer<DeleteRowsEvent> {
    private boolean version2;
    
    public DeleteRowsEventDeserializer() {
        super();
    }
    
    public DeleteRowsEventDeserializer(boolean version2) {
        super();
        this.version2 = version2;
    }

    @Override
    public BinlogEvent deserialize(DeleteRowsEvent event, MysqlBinlogByteArrayInputStream is, BinlogDeserializerContext context) throws IOException {

        final long tableId = is.readLong(6, true);

        final TableMapEvent tme = context.getTableMapEvent(tableId);

        event.setDatabaseName(tme.getDatabaseName());
        event.setTableName(tme.getTableName());

        event.setTableId(tableId);
        event.setReserved(is.readInt(2, true));

        if (this.isVersion2()) {
            event.setExtraInfoLength(is.readInt(2, true));
            if (event.getExtraInfoLength() > 2) {
                event.setExtraInfo(is.read(event.getExtraInfoLength() - 2));
            }
        }

        Number columnCount = is.readMysqlPackedNumber();
        event.setColumnCount((columnCount == null ? 0 : columnCount.intValue()));
        event.setUsedColumns(is.readBitSet((int)event.getColumnCount(), true));
        event.setRows(parseRows(is, tme, event));

        return event;
    }

    protected List<Row> parseRows(MysqlBinlogByteArrayInputStream is, TableMapEvent tme, DeleteRowsEvent event) throws IOException {
        final List<Row> row = new LinkedList<Row>();
        while (is.available() > 0) {
            row.add(parseRow(is, tme, event.getUsedColumns()));
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
