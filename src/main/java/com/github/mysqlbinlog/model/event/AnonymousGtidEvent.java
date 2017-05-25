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

public class AnonymousGtidEvent extends BinlogEvent {
    private static final long serialVersionUID = 2485262423513374073L;
    //TODO: implement actual event

    public AnonymousGtidEvent() {

    }

    public AnonymousGtidEvent(BinlogEventHeader header, byte[] rawData) {
        super(header, rawData);
    }

}
