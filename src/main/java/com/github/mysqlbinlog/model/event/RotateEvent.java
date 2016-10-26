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

/*
 * ROTATE_EVENT
 *
 * Written when mysqld switches to a new binary log file. 
 * This occurs when someone issues a FLUSH LOGS statement or the current binary log file becomes too large. 
 * The maximum size is determined by max_binlog_size.
 *
 */
public final class RotateEvent extends BinlogEvent {
    private static final long serialVersionUID = 2129997803902359868L;

    private long binlogPosition;
    private String binlogFileName;

    public RotateEvent() {
        
    }

    public RotateEvent(BinlogEventHeader header, byte[] rawData) {
        super(header, rawData);
    }

    public long getBinlogPosition() {
        return binlogPosition;
    }
    public void setBinlogPosition(long binlogPosition) {
        this.binlogPosition = binlogPosition;
    }

    public String getBinlogFileName() {
        return binlogFileName;
    }
    public void setBinlogFileName(String binlogFileName) {
        this.binlogFileName = binlogFileName;
    }

    @Override
    public String toString() {
        return "RotateEvent [binlogPosition=" + binlogPosition
                + ", binlogFileName=" + binlogFileName + "]";
    }
}
