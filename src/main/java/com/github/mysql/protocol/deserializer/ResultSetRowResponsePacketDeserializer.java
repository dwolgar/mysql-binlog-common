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


import java.util.LinkedList;
import java.util.List;

import com.github.mysql.io.MysqlBinlogByteArrayInputStream;
import com.github.mysql.protocol.model.ResponsePacket;
import com.github.mysql.protocol.model.ResultSetRowResponsePacket;


public class ResultSetRowResponsePacketDeserializer implements ResponsePacketDeserializer {

    @Override
    public ResponsePacket unmarshal(MysqlBinlogByteArrayInputStream is) {
        try {
            ResultSetRowResponsePacket packet = new ResultSetRowResponsePacket();

            List<String> list = new LinkedList<String>();
            while (is.available() > 0) {
                list.add(is.readLengthEncodedString());
            }
            packet.setValues(list);

            return packet;
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
