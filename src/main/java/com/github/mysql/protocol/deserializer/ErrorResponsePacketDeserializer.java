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
import com.github.mysql.protocol.model.ErrorResponsePacket;
import com.github.mysql.protocol.model.ResponsePacket;

public class ErrorResponsePacketDeserializer implements ResponsePacketDeserializer {

    @Override
    public ResponsePacket unmarshal(MysqlBinlogByteArrayInputStream is) {
        try {
            ErrorResponsePacket packet = new ErrorResponsePacket();

            int marker = is.readInt(1, true);
            packet.setErrorCode(is.readInt(2, true));
            packet.setSlash(is.readString(1));
            packet.setSqlState(is.readString(5));
            packet.setErrorMessage(is.readString(is.available()));

            return packet;
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
