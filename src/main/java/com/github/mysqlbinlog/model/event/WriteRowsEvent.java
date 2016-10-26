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

package com.github.mysqlbinlog.model.event;


import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

import com.github.mysqlbinlog.model.event.extra.Row;

/**
 * WRITE_ROWS_EVENT
 *
 * Used for row-based binary logging. This event logs inserts of rows in a single table.
 */
public final class WriteRowsEvent extends RowEvent {
    private static final long serialVersionUID = 1L;
    private int extraInfoLength;
    private byte extraInfo[];
    private long columnCount;
    private BitSet usedColumns;
    private List<Row> rows;

    public WriteRowsEvent() {
    }

    public WriteRowsEvent(BinlogEventHeader header, byte[] rawData) {
        super(header, rawData);
    }

    public int getExtraInfoLength() {
        return extraInfoLength;
    }
    public void setExtraInfoLength(int extraInfoLength) {
        this.extraInfoLength = extraInfoLength;
    }

    public byte[] getExtraInfo() {
        return extraInfo;
    }
    public void setExtraInfo(byte[] extraInfo) {
        this.extraInfo = extraInfo;
    }

    public long getColumnCount() {
        return columnCount;
    }
    public void setColumnCount(long columnCount) {
        this.columnCount = columnCount;
    }

    public BitSet getUsedColumns() {
        return usedColumns;
    }
    public void setUsedColumns(BitSet usedColumns) {
        this.usedColumns = usedColumns;
    }

    public List<Row> getRows() {
        return rows;
    }
    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "WriteRowsEventV2 ["
                + "tableId=" + getTableId() + ","
                + "tableName=" + getTableName() + ","
                + "databaseName=" + getDatabaseName() + ","
                + "reserved=" + getReserved() + ","
                + "extraInfoLength=" + extraInfoLength
                + ", extraInfo=" + Arrays.toString(extraInfo)
                + ", columnCount=" + columnCount + ", usedColumns="
                + usedColumns + ", rows=" + rows + "]";
    }
    
    
}
