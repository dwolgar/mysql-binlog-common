package com.github.mysqlbinlog.event.parser;

import java.io.IOException;

import com.github.mysqlbinlog.common.model.event.BinlogEventHeader;
import com.github.mysqlbinlog.io.MysqlBinlogByteArrayInputStream;

public interface BinlogEventHeaderParser {
	public BinlogEventHeader parse(MysqlBinlogByteArrayInputStream in) throws IOException;
}
