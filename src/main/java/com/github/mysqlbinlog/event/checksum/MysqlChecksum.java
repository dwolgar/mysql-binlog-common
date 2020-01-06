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

import java.util.zip.Checksum;

import com.github.mysql.io.MysqlBinlogByteArrayInputStream;

public interface MysqlChecksum extends Checksum {

    public enum MysqlChecksumType {
        NONE, CRC32
    }

    public MysqlChecksumType getType();
    
    public int getSize();
    
    public void readChecksum(MysqlBinlogByteArrayInputStream is);
    
    public Object getExpectedValue();
    
    public boolean validate();

}
