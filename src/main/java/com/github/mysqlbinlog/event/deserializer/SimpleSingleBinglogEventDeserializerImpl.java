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

import java.io.ByteArrayInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mysql.io.MysqlBinlogByteArrayInputStream;
import com.github.mysqlbinlog.event.checksum.MysqlChecksum;
import com.github.mysqlbinlog.model.event.BinlogEvent;
import com.github.mysqlbinlog.model.event.BinlogEventHeader;

@SuppressWarnings("rawtypes")
public class SimpleSingleBinglogEventDeserializerImpl implements SingleBinglogEventDeserializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSingleBinglogEventDeserializerImpl.class);
    
    private BinlogEventHeaderDeserializer binlogEventHeaderParser;
    private BinlogEventFactory binlogEventFactory;
    private BinlogEventDeserializerFactory binlogEventUnmarshallerFactory;
    
    public SimpleSingleBinglogEventDeserializerImpl() {
    }
    
    @SuppressWarnings("unchecked")
    public BinlogEvent deserialize(byte[] rawData, BinlogDeserializerContext context) {
        MysqlChecksum checksum = context.getChecksum();
        
        MysqlBinlogByteArrayInputStream is = new MysqlBinlogByteArrayInputStream(new ByteArrayInputStream(rawData, 0, rawData.length - checksum.getSize()));
        
        
        BinlogEventHeader eventHeader = null;
        
        try {
            eventHeader = binlogEventHeaderParser.deserialize(is);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        
        BinlogEventDeserializer binlogEventParser = binlogEventUnmarshallerFactory.getBinlogEventDeserializer(eventHeader);
        if (binlogEventParser == null) {
            throw new RuntimeException("Unknown event type [" + eventHeader.getEventType() + "]");
        }
        
        BinlogEvent event = binlogEventFactory.createBinlogEvent(eventHeader, rawData);
        try {
            binlogEventParser.deserialize(event, is, context);
            
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        
        checksum.update(rawData, 1, rawData.length - checksum.getSize() - 1);
        is.setInputStream(new ByteArrayInputStream(rawData, rawData.length - checksum.getSize(), checksum.getSize()));
        checksum.readChecksum(is);
        if (!checksum.validate()) {
            if (LOGGER.isDebugEnabled())
                LOGGER.debug("Invalid checksum [" + checksum.getValue() + "][" + checksum.getExpectedValue() + "][" + event + "]");
            throw new RuntimeException("Invalid checksum [" + checksum.getValue() + "][" + checksum.getExpectedValue() + "][" + event + "]");
        }
        checksum.reset();
        
        return event;
    }
    
    
    public BinlogEventHeaderDeserializer getBinlogEventHeaderParser() {
        return binlogEventHeaderParser;
    }
    public void setBinlogEventHeaderParser(BinlogEventHeaderDeserializer binlogEventHeaderParser) {
        this.binlogEventHeaderParser = binlogEventHeaderParser;
    }

    public BinlogEventFactory getBinlogEventFactory() {
        return binlogEventFactory;
    }
    public void setBinlogEventFactory(BinlogEventFactory binlogEventFactory) {
        this.binlogEventFactory = binlogEventFactory;
    }

    public BinlogEventDeserializerFactory getBinlogEventUnmarshallerFactory() {
        return binlogEventUnmarshallerFactory;
    }
    public void setBinlogEventUnmarshallerFactory(BinlogEventDeserializerFactory binlogEventUnmarshallerFactory) {
        this.binlogEventUnmarshallerFactory = binlogEventUnmarshallerFactory;
    }

}
