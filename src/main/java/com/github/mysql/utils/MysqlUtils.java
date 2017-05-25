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

package com.github.mysql.utils;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public final class MysqlUtils {
    private static final int DIGITS_PER_4BYTES = 9;
    private static final BigDecimal POSITIVE_ONE = BigDecimal.ONE;
    private static final BigDecimal NEGATIVE_ONE = new BigDecimal("-1");
    private static final int DECIMAL_BINARY_SIZE [] = {0, 1, 1, 2, 2, 3, 3, 4, 4, 4};

    public static int toYear(int value) {
        return 1900 + value;
    }

    public static Date toDate(int value) {
        final int d = value % 32;
        value >>>= 5;
        final int m = value % 16;
        final int y = value >> 4;
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(y, m - 1, d);
        return cal.getTime();
    }

    public static Date toTime(int value) {
        final int s = (int) (value % 100);
        value /= 100;
        final int m = (int) (value % 100);
        final int h = (int) (value / 100);
        final Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 1, h, m, s);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date toTime2(int value, int nanos) {
        final int h = (value >> 12) & 0x3FF;
        final int m = (value >> 6) & 0x3F;
        final int s = (value >> 0) & 0x3F;
        final Calendar cal = Calendar.getInstance();
        cal.set(1970, 0, 1, h, m, s);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date toDatetime(long value) {
        final int second = (int) (value % 100);
        value /= 100;
        final int minute = (int) (value % 100);
        value /= 100;
        final int hour = (int) (value % 100);
        value /= 100;
        final int day = (int) (value % 100);
        value /= 100;
        final int month = (int) (value % 100);
        final int year = (int) (value / 100);
        final Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, hour, minute, second);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date toDatetime2(long value, int nanos) {
        final long x = (value >> 22) & 0x1FFFFL;
        final int year = (int) (x / 13);
        final int month = (int) (x % 13);
        final int day = ((int) (value >> 17)) & 0x1F;
        final int hour = ((int) (value >> 12)) & 0x1F;
        final int minute = ((int) (value >> 6)) & 0x3F;
        final int second = ((int) (value >> 0)) & 0x3F;
        final Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, hour, minute, second);
        cal.set(Calendar.MILLISECOND, 0);
        final long millis = cal.getTimeInMillis();
        return new Date(millis + (nanos / 1000000));
    }

    public static Date toTimestamp(long seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(seconds * 1000l);

        return cal.getTime();
    }

    public static Date toTimestamp2(long seconds, int nanos) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(seconds * 1000l);
        final long millis = cal.getTimeInMillis();

        return new Date(millis + (nanos / 1000000));
    }

    public static BigDecimal toDecimal(int precision, int scale, byte[] value) {
        final boolean positive = (value[0] & 0x80) == 0x80;
        value[0] ^= 0x80;
        if (!positive) {
            for (int i = 0; i < value.length; i++) {
                value[i] ^= 0xFF;
            }
        }

        final int x = precision - scale;
        final int ipDigits = x / DIGITS_PER_4BYTES;
        final int ipDigitsX = x - ipDigits * DIGITS_PER_4BYTES;
        final int ipSize = (ipDigits << 2) + DECIMAL_BINARY_SIZE[ipDigitsX];
        int offset = DECIMAL_BINARY_SIZE[ipDigitsX];
        BigDecimal ip = offset > 0 ? BigDecimal.valueOf(toInt(value, 0, offset)) : BigDecimal.ZERO;
        for (; offset < ipSize; offset += 4) {
            final int i = toInt(value, offset, 4);
            ip = ip.movePointRight(DIGITS_PER_4BYTES).add(BigDecimal.valueOf(i));
        }

        //
        int shift = 0;
        BigDecimal fp = BigDecimal.ZERO;
        for (; shift + DIGITS_PER_4BYTES <= scale; shift += DIGITS_PER_4BYTES, offset += 4) {
            final int i = toInt(value, offset, 4);
            fp = fp.add(BigDecimal.valueOf(i).movePointLeft(shift + DIGITS_PER_4BYTES));
        }
        if (shift < scale) {
            final int i = toInt(value, offset, DECIMAL_BINARY_SIZE[scale - shift]);
            fp = fp.add(BigDecimal.valueOf(i).movePointLeft(scale));
        }

        //
        return positive ? POSITIVE_ONE.multiply(ip.add(fp)) : NEGATIVE_ONE.multiply(ip.add(fp));
    }


    public static int getDecimalBinarySize(int precision, int scale) {
        final int x = precision - scale;
        final int ipDigits = x / DIGITS_PER_4BYTES;
        final int fpDigits = scale / DIGITS_PER_4BYTES;
        final int ipDigitsX = x - ipDigits * DIGITS_PER_4BYTES;
        final int fpDigitsX = scale - fpDigits * DIGITS_PER_4BYTES;
        return (ipDigits << 2) + DECIMAL_BINARY_SIZE[ipDigitsX] + (fpDigits << 2) + DECIMAL_BINARY_SIZE[fpDigitsX];
    }

    public static int toInt(byte[] data, int offset, int length) {
        int value = 0;
        for (int i = offset; i < (offset + length); i++) {
            final byte b = data[i];
            value = (value << 8) | (b >= 0 ? (int) b : (b + 256));
        }
        return value;
    }
}
