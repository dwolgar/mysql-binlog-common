package com.github.mysqlbinlog.event.parser;

import java.io.IOException;

import com.github.mysqlbinlog.common.model.event.BinlogEvent;
import com.github.mysqlbinlog.io.MysqlBinlogByteArrayInputStream;

public interface BinlogEventParser {
	public BinlogEvent parse(MysqlBinlogByteArrayInputStream is)  throws IOException;
}
