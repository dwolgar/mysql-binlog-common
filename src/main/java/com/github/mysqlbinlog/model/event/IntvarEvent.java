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

import java.math.BigInteger;

/*
 * INTVAR_EVENT
 *
 * Written every time a statement uses an AUTO_INCREMENT column or the LAST_INSERT_ID() function; precedes other events for the statement. 
 * This is written only before a QUERY_EVENT and is not used with row-based logging. An INTVAR_EVENT is written with a "subtype" in the event data part:
 *
 *  INSERT_ID_EVENT indicates the value to use for an AUTO_INCREMENT column in the next statement.
 *
 *  LAST_INSERT_ID_EVENT indicates the value to use for the LAST_INSERT_ID() function in the next statement.
 */
public final class IntvarEvent extends BinlogEvent {
    private static final long serialVersionUID = -8777784212561872473L;

    private int type;
    private BigInteger value;

    public IntvarEvent() {
        
    }

    public IntvarEvent(BinlogEventHeader header, byte[] rawData) {
        super(header, rawData);
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    public BigInteger getValue() {
        return value;
    }
    public void setValue(BigInteger value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "IntvarEvent [type=" + type + ", value=" + value + "]";
    }
}
