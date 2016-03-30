package com.github.mysqlbinlog.event.parser;

import java.io.IOException;

import com.github.mysqlbinlog.common.model.event.BinlogEventHeader;
import com.github.mysqlbinlog.io.MysqlBinlogByteArrayInputStream;

public class BinlogEventHeaderParserImpl implements BinlogEventHeaderParser {

	public BinlogEventHeader parse(MysqlBinlogByteArrayInputStream is) throws IOException {
		BinlogEventHeader header = new BinlogEventHeader();
		
		header.setTimestamp(is.readLong(4, true) * 1000l);
		header.setEventType(is.readInt(1, true));
		header.setServerId(is.readLong(4, true));
		header.setEventLength(is.readLong(4, true));
		header.setNextPosition(is.readLong(4, true));
		header.setFlags(is.readInt(2, true));
		
		return header;
	}

}
