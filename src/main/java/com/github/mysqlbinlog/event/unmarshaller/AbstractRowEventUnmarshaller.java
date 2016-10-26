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

import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import com.github.mysql.constant.MysqlConstants;
import com.github.mysql.io.MysqlBinlogByteArrayInputStream;
import com.github.mysql.utils.MysqlUtils;
import com.github.mysqlbinlog.model.event.BinlogEvent;
import com.github.mysqlbinlog.model.event.TableMapEvent;
import com.github.mysqlbinlog.model.event.extra.BitsetColumn;
import com.github.mysqlbinlog.model.event.extra.BlobColumn;
import com.github.mysqlbinlog.model.event.extra.Column;
import com.github.mysqlbinlog.model.event.extra.DatetimeColumn;
import com.github.mysqlbinlog.model.event.extra.DecimalColumn;
import com.github.mysqlbinlog.model.event.extra.DoubleColumn;
import com.github.mysqlbinlog.model.event.extra.EnumColumn;
import com.github.mysqlbinlog.model.event.extra.FloatColumn;
import com.github.mysqlbinlog.model.event.extra.IntegerColumn;
import com.github.mysqlbinlog.model.event.extra.LongLongColumn;
import com.github.mysqlbinlog.model.event.extra.Metadata;
import com.github.mysqlbinlog.model.event.extra.NullColumn;
import com.github.mysqlbinlog.model.event.extra.Row;
import com.github.mysqlbinlog.model.event.extra.SetColumn;
import com.github.mysqlbinlog.model.event.extra.StringColumn;


public abstract class AbstractRowEventUnmarshaller<E extends BinlogEvent> implements BinlogEventUnmarshaller<E> {

    public abstract BinlogEvent unmarshal(E event, MysqlBinlogByteArrayInputStream is, BinlogUnmarshallerContext context) throws IOException;

    protected Row parseRow(MysqlBinlogByteArrayInputStream is, TableMapEvent tme, BitSet usedColumns) throws IOException {
        int unusedColumnCount = 0;
        final byte[] types = tme.getColumnTypes();
        final Metadata metadata = tme.getColumnMetadata();
        final BitSet nullColumns = is.readBitSet(types.length, true);
        final List<Column> columns = new ArrayList<Column>(types.length);

        for (int i = 0; i < types.length; ++i) {
            int length = 0;
            final int meta = metadata.getMetadata(i);
            int type = types[i] & 0xFF;
            if (type == MysqlConstants.TYPE_STRING && meta > 256) {
                final int meta0 = meta >> 8;
                final int meta1 = meta & 0xFF;
                if ((meta0 & 0x30) != 0x30) { 
                    type = meta0 | 0x30;
                    length = meta1 | (((meta0 & 0x30) ^ 0x30) << 4);
                } else {
                    switch (meta0) {
                    case MysqlConstants.TYPE_SET:
                    case MysqlConstants.TYPE_ENUM:
                    case MysqlConstants.TYPE_STRING:
                        type = meta0;
                        length = meta1;
                        break;
                    default:
                        throw new RuntimeException("Unknown column type: " + type);
                    }
                }
            }

            if (!usedColumns.get(i)) {
                unusedColumnCount++;
                continue;
            } else if (nullColumns.get(i - unusedColumnCount)) {
                columns.add(new NullColumn(MysqlConstants.TYPE_NULL, type));
                continue;
            }

            switch (type) {
            case MysqlConstants.TYPE_TINY:
                columns.add(new IntegerColumn(type, is.readInt(1, true)));
                break;
            case MysqlConstants.TYPE_SHORT:
                columns.add(new IntegerColumn(type, is.readInt(2, true)));
                break;
            case MysqlConstants.TYPE_INT24:
                columns.add(new IntegerColumn(type, is.readInt(3, true)));
                break;
            case MysqlConstants.TYPE_LONG:
                columns.add(new IntegerColumn(type, is.readInt(4, true)));
                break;
            case MysqlConstants.TYPE_LONGLONG:
                columns.add(new LongLongColumn(type, is.readLong(8, true)));
                break;

            case MysqlConstants.TYPE_FLOAT:
                columns.add(new FloatColumn(type, Float.intBitsToFloat(is.readInt(4, true))));
                break;
            case MysqlConstants.TYPE_DOUBLE:
                columns.add(new DoubleColumn(type, Double.longBitsToDouble(is.readLong(8, true))));
                break;

            case MysqlConstants.TYPE_YEAR:
                columns.add(new IntegerColumn(type, 1900 + is.readInt(1, true)));
                break;
            case MysqlConstants.TYPE_DATE: 
                columns.add(new DatetimeColumn(type, MysqlUtils.toDate(is.readInt(3, true))));
                break;
            case MysqlConstants.TYPE_TIME:
                columns.add(new DatetimeColumn(type, MysqlUtils.toTime(is.readInt(3, true))));
                break;
            case MysqlConstants.TYPE_DATETIME:
                columns.add(new DatetimeColumn(type, MysqlUtils.toDatetime(is.readLong(8, true))));
                break;
            case MysqlConstants.TYPE_TIMESTAMP:
                columns.add(new DatetimeColumn(type, MysqlUtils.toTimestamp(is.readLong(4, true))));
                break;
            case MysqlConstants.TYPE_ENUM:
                columns.add(new EnumColumn(type, is.readInt(length, true)));
                break;
            case MysqlConstants.TYPE_SET:
                columns.add(new SetColumn(type, is.readLong(length, true)));
                break;
            case MysqlConstants.TYPE_BIT:
                final int bitLength = (meta >> 8) * 8 + (meta & 0xFF);
                columns.add(new BitsetColumn(type, is.readBitSet(bitLength, false)));
                break;
            case MysqlConstants.TYPE_BLOB:
                final int blobLength = is.readInt(meta, true);
                columns.add(new BlobColumn(type, is.read(blobLength)));
                break;
            case MysqlConstants.TYPE_NEWDECIMAL:
                final int precision = meta & 0xFF;
                final int scale = meta >> 8;
                final int decimalLength = MysqlUtils.getDecimalBinarySize(precision, scale);
                columns.add(new DecimalColumn(type, MysqlUtils.toDecimal(precision, scale, is.read(decimalLength)), precision, scale));
                break;
            case MysqlConstants.TYPE_STRING:
                final int stringLength = length < 256 ? is.readInt(1, true) : is.readInt(2, true);
                columns.add(new StringColumn(type, is.readString(stringLength)));
                break;
            case MysqlConstants.TYPE_VARCHAR:
            case MysqlConstants.TYPE_VAR_STRING:
                final int varcharLength = meta < 256 ? is.readInt(1, true) : is.readInt(2, true);
                columns.add(new StringColumn(type, is.readString(varcharLength)));
                break;
            case MysqlConstants.TYPE_TIME2:
                final int value1 = is.readInt(3, false);
                final int nanos1 = is.readInt((meta + 1) / 2, false);
                columns.add(new DatetimeColumn(type, MysqlUtils.toTime2(value1, nanos1)));
                break;
            case MysqlConstants.TYPE_DATETIME2:
                final long value2 = is.readLong(5, false);
                final int nanos2 = is.readInt((meta + 1) / 2, false);
                columns.add(new DatetimeColumn(type, MysqlUtils.toDatetime2(value2, nanos2)));
                break;
            case MysqlConstants.TYPE_TIMESTAMP2:
                final long value3 = is.readLong(4, false);
                final int nanos3 = is.readInt((meta + 1) / 2, false);
                columns.add(new DatetimeColumn(type, MysqlUtils.toTimestamp2(value3, nanos3)));
                break;
            default:
                throw new RuntimeException("Unknown column type: " + type);
            }
        }

        return new Row(columns);
    }
}
