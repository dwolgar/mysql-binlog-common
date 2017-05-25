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

package com.github.mysql.io;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;

public class MysqlBinlogByteArrayInputStream extends InputStream {
    private InputStream inputStream;

    public MysqlBinlogByteArrayInputStream() {
    }

    public MysqlBinlogByteArrayInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public byte[] read(int length) throws IOException {
        byte[] bytes = new byte[length];

        int remaining = length;
        int offset = 0;
        while (remaining != 0) {
            int read = read(bytes, offset + length - remaining, remaining);
            if (read == -1) {
                throw new EOFException();
            }
            remaining -= read;
        }

        return bytes;
    }

    public int readInt(int length, boolean littleEndian) throws IOException {
        int result = 0;
        for (int i = 0; i < length; ++i) {
            final int temp = this.read();
            if (littleEndian) {
                result |= (temp << (i << 3));
            } else {
                result = (result << 8) | temp;
            }
        }
        return result;
    }

    public long readLong(int length, boolean littleEndian) throws IOException {
        long result = 0;
        for (int i = 0; i < length; ++i) {
            final long temp = this.read();
            if (littleEndian) {
                result |= (temp << (i << 3));
            } else {
                result = (result << 8) | temp;
            }
        }
        return result;
    }

    public Number readMysqlPackedNumber() throws IOException {
        int value = this.read();
        if (value < 251) {
            return value;
        } else if (value == 251) {
            return null;
        } else if (value == 252) {
            return (long) readInt(2, true);
        } else if (value == 253) {
            return (long) readInt(3, true);
        } else if (value == 254) {
            return readLong(8, true);
        }

        throw new IOException("Unexpected value [" + value + "]");
    }

    public String readString(int length) throws IOException {
        return new String(read(length));
    }

    public String readLengthEncodedString() throws IOException {
        return readString(readMysqlPackedNumber().intValue());
    }

    public String readNullTerminatedString() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int b; (b = this.read()) != 0;) {
            out.write(b);
        }
        return new String(out.toByteArray());
    }

    public BitSet readBitSet(int length, boolean bigEndian) throws IOException {
        byte[] bytes = read((length + 7) >> 3);
        bytes = bigEndian ? bytes : reverse(bytes);
        BitSet result = new BitSet();
        for (int i = 0; i < length; i++) {
            if ((bytes[i >> 3] & (1 << (i % 8))) != 0) {
                result.set(i);
            }
        }
        return result;
    }

    private byte[] reverse(byte[] bytes) {
        for (int i = 0, length = bytes.length >> 1; i < length; i++) {
            int index = bytes.length - 1 - i;
            byte temp = bytes[i];
            bytes[i] = bytes[index];
            bytes[index] = temp;
        }
        return bytes;
    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }

    @Override
    public int available() throws IOException {
        return inputStream.available();
    }

    public InputStream getInputStream() {
        return inputStream;
    }
    
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}
