package com.github.mysqlbinlog.io;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.BitSet;

public class MysqlBinlogByteArrayInputStream extends InputStream {
	private final InputStream inputStream;
	
	public MysqlBinlogByteArrayInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public MysqlBinlogByteArrayInputStream(byte[] array) {
		this.inputStream = new ByteArrayInputStream(array);
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
		int b = this.read();
		if (b < 251) {
			return b;
		} else if (b == 251) {
			return null;
		} else if (b == 252) {
			return (long) readInt(2, true);
		} else if (b == 253) {
			return (long) readInt(3, true);
		} else if (b == 254) {
			return readLong(8, true);
		}
		
		throw new IOException("Unexpected value [" + b + "]");
	}
	
	public String readString(int length) throws IOException {
        return new String(read(length));
	}
	
	public String readLengthEncodedString() throws IOException {
		return readString(readMysqlPackedNumber().intValue());
	}
	
	public String readNullTerminatedString() throws IOException {
        ByteArrayOutputStream s = new ByteArrayOutputStream();
        for (int b; (b = this.read()) != 0; ) {
            s.write(b);
        }
        return new String(s.toByteArray());
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
            int j = bytes.length - 1 - i;
            byte t = bytes[i];
            bytes[i] = bytes[j];
            bytes[j] = t;
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

	public InputStream getInputStream() {
		return inputStream;
	}
}
