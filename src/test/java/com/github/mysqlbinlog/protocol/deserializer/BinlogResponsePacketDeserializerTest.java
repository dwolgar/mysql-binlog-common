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

package com.github.mysqlbinlog.protocol.deserializer;

import java.io.ByteArrayInputStream;

import javax.xml.bind.DatatypeConverter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.github.mysql.io.MysqlBinlogByteArrayInputStream;
import com.github.mysql.protocol.deserializer.EOFResponsePacketDeserializer;
import com.github.mysql.protocol.deserializer.GreetingResponsePacketDeserializer;
import com.github.mysql.protocol.deserializer.OKResponsePacketDeserializer;
import com.github.mysql.protocol.deserializer.ResultSetFieldResponsePacketDeserializer;
import com.github.mysql.protocol.deserializer.ResultSetHeaderResponsePacketDeserializer;
import com.github.mysql.protocol.deserializer.ResultSetRowResponsePacketDeserializer;
import com.github.mysql.protocol.model.EOFResponsePacket;
import com.github.mysql.protocol.model.GreetingResponsePacket;
import com.github.mysql.protocol.model.OKResponsePacket;
import com.github.mysql.protocol.model.ResultSetFieldResponsePacket;
import com.github.mysql.protocol.model.ResultSetHeaderResponsePacket;
import com.github.mysql.protocol.model.ResultSetRowResponsePacket;

@RunWith(JUnit4.class)
public class BinlogResponsePacketDeserializerTest {
    @Test
    public void MysqlGreetingResponsePacketUnmarshallerTest() throws Exception {
        String hexPacket = "0A352E362E33302D307562756E7475302E31342E30342E312D6C6F67002900000037374D5F7347487300FFF70802007F8015000000000000000000002A75313144425D2E6C554846006D7973716C5F6E61746976655F70617373776F726400";
        
        GreetingResponsePacketDeserializer unmarshaller = new GreetingResponsePacketDeserializer();

        GreetingResponsePacket packet = (GreetingResponsePacket) unmarshaller.deserialize(new MysqlBinlogByteArrayInputStream(new ByteArrayInputStream(DatatypeConverter.parseHexBinary(hexPacket))));
        System.out.println("MysqlGreetingResponsePacket [" + packet + "]");
    }
    
    @Test
    public void MysqlOKResponsePacketUnmarshallerTest() throws Exception {
        String hexPacket = "000002000000";
        
        OKResponsePacketDeserializer unmarshaller = new OKResponsePacketDeserializer();

        OKResponsePacket packet = (OKResponsePacket) unmarshaller.deserialize(new MysqlBinlogByteArrayInputStream(new ByteArrayInputStream(DatatypeConverter.parseHexBinary(hexPacket))));
        System.out.println("MysqlOKResponsePacket [" + packet + "]");
    }


    @Test
    public void MysqlResultSetFieldResponsePacketUnmarshallerTest() throws Exception {
        String[] hexPacket = {
                "0364656612696E666F726D6174696F6E5F736368656D61095641524941424C4553095641524941424C45530D5661726961626C655F6E616D650D5641524941424C455F4E414D450C080040000000FD0100000000",
                "0364656612696E666F726D6174696F6E5F736368656D61095641524941424C4553095641524941424C45530556616C75650E5641524941424C455F56414C55450C080000040000FD0000000000"
                };
        
        ResultSetFieldResponsePacketDeserializer unmarshaller = new ResultSetFieldResponsePacketDeserializer();

        for (int i = 0; i<hexPacket.length; i++) {
            ResultSetFieldResponsePacket packet = (ResultSetFieldResponsePacket) unmarshaller.deserialize(new MysqlBinlogByteArrayInputStream(new ByteArrayInputStream(DatatypeConverter.parseHexBinary(hexPacket[i]))));
            System.out.println("MysqlResultSetFieldResponsePacket [" + packet + "]");
        }
    }
    
    @Test
    public void MysqlResultSetRowResponsePacketUnmarshallerTest() throws Exception {
        String[] hexPacket = {
                "186175746F5F696E6372656D656E745F696E6372656D656E740131",
                "156175746F5F696E6372656D656E745F6F66667365740131",
                "0A6175746F636F6D6D6974024F4E",
                "176175746F6D617469635F73705F70726976696C65676573024F4E",
                };
        
        ResultSetRowResponsePacketDeserializer unmarshaller = new ResultSetRowResponsePacketDeserializer();

        for (int i = 0; i<hexPacket.length; i++) {
            ResultSetRowResponsePacket packet = (ResultSetRowResponsePacket) unmarshaller.deserialize(new MysqlBinlogByteArrayInputStream(new ByteArrayInputStream(DatatypeConverter.parseHexBinary(hexPacket[i]))));
            System.out.println("MysqlResultSetRowResponsePacket [" + packet + "]");
        }
    }

    @Test
    public void MysqlEOFResponsePacketUnmarshallerTest() throws Exception {
        String hexPacket = "00002200";
        
        EOFResponsePacketDeserializer unmarshaller = new EOFResponsePacketDeserializer();

        EOFResponsePacket packet = (EOFResponsePacket) unmarshaller.deserialize(new MysqlBinlogByteArrayInputStream(new ByteArrayInputStream(DatatypeConverter.parseHexBinary(hexPacket))));
        System.out.println("MysqlEOFResponsePacket [" + packet + "]");
    }

    @Test
    public void MysqlResultSetHeaderResponsePacketUnmarshallerTest() throws Exception {
        String hexPacket = "02";
        
        ResultSetHeaderResponsePacketDeserializer unmarshaller = new ResultSetHeaderResponsePacketDeserializer();

        ResultSetHeaderResponsePacket packet = (ResultSetHeaderResponsePacket) unmarshaller.deserialize(new MysqlBinlogByteArrayInputStream(new ByteArrayInputStream(DatatypeConverter.parseHexBinary(hexPacket))));
        System.out.println("MysqlResultSetHeaderResponsePacket [" + packet + "]");
    }


}
