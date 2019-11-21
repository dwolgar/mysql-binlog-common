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

package com.github.mysql.protocol.model;

import com.github.mysql.io.MysqlBinlogByteArrayOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.bind.DatatypeConverter;

public class RawMysqlPacket implements ResponsePacket {
    private static final long serialVersionUID = -8149631338389951783L;
    
    private int length;
    private int sequence;

    private byte[] rawBody;
    
    public RawMysqlPacket() {
        
    }
    
    public RawMysqlPacket(int length, int sequence, byte[] rawBody) {
        this.length = length;
        this.sequence = sequence;
        this.rawBody = rawBody;
    }
    
    public byte[] getFullBody() throws IOException {
        MysqlBinlogByteArrayOutputStream os = new MysqlBinlogByteArrayOutputStream(new ByteArrayOutputStream());
        
        os.writeInteger(this.length, 3);
        os.writeInteger(this.sequence, 1);
        os.write(this.rawBody);
        
        os.close();
        
        return ((ByteArrayOutputStream)(os.getOutputStream())).toByteArray();
    }
    
    public boolean isEmpty() {
        if (this.getRawBody() == null) {
            return true;
        }
        return false;
    }
    
    public boolean isErrorPacket() {
        if (this.getRawBody()[0] == ErrorResponsePacket.HEADER) {
            return true;
        }
        return false;
    }

    public boolean isEOFPacket() {
        if (this.getRawBody()[0] == EOFResponsePacket.HEADER) {
            return true;
        }
        return false;
    }

    public boolean isOKPacket() {
        if (this.getRawBody()[0] == OKResponsePacket.HEADER) {
            return true;
        }
        return false;
    }

    
    @Override
    public String toString() {
        return "MysqlRawResponsePacket [length=" + length + ", sequence="
                + sequence + ", raw body=" + (this.rawBody == null ? "NULL" : DatatypeConverter.printHexBinary(this.rawBody)) + "]";
    }

    public int getLength() {
        return length;
    }
    
    public void setLength(int length) {
        this.length = length;
    }

    public int getSequence() {
        return sequence;
    }
    
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public byte[] getRawBody() {
        return rawBody;
    }
    
    public void setRawBody(byte[] rawBody) {
        this.rawBody = rawBody;
    }
}
