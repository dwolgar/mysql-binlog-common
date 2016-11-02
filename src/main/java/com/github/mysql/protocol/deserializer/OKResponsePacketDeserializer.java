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
import com.github.mysql.protocol.model.OKResponsePacket;
import com.github.mysql.protocol.model.ResponsePacket;

public class OKResponsePacketDeserializer implements ResponsePacketDeserializer {

    @Override
    public ResponsePacket unmarshal(MysqlBinlogByteArrayInputStream is) {
        try {
            OKResponsePacket packet = new OKResponsePacket();

            int marker = is.readInt(1, true);
            packet.setAffectedRows(is.readMysqlPackedNumber().longValue());
            packet.setInsertId(is.readMysqlPackedNumber().longValue());
            packet.setServerStatus(is.readInt(2, true));
            packet.setWarningCount(is.readInt(2, true));
            if (is.available() > 0) 
                packet.setMessage(is.readString(is.available()));
            return packet;
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
