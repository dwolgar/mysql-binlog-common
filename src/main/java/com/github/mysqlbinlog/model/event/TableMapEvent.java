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

import com.github.mysqlbinlog.model.event.extra.ColumnExtraData;
import com.github.mysqlbinlog.model.event.extra.Metadata;

/*
 * TABLE_MAP_EVENT
 *
 * Used for row-based binary logging. This event precedes each row operation event. 
 * It maps a table definition to a number, where the table definition consists of database and table names and column definitions. 
 * The purpose of this event is to enable replication when a table has different definitions on the master and slave. 
 * Row operation events that belong to the same transaction may be grouped into sequences, 
 * in which case each such sequence of events begins with a sequence of TABLE_MAP_EVENT events: one per table used by events in the sequence.
 */
public final class TableMapEvent extends BinlogEvent {
    private static final long serialVersionUID = -8115026727547190289L;

    private long tableId;
    private int reserved;
    private String databaseName;
    private String tableName;
    private long columnCount;
    private byte[] columnTypes;
    private long columnMetadataCount;
    private Metadata columnMetadata;
    private BitSet columnNullabilities;
    
    private List<ColumnExtraData> columnExtra;
    
    public TableMapEvent() {}

    public TableMapEvent(BinlogEventHeader header, byte[] rawData) {
        super(header, rawData);
    }

    public long getTableId() {
        return tableId;
    }
    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public int getReserved() {
        return reserved;
    }
    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public String getDatabaseName() {
        return databaseName;
    }
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public long getColumnCount() {
        return columnCount;
    }
    public void setColumnCount(long columnCount) {
        this.columnCount = columnCount;
    }

    public byte[] getColumnTypes() {
        return columnTypes;
    }
    public void setColumnTypes(byte[] columnTypes) {
        this.columnTypes = columnTypes;
    }

    public long getColumnMetadataCount() {
        return columnMetadataCount;
    }
    public void setColumnMetadataCount(long columnMetadataCount) {
        this.columnMetadataCount = columnMetadataCount;
    }

    public Metadata getColumnMetadata() {
        return columnMetadata;
    }
    public void setColumnMetadata(Metadata columnMetadata) {
        this.columnMetadata = columnMetadata;
    }

    public BitSet getColumnNullabilities() {
        return columnNullabilities;
    }
    public void setColumnNullabilities(BitSet columnNullabilities) {
        this.columnNullabilities = columnNullabilities;
    }

    public List<ColumnExtraData> getColumnExtra() {
        return columnExtra;
    }
    public void setColumnExtra(List<ColumnExtraData> columnExtra) {
        this.columnExtra = columnExtra;
    }
    
    @Override
    public String toString() {
        return "TableMapEvent [tableId=" + tableId + ", reserved=" + reserved
                + ", databaseName=" + databaseName + ", tableName=" + tableName
                + ", columnCount=" + columnCount + ", columnTypes="
                + Arrays.toString(columnTypes) + ", columnMetadataCount="
                + columnMetadataCount + ", columnMetadata=" + columnMetadata
                + ", columnNullabilities=" + columnNullabilities
                + ", columnExtra=" + columnExtra + "]";
    }

}
