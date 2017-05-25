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
 * RAND_EVENT
 *
 * Written every time a statement uses the RAND() function; precedes other events for the statement. 
 * Indicates the seed values to use for generating a random number with RAND() in the next statement. 
 * This is written only before a QUERY_EVENT and is not used with row-based logging.
 */
public final class RandEvent extends BinlogEvent {
    private static final long serialVersionUID = -676004813844458819L;

    private long randSeed1;
    private long randSeed2;

    public RandEvent() {}

    public RandEvent(BinlogEventHeader header, byte[] rawData) {
        super(header, rawData);
    }

    public long getRandSeed1() {
        return randSeed1;
    }
    
    public void setRandSeed1(long randSeed1) {
        this.randSeed1 = randSeed1;
    }
    
    public long getRandSeed2() {
        return randSeed2;
    }
    
    public void setRandSeed2(long randSeed2) {
        this.randSeed2 = randSeed2;
    }
    
    @Override
    public String toString() {
        return "RandEvent [randSeed1=" + randSeed1 + ", randSeed2=" + randSeed2
                + "]";
    }
}
