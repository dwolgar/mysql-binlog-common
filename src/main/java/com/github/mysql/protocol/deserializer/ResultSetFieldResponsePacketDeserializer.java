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

package com.github.mysql.protocol.deserializer;


import com.github.mysql.io.MysqlBinlogByteArrayInputStream;
import com.github.mysql.protocol.model.ResponsePacket;
import com.github.mysql.protocol.model.ResultSetFieldResponsePacket;

public class ResultSetFieldResponsePacketDeserializer implements ResponsePacketDeserializer {

    @Override
    public ResponsePacket unmarshal(MysqlBinlogByteArrayInputStream is) {
        try {
            ResultSetFieldResponsePacket packet = new ResultSetFieldResponsePacket();

            packet.setCatalog(is.readLengthEncodedString());
            packet.setDb(is.readLengthEncodedString());
            packet.setTable(is.readLengthEncodedString());
            packet.setOrginalTable(is.readLengthEncodedString());
            packet.setColumn(is.readLengthEncodedString());
            packet.setOriginalColumn(is.readLengthEncodedString());
            packet.setFixed12(is.readInt(1, true));
            packet.setCharset(is.readInt(2, true));
            packet.setFieldLength(is.readLong(4, true));
            packet.setFieldType(is.readInt(1, true));
            packet.setFieldOptions(is.readInt(2, true));
            packet.setDecimalPrecision(is.readInt(1, true));
            packet.setReserved(is.readInt(2, true));
            if (is.available() > 0) {
                packet.setDefaultValue(is.readLengthEncodedString());
            }

            return packet;
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
