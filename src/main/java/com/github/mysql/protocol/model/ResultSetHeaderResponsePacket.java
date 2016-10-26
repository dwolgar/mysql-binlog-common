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

package com.github.mysql.protocol.model;

public class ResultSetHeaderResponsePacket implements ResponsePacket {
    private static final long serialVersionUID = 4061497205533352826L;
    
    private long fieldCount;
    private long extra;
    
    public ResultSetHeaderResponsePacket() {
        
    }
    
    @Override
    public String toString() {
        return "MysqlResultSetHeaderResponsePacket [fieldCount=" + fieldCount
                + ", extra=" + extra + "]";
    }

    public long getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(long fieldCount) {
        this.fieldCount = fieldCount;
    }

    public long getExtra() {
        return extra;
    }

    public void setExtra(long extra) {
        this.extra = extra;
    }

}
