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

package com.github.mysqlbinlog.event.deserializer;

import com.github.mysql.io.MysqlBinlogByteArrayInputStream;
import com.github.mysqlbinlog.model.event.AnonymousGtidEvent;
import com.github.mysqlbinlog.model.event.BinlogEvent;

import java.io.IOException;

public class AnonymousGtidEventDeserializer implements
        BinlogEventDeserializer<AnonymousGtidEvent> {

    @Override
    public BinlogEvent deserialize(AnonymousGtidEvent event,
            MysqlBinlogByteArrayInputStream is,
            BinlogDeserializerContext context) throws IOException {

        event.setFlags(is.readInt(1, true));
        event.setSourceId(is.read(16));
        event.setTransactionId(is.readLong(8, true));
        event.setLogicalCockTimestampTypeCode(is.readInt(1, true));
        event.setLastCommited(is.readLong(8, true));
        event.setSequenceNumber(is.readLong(8, true));
        event.setImmidiateCommitTimestamp(is.readLong(8,  true));
        event.setOriginalCommitTimestamp(is.readLong(8,  true));
        event.setTransactionLength(is.readMysqlPackedNumber().longValue());
        event.setImmidiateServerVersion(is.readInt(4, true));
        event.setOriginalServerVersion(is.readInt(4,  true));
        is.skip(is.available());

        return event;
    }

}
