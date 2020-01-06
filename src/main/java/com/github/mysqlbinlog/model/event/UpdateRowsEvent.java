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

import com.github.mysqlbinlog.model.event.extra.Pair;
import com.github.mysqlbinlog.model.event.extra.Row;

/**
 * UPDATE_ROWS_EVENT
 *
 * Used for row-based binary logging. This event logs updates of rows in a single table.
 */
public final class UpdateRowsEvent extends RowEvent {
    private static final long serialVersionUID = -5780778688824330051L;
    
    private int extraInfoLength;
    private byte extraInfo[];
    private long columnCount;
    private BitSet usedColumnsBefore;
    private BitSet usedColumnsAfter;
    private List<Pair<Row>> rows;

    public UpdateRowsEvent() {
    }

    public UpdateRowsEvent(BinlogEventHeader header, byte[] rawData) {
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

    public BitSet getUsedColumnsBefore() {
        return usedColumnsBefore;
    }
    
    public void setUsedColumnsBefore(BitSet usedColumnsBefore) {
        this.usedColumnsBefore = usedColumnsBefore;
    }

    public BitSet getUsedColumnsAfter() {
        return usedColumnsAfter;
    }
    
    public void setUsedColumnsAfter(BitSet usedColumnsAfter) {
        this.usedColumnsAfter = usedColumnsAfter;
    }

    public List<Pair<Row>> getRows() {
        return rows;
    }
    
    public void setRows(List<Pair<Row>> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "UpdateRowsEvent ["
                + "tableId=" + getTableId() + ","
                + "tableName=" + getTableName() + ","
                + "databaseName=" + getDatabaseName() + ","
                + "reserved=" + getReserved() + ","
                + "extraInfoLength=" + extraInfoLength
                + ", extraInfo=" + Arrays.toString(extraInfo)
                + ", columnCount=" + columnCount + ", usedColumnsBefore="
                + usedColumnsBefore + ", usedColumnsAfter=" + usedColumnsAfter
                + ", rows=" + rows + "]";
    }
}
