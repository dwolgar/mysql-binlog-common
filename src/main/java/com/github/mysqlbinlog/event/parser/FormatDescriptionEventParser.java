package com.github.mysqlbinlog.event.parser;

import java.io.IOException;

import com.github.mysqlbinlog.common.model.event.BinlogEvent;
import com.github.mysqlbinlog.common.model.event.FormatDescriptionEvent;
import com.github.mysqlbinlog.io.MysqlBinlogByteArrayInputStream;

public class FormatDescriptionEventParser implements BinlogEventParser {

	public BinlogEvent parse(MysqlBinlogByteArrayInputStream is) throws IOException {
		FormatDescriptionEvent event = new FormatDescriptionEvent();
		event.setBinlogVersion(is.readInt(2, true));
		event.setServerVersion(is.readString(50).trim());
		event.setCreateTimestamp(is.readLong(4, true) * 1000L);
		event.setHeaderLength(is.readInt(1, true));
	    event.setEventTypes(is.read(is.available()));
	    
		return event;
	}

}
