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

package com.github.mysqlbinlog.event.unmarshaller;

import static org.junit.Assert.assertEquals;
import javax.xml.bind.DatatypeConverter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.github.mysqlbinlog.event.checksum.NoneMysqlChecksumImpl;
import com.github.mysqlbinlog.event.unmarshaller.BinlogEventFactory;
import com.github.mysqlbinlog.event.unmarshaller.BinlogEventHeaderUnmarshaller;
import com.github.mysqlbinlog.event.unmarshaller.BinlogEventUnmarshallerFactory;
import com.github.mysqlbinlog.event.unmarshaller.SimpleBinlogEventFactoryImpl;
import com.github.mysqlbinlog.event.unmarshaller.SimpleBinlogEventHeaderUnmarshallerImpl;
import com.github.mysqlbinlog.event.unmarshaller.SimpleBinlogEventUnmarshallerFactoryImpl;
import com.github.mysqlbinlog.event.unmarshaller.SimpleBinlogUnmarshallerContextImpl;
import com.github.mysqlbinlog.event.unmarshaller.SimpleSingleBinglogEventUnmarshallerImpl;
import com.github.mysqlbinlog.event.unmarshaller.SingleBinglogEventUnmarshaller;
import com.github.mysqlbinlog.model.event.DeleteRowsEvent;
import com.github.mysqlbinlog.model.event.FormatDescriptionEvent;
import com.github.mysqlbinlog.model.event.QueryEvent;
import com.github.mysqlbinlog.model.event.RotateEvent;
import com.github.mysqlbinlog.model.event.StopEvent;
import com.github.mysqlbinlog.model.event.TableMapEvent;
import com.github.mysqlbinlog.model.event.UpdateRowsEvent;
import com.github.mysqlbinlog.model.event.WriteRowsEvent;
import com.github.mysqlbinlog.model.event.XidEvent;

@RunWith(JUnit4.class)
public class BinlogEventUnmarshallerTest {
    private SingleBinglogEventUnmarshaller singleBinglogEventUnmarshaller;
    
    @Before
    public void init() {
        BinlogEventHeaderUnmarshaller binlogEventHeaderParser = new SimpleBinlogEventHeaderUnmarshallerImpl();
        BinlogEventFactory binlogEventFactory = new SimpleBinlogEventFactoryImpl();
        BinlogEventUnmarshallerFactory binlogEventUnmarshallerFactory = new SimpleBinlogEventUnmarshallerFactoryImpl();
        
        singleBinglogEventUnmarshaller = new SimpleSingleBinglogEventUnmarshallerImpl();
        ((SimpleSingleBinglogEventUnmarshallerImpl)(singleBinglogEventUnmarshaller)).setBinlogEventHeaderParser(binlogEventHeaderParser);
        ((SimpleSingleBinglogEventUnmarshallerImpl)(singleBinglogEventUnmarshaller)).setBinlogEventFactory(binlogEventFactory);
        ((SimpleSingleBinglogEventUnmarshallerImpl)(singleBinglogEventUnmarshaller)).setBinlogEventUnmarshallerFactory(binlogEventUnmarshallerFactory);
    }
    
    @Test
    public void QueryEventUnmarshallerTest() {
        SimpleBinlogUnmarshallerContextImpl binlogParserContext = new SimpleBinlogUnmarshallerContextImpl();
        binlogParserContext.setChecksum(new NoneMysqlChecksumImpl());
        
        String hexEvent = "00EA633657026F00000049000000B6661100080001000000000000000500001A0000000000000100000000000000000603737464042100210008006D7973716C00424547494E";
        
        QueryEvent queryEvent = (QueryEvent) singleBinglogEventUnmarshaller.parse(DatatypeConverter.parseHexBinary(hexEvent), binlogParserContext);
        assertEquals("failure - Query strings are not equal", "BEGIN", queryEvent.getSql());
        assertEquals("failure - Query strings are not equal", "mysql", queryEvent.getDatabaseName());
        
        hexEvent = "00EA633657026F0000004A00000066671100080001000000000000000500001A0000000000000100000000000000000603737464042100210008006D7973716C00434F4D4D4954";
        queryEvent = (QueryEvent) singleBinglogEventUnmarshaller.parse(DatatypeConverter.parseHexBinary(hexEvent), binlogParserContext);
        assertEquals("failure - Query strings are not equal", "COMMIT", queryEvent.getSql());
        assertEquals("failure - Query strings are not equal", "mysql", queryEvent.getDatabaseName());
        
        hexEvent = "00EE633657026F0000003D010000B501000000000100000000000000050000220000000000000100000040000000000603737464040800080008000C016D7973716C006D7973716C0020435245415445205441424C45204946204E4F542045584953545320706C7567696E20286E616D6520636861722836342920434F4C4C41544520757466385F62696E204E4F54204E554C4C2044454641554C542027272C20202020646C2063686172283132382920434F4C4C41544520757466385F62696E204E4F54204E554C4C2044454641554C542027272C202020205052494D415259204B455920286E616D65292920454E47494E453D4D794953414D2044454641554C5420434841525345543D7574663820434F4C4C4154453D757466385F62696E20434F4D4D454E543D274D7953514C20706C7567696E73273B";
        queryEvent = (QueryEvent) singleBinglogEventUnmarshaller.parse(DatatypeConverter.parseHexBinary(hexEvent), binlogParserContext);
        assertEquals("failure - Query strings are not equal", "mysql", queryEvent.getDatabaseName());
        
        System.out.println("QueryEvent [" + queryEvent + "]");

    }
    
    @Test
    public void XidEventUnmarshallerTest() {
        SimpleBinlogUnmarshallerContextImpl binlogParserContext = new SimpleBinlogUnmarshallerContextImpl();
        binlogParserContext.setChecksum(new NoneMysqlChecksumImpl());

        String hexEvent = "00B9993B57106F0000001F000000520300000000BB03000000000000";
        
        XidEvent xidEvent = (XidEvent) singleBinglogEventUnmarshaller.parse(DatatypeConverter.parseHexBinary(hexEvent), binlogParserContext);
        assertEquals("failure - XidEvent XID are not equal", 955, xidEvent.getXid());
    
        System.out.println("XidEvent [" + xidEvent + "]");
    }

    
    @Test
    public void StopEventUnmarshallerTest() {
        SimpleBinlogUnmarshallerContextImpl binlogParserContext = new SimpleBinlogUnmarshallerContextImpl();
        binlogParserContext.setChecksum(new NoneMysqlChecksumImpl());

        String hexEvent = "00EC633657036F00000017000000250300000000";
        
        StopEvent stopEvent = (StopEvent) singleBinglogEventUnmarshaller.parse(DatatypeConverter.parseHexBinary(hexEvent), binlogParserContext);
        assertEquals("failure - StopEvent event type are not equal", 3, stopEvent.getHeader().getEventType());
    
        System.out.println("StopEvent [" + stopEvent + "]");
    }
    
    @Test
    public void RotateEventUnmarshallerTest() {
        SimpleBinlogUnmarshallerContextImpl binlogParserContext = new SimpleBinlogUnmarshallerContextImpl();
        binlogParserContext.setChecksum(new NoneMysqlChecksumImpl());

        String hexEvent = "0000000000046F0000002F00000000000000200004000000000000006D7973716C2D62696E2E303030303932";
        
        RotateEvent rotateEvent = (RotateEvent) singleBinglogEventUnmarshaller.parse(DatatypeConverter.parseHexBinary(hexEvent), binlogParserContext);
        assertEquals("failure - RotateEvent fileName are not equal", "mysql-bin.000092", rotateEvent.getBinlogFileName());
        
        System.out.println("RotateEvent [" + rotateEvent + "]");
    }
    
    @Test
    public void FormatDescriptionEventUnmarshallerTest() {
        SimpleBinlogUnmarshallerContextImpl binlogParserContext = new SimpleBinlogUnmarshallerContextImpl();
        binlogParserContext.setChecksum(new NoneMysqlChecksumImpl());

        String hexEvent = "00EE6336570F6F000000740000007800000000000400352E362E33302D307562756E7475302E31342E30342E312D6C6F670000000000000000000000000000000000000000000000EE63365713380D0008001200040404041200005C00041A08000000080808020000000A0A0A19190001";
        
        FormatDescriptionEvent formatDescriptionEvent = (FormatDescriptionEvent) singleBinglogEventUnmarshaller.parse(DatatypeConverter.parseHexBinary(hexEvent), binlogParserContext);
        assertEquals("failure - FormatDescriptionEvent fileName are not equal", "5.6.30-0ubuntu0.14.04.1-log", formatDescriptionEvent.getServerVersion());
        
        System.out.println("FormatDescriptionEvent [" + formatDescriptionEvent + "]");
    }


    
    @Test
    public void UpdateRowsEventV2UnmarshallerTest() {
        SimpleBinlogUnmarshallerContextImpl binlogParserContext = new SimpleBinlogUnmarshallerContextImpl();
        binlogParserContext.setChecksum(new NoneMysqlChecksumImpl());

        String hexEvent = "00EC633657136F000000A90000006A01000000000000000000000100056D7973716C000475736572002BFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFEFCFCFC03030303FEFCFE4AFEB4FE30FE29F701F701F701F701F701F701F701F701F701F701F701F701F701F701F701F701F701F701F701F701F701F701F701F701F701F701F701F701F701F701020202FEC002F701000000000003";
        
        TableMapEvent tableMapEvent = (TableMapEvent) singleBinglogEventUnmarshaller.parse(DatatypeConverter.parseHexBinary(hexEvent), binlogParserContext);
        assertEquals("failure - TableMapEvent tableName are not equal", "user", tableMapEvent.getTableName());
        //System.out.println("TableMapEvent [" + tableMapEvent + "]");
        
        hexEvent = "00EC6336571F6F0000005A010000C40200000000000000000000010002002BFFFFFFFFFFFFFFFFFFFFFFFF0000000000FA096C6F63616C686F73741064656269616E2D7379732D6D61696E74292A3633384343383236433345383332463542313134344331363038313739324636363033383231434302020202020202020202020202020202020202020202020202020202010100000000000000000000000000000000000000000000156D7973716C5F6E61746976655F70617373776F7264010000000000FA096C6F63616C686F73741064656269616E2D7379732D6D61696E74292A3633384343383236433345383332463542313134344331363038313739324636363033383231434302020202020202020202020202020202020202020202020202020202010100000000000000000000000000000000000000000000156D7973716C5F6E61746976655F70617373776F726401";
        UpdateRowsEvent updateRowsEvent = (UpdateRowsEvent ) singleBinglogEventUnmarshaller.parse(DatatypeConverter.parseHexBinary(hexEvent), binlogParserContext);
        assertEquals("failure - UpdateRowsEvent tableName are not equal", "user", updateRowsEvent.getTableName());
        //System.out.println("UpdateRowsEvent [" + updateRowsEvent + "]");
    }
    
    @Test
    public void WriteRowsEventV2UnmarshallerTest() {
        SimpleBinlogUnmarshallerContextImpl binlogParserContext = new SimpleBinlogUnmarshallerContextImpl();
        binlogParserContext.setChecksum(new NoneMysqlChecksumImpl());

        String hexEvent = "00EA633657136F0000003A000000B3F6120000005400000000000100056D7973716C000D68656C705F72656C6174696F6E000203030000";
        
        TableMapEvent tableMapEvent = (TableMapEvent) singleBinglogEventUnmarshaller.parse(DatatypeConverter.parseHexBinary(hexEvent), binlogParserContext);
        assertEquals("failure - TableMapEvent tableName are not equal", "help_relation", tableMapEvent.getTableName());
        //System.out.println("TableMapEvent [" + tableMapEvent + "]");
        
        hexEvent = "00EA6336571E6F0000002C000000DFF6120000005400000000000100020002FFFC3802000060020000";
        WriteRowsEvent writeRowsEvent = (WriteRowsEvent ) singleBinglogEventUnmarshaller.parse(DatatypeConverter.parseHexBinary(hexEvent), binlogParserContext);
        assertEquals("failure - WriteRowsEvent tableName are not equal", "help_relation", writeRowsEvent.getTableName());
        //System.out.println("WriteRowsEvent [" + writeRowsEvent + "]");

        hexEvent = "00B9993B57136F000000520000001A010000000092000000000001000474657374000F646966666572656E745F76616C7565000C0308F6040512110AFE0FFCFC0C0A0204080000F70100020202BE0D";
        tableMapEvent = (TableMapEvent) singleBinglogEventUnmarshaller.parse(DatatypeConverter.parseHexBinary(hexEvent), binlogParserContext);
        assertEquals("failure - TableMapEvent tableName are not equal", "different_value", tableMapEvent.getTableName());
        //System.out.println("TableMapEvent [" + tableMapEvent + "]");
        
        hexEvent = "00B9993B571E6F000000AC000000330300000000920000000000010002000CFFFFA0F0160000001027000000000000800000020055555540BAAAAAAAAAAAFA3F573B99B9020600746573745F314D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B005445535420544558542031";
        writeRowsEvent = (WriteRowsEvent ) singleBinglogEventUnmarshaller.parse(DatatypeConverter.parseHexBinary(hexEvent), binlogParserContext);
        assertEquals("failure - WriteRowsEvent tableName are not equal", "different_value", writeRowsEvent.getTableName());
        //System.out.println("WriteRowsEvent [" + writeRowsEvent + "]");
    }
    
    @Test
    public void DeleteRowsEventV2UnmarshallerTest() {
        SimpleBinlogUnmarshallerContextImpl binlogParserContext = new SimpleBinlogUnmarshallerContextImpl();
        binlogParserContext.setChecksum(new NoneMysqlChecksumImpl());

        String hexEvent = "00529A3B57136F00000052000000EC030000000092000000000001000474657374000F646966666572656E745F76616C7565000C0308F6040512110AFE0FFCFC0C0A0204080000F70100020202BE0D";
        
        TableMapEvent tableMapEvent = (TableMapEvent) singleBinglogEventUnmarshaller.parse(DatatypeConverter.parseHexBinary(hexEvent), binlogParserContext);
        assertEquals("failure - TableMapEvent tableName are not equal", "different_value", tableMapEvent.getTableName());
        //System.out.println("TableMapEvent [" + tableMapEvent + "]");
        
        hexEvent = "00529A3B57206F000000540C0000401000000000920000000000010002000CFFFF00F001000000000000000000000080000000000000000000000000000000009998B2EB3656CF83E659C00F010600746573745F304D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B00544553542054455854203000F0020000001027000000000000800000020055555540BAAAAAAAAAAAFA3F9998B2EB3656CF83E659C00F020600746573745F314D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B00544553542054455854203100F003000000204E00000000000080000004005555D540A3AAAAAAAAAA0A409998B2EB3656CF83E659C00F030600746573745F324D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B00544553542054455854203200F004000000307500000000000080000006000000204100000000000014409998B2EB3656CF83E659C00F040600746573745F334D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B00544553542054455854203300F005000000409C000000000000800000080055555541AEAAAAAAAAAA1A409998B2EB3656CF83E659C00F000600746573745F344D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B00544553542054455854203400F00600000050C30000000000008000000A0055558541A9AAAAAAAAAA20409998B2EB3656CF83E659C00F010600746573745F354D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B00544553542054455854203500F00700000060EA0000000000008000000C000000A04100000000000024409998B2EB3656CF83E659C00F020600746573745F364D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B00544553542054455854203600F00800000070110100000000008000000E00ABAABA4168555555555527409998B2EB3656CF83E659C00F030600746573745F374D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B00544553542054455854203700F009000000803801000000000080000010005555D54198AAAAAAAAAA2A409998B2EB3656CF83E659C00F040600746573745F384D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B00544553542054455854203800F00A000000905F01000000000080000012000000F0410000000000002E409998B2EB3656CF83E659C00F000600746573745F394D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B00544553542054455854203900F00B000000000000000000000080000000000000000000000000000000009998B2ECDE56CF857259C00F010600746573745F304D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B005445535420544558542030A0F00C0000001027000000000000800000020055555540BAAAAAAAAAAAFA3F56CF8572020600746573745F314D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B00544553542054455854203100F00D000000204E00000000000080000004005555D540A3AAAAAAAAAA0A409998B2ECDE56CF857259C00F030600746573745F324D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B005445535420544558542032A0F00E0000003075000000000000800000060000002041000000000000144056CF8572040600746573745F334D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B00544553542054455854203300F00F000000409C000000000000800000080055555541AEAAAAAAAAAA1A409998B2ECDE56CF857259C00F000600746573745F344D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B005445535420544558542034A0F01000000050C30000000000008000000A0055558541A9AAAAAAAAAA204056CF8572010600746573745F354D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B00544553542054455854203500F01100000060EA0000000000008000000C000000A04100000000000024409998B2ECDE56CF857259C00F020600746573745F364D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B005445535420544558542036A0F01200000070110100000000008000000E00ABAABA41685555555555274056CF8572030600746573745F374D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B00544553542054455854203700F013000000803801000000000080000010005555D54198AAAAAAAAAA2A409998B2ECDE56CF857259C00F040600746573745F384D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B005445535420544558542038A0F014000000905F01000000000080000012000000F0410000000000002E4056CF8572000600746573745F394D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B00544553542054455854203900F01500000000000000000000008000000000000000000000000000000000999962F5B1573B99B9B1C00F010600746573745F304D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B005445535420544558542030A0F0160000001027000000000000800000020055555540BAAAAAAAAAAAFA3F573B99B9020600746573745F314D00615546425155464251554642515546425155464251554642515546425155464251554642515546425155464251574A69596D4A69596D4A69596D4A69596D4A69596D4A69596D4A6959673D3D0A0B005445535420544558542031";
        DeleteRowsEvent deleteRowsEvent = (DeleteRowsEvent ) singleBinglogEventUnmarshaller.parse(DatatypeConverter.parseHexBinary(hexEvent), binlogParserContext);
        assertEquals("failure - DeleteRowsEvent tableName are not equal", "different_value", deleteRowsEvent.getTableName());
        System.out.println("DeleteRowsEvent [" + deleteRowsEvent + "]");

    }


    @Test
    public void UserVarEventUnmarshallerTest() {
        
    }
    @Test
    public void RandEventUnmarshallerTest() {
        
    }
    @Test
    public void IntVarEventUnmarshallerTest() {
        
    }
    @Test
    public void IncidentEventUnmarshallerTest() {
        
    }
    @Test
    public void GtidEventUnmarshallerTest() {
        
    }
}
