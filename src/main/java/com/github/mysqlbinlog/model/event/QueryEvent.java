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

import com.github.mysqlbinlog.model.variable.StatusVariable;

import java.util.List;

/*
 * QUERY_EVENT
 *
 * Written when an updating statement is done.
 */
public class QueryEvent extends BinlogEvent {
    private static final long serialVersionUID = -3736395533547553668L;
    
    private long threadId;
    private long elapsedTime;
    private int errorCode;
    private List<StatusVariable> statusVariables;
    private String databaseName;
    private String sql;

    public QueryEvent(BinlogEventHeader header, byte[] rawData) {
        super(header, rawData);
    }
    
    public QueryEvent() {
        
    }

    public long getThreadId() {
        return threadId;
    }
    
    public void setThreadId(long threadId) {
        this.threadId = threadId;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }
    
    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public int getErrorCode() {
        return errorCode;
    }
    
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<StatusVariable> getStatusVariables() {
        return statusVariables;
    }
    
    public void setStatusVariables(List<StatusVariable> statusVariables) {
        this.statusVariables = statusVariables;
    }

    public String getDatabaseName() {
        return databaseName;
    }
    
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getSql() {
        return sql;
    }
    
    public void setSql(String sql) {
        this.sql = sql;
    }
    
    @Override
    public String toString() {
        return "QueryEvent [threadId=" + threadId + ", elapsedTime="
                + elapsedTime + ", errorCode=" + errorCode
                + ", statusVariables=" + statusVariables + ", databaseName="
                + databaseName + ", sql=" + sql + "]";
    }
}
