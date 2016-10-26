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

package com.github.mysqlbinlog.event.checksum;

public class MysqlChecksumFactory {
    public static MysqlChecksum create(String config) {
        if (null == config || config.length() == 0) {
            return new NoneMysqlChecksumImpl();
        }
        if ("NONE".equalsIgnoreCase(config)) { 
            return new NoneMysqlChecksumImpl();
        }
        if ("CRC32".equalsIgnoreCase(config)) { 
            return new Crc32MysqlChecksumImpl();
        }

        throw new UnsupportedOperationException("The checksum algorithm [" + config + "] is not supported.");
    }
}
