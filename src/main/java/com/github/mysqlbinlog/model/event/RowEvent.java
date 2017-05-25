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

public abstract class RowEvent extends BinlogEvent {
    private static final long serialVersionUID = 1033129061104761546L;
    protected long tableId;
    protected int reserved;

    protected String databaseName;
    protected String tableName;
    
    public RowEvent() {
    }

    public RowEvent(BinlogEventHeader header, byte[] rawData) {
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
}
