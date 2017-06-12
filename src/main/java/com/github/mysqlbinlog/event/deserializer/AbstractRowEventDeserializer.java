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

package com.github.mysqlbinlog.event.deserializer;

import com.github.mysql.constant.MysqlConstants;
import com.github.mysql.io.MysqlBinlogByteArrayInputStream;
import com.github.mysql.utils.MysqlUtils;
import com.github.mysqlbinlog.model.event.BinlogEvent;
import com.github.mysqlbinlog.model.event.TableMapEvent;
import com.github.mysqlbinlog.model.event.extra.BitsetColumn;
import com.github.mysqlbinlog.model.event.extra.BlobColumn;
import com.github.mysqlbinlog.model.event.extra.Column;
import com.github.mysqlbinlog.model.event.extra.ColumnExtraData;
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

import java.io.IOException;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public abstract class AbstractRowEventDeserializer<E extends BinlogEvent> implements BinlogEventDeserializer<E> {

    public abstract BinlogEvent deserialize(E event, MysqlBinlogByteArrayInputStream is, BinlogDeserializerContext context) throws IOException;

    protected Row parseRow(MysqlBinlogByteArrayInputStream is, TableMapEvent tme, BitSet usedColumns) throws IOException {
        int unusedColumnCount = 0;
        final byte[] types = tme.getColumnTypes();
        final Metadata metadata = tme.getColumnMetadata();
        final List<ColumnExtraData> columnsExtraItems = tme.getColumnExtra();
        
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
            
            
            ColumnExtraData columnMetaData = null;
            String columnName = null;
            
            if (columnsExtraItems != null) {
                columnMetaData = columnsExtraItems.get(i);
                if (columnMetaData != null) {
                    columnName = columnMetaData.getName(); 
                }
            }

            if (!usedColumns.get(i)) {
                unusedColumnCount++;
                continue;
            } else if (nullColumns.get(i - unusedColumnCount)) {
                columns.add(new NullColumn(columnName, MysqlConstants.TYPE_NULL, type));
                continue;
            }

            switch (type) {
            case MysqlConstants.TYPE_TINY:
                columns.add(new IntegerColumn(columnName, type, is.readInt(1, true)));
                break;
            case MysqlConstants.TYPE_SHORT:
                columns.add(new IntegerColumn(columnName, type, is.readInt(2, true)));
                break;
            case MysqlConstants.TYPE_INT24:
                columns.add(new IntegerColumn(columnName, type, is.readInt(3, true)));
                break;
            case MysqlConstants.TYPE_LONG:
                columns.add(new IntegerColumn(columnName, type, is.readInt(4, true)));
                break;
            case MysqlConstants.TYPE_LONGLONG:
                columns.add(new LongLongColumn(columnName, type, is.readLong(8, true)));
                break;

            case MysqlConstants.TYPE_FLOAT:
                columns.add(new FloatColumn(columnName, type, Float.intBitsToFloat(is.readInt(4, true))));
                break;
            case MysqlConstants.TYPE_DOUBLE:
                columns.add(new DoubleColumn(columnName, type, Double.longBitsToDouble(is.readLong(8, true))));
                break;

            case MysqlConstants.TYPE_YEAR:
                columns.add(new IntegerColumn(columnName, type, 1900 + is.readInt(1, true)));
                break;
            case MysqlConstants.TYPE_DATE: 
                columns.add(new DatetimeColumn(columnName, type, MysqlUtils.toDate(is.readInt(3, true))));
                break;
            case MysqlConstants.TYPE_TIME:
                columns.add(new DatetimeColumn(columnName, type, MysqlUtils.toTime(is.readInt(3, true))));
                break;
            case MysqlConstants.TYPE_DATETIME:
                columns.add(new DatetimeColumn(columnName, type, MysqlUtils.toDatetime(is.readLong(8, true))));
                break;
            case MysqlConstants.TYPE_TIMESTAMP:
                columns.add(new DatetimeColumn(columnName, type, MysqlUtils.toTimestamp(is.readLong(4, true))));
                break;
            case MysqlConstants.TYPE_ENUM: {
                int value = is.readInt(length, true);
                String stringValue = null;
                if (columnMetaData != null) {
                    String[] valueSet = columnMetaData.getValueSet();
                    if (valueSet != null) {
                        stringValue = (value == 0 ? "" : valueSet[value - 1]);
                    }
                }
                columns.add(new EnumColumn(columnName, type, value, stringValue));
            }  break;
            case MysqlConstants.TYPE_SET: {
                long value = is.readLong(length, true);
                String stringValue = null;
                if (columnMetaData != null) {
                    String[] valueSet = columnMetaData.getValueSet();
                    List<String> maskedValues = new ArrayList<String>();
                    if (valueSet != null) {
                        for (int bits = 0; bits< valueSet.length; bits++) {
                            long binValue = ((long)0x01) << bits;
                            if ((binValue & value) > 0) {
                                maskedValues.add(valueSet[bits]);
                            }
                        }
                        stringValue = String.join(",", maskedValues);
                    }
                }
                columns.add(new SetColumn(columnName, type, value, stringValue));
            }   break;
            case MysqlConstants.TYPE_BIT:
                final int bitLength = (meta >> 8) * 8 + (meta & 0xFF);
                columns.add(new BitsetColumn(columnName, type, is.readBitSet(bitLength, false)));
                break;
            case MysqlConstants.TYPE_BLOB:
                final int blobLength = is.readInt(meta, true);
                columns.add(new BlobColumn(columnName, type, is.read(blobLength)));
                break;
            case MysqlConstants.TYPE_NEWDECIMAL:
                final int precision = meta & 0xFF;
                final int scale = meta >> 8;
                final int decimalLength = MysqlUtils.getDecimalBinarySize(precision, scale);
                columns.add(new DecimalColumn(columnName, type, MysqlUtils.toDecimal(precision, scale, is.read(decimalLength)), precision, scale));
                break;
            case MysqlConstants.TYPE_STRING:
                final int stringLength = length < 256 ? is.readInt(1, true) : is.readInt(2, true);
                columns.add(new StringColumn(columnName, type, is.readString(stringLength)));
                break;
            case MysqlConstants.TYPE_VARCHAR:
            case MysqlConstants.TYPE_VAR_STRING:
                final int varcharLength = meta < 256 ? is.readInt(1, true) : is.readInt(2, true);
                columns.add(new StringColumn(columnName, type, is.readString(varcharLength)));
                break;
            case MysqlConstants.TYPE_TIME2:
                final int value1 = is.readInt(3, false);
                final int nanos1 = is.readInt((meta + 1) / 2, false);
                columns.add(new DatetimeColumn(columnName, type, MysqlUtils.toTime2(value1, nanos1)));
                break;
            case MysqlConstants.TYPE_DATETIME2:
                final long value2 = is.readLong(5, false);
                final int nanos2 = is.readInt((meta + 1) / 2, false);
                columns.add(new DatetimeColumn(columnName, type, MysqlUtils.toDatetime2(value2, nanos2)));
                break;
            case MysqlConstants.TYPE_TIMESTAMP2:
                final long value3 = is.readLong(4, false);
                final int nanos3 = is.readInt((meta + 1) / 2, false);
                columns.add(new DatetimeColumn(columnName, type, MysqlUtils.toTimestamp2(value3, nanos3)));
                break;
            default:
                throw new RuntimeException("Unknown column type: " + type);
            }
        }

        return new Row(columns);
    }
}
