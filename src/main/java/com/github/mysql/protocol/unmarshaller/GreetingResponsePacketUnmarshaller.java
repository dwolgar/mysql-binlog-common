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

package com.github.mysql.protocol.unmarshaller;


import com.github.mysql.io.MysqlBinlogByteArrayInputStream;
import com.github.mysql.protocol.model.GreetingResponsePacket;
import com.github.mysql.protocol.model.ResponsePacket;

public class GreetingResponsePacketUnmarshaller implements ResponsePacketUnmarshaller {

    @Override
    public ResponsePacket unmarshal(MysqlBinlogByteArrayInputStream is) {
        try {
            GreetingResponsePacket packet = new GreetingResponsePacket();

            packet.setProtocolVersion(is.readInt(1, true));
            packet.setServerVersion(is.readNullTerminatedString());
            packet.setThreadId(is.readLong(4, true));
            String scramble1 = is.readNullTerminatedString();
            packet.setServerCapabilities(is.readInt(2, true));
            packet.setServerCollation(is.readInt(1, true));
            packet.setServerStatus(is.readInt(2, true));
            is.skip(13);
            String scramble2 = is.readNullTerminatedString();
            if (is.available() > 0) {
                packet.setPluginProvidedData(is.readNullTerminatedString());
            }
            packet.setScramble(scramble1 + scramble2);

            return packet;
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
