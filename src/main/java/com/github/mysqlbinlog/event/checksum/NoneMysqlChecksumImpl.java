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

import com.github.mysql.io.MysqlBinlogByteArrayInputStream;

public class NoneMysqlChecksumImpl implements MysqlChecksum {

    /* (non-Javadoc)
     * @see java.util.zip.Checksum#update(int)
     */
    @Override
    public void update(int value) {
    }

    /* (non-Javadoc)
     * @see java.util.zip.Checksum#update(byte[], int, int)
     */
    @Override
    public void update(byte[] array, int off, int len) {
    }

    /* (non-Javadoc)
     * @see java.util.zip.Checksum#getValue()
     */
    @Override
    public long getValue() {
        return 0;
    }

    /* (non-Javadoc)
     * @see java.util.zip.Checksum#reset()
     */
    @Override
    public void reset() {
    }

    /* (non-Javadoc)
     * @see com.github.mysqlbinlog.event.checksum.MysqlChecksum#getType()
     */
    @Override
    public MysqlChecksumType getType() {
        return MysqlChecksumType.NONE;
    }

    /* (non-Javadoc)
     * @see com.github.mysqlbinlog.event.checksum.MysqlChecksum#getSize()
     */
    @Override
    public int getSize() {
        return 0;
    }

    /* (non-Javadoc)
     * @see com.github.mysqlbinlog.event.checksum.MysqlChecksum#validateAndReset(java.lang.Object)
     */
    @Override
    public boolean validate() throws IllegalStateException {
        return true;
    }

    /* (non-Javadoc)
     * @see com.github.mysqlbinlog.event.checksum.MysqlChecksum#readChecksum(com.github.mysqlbinlog.io.MysqlBinlogByteArrayInputStream)
     */
    @Override
    public void readChecksum(MysqlBinlogByteArrayInputStream is) {
    }

    /* (non-Javadoc)
     * @see com.github.mysqlbinlog.event.checksum.MysqlChecksum#getExpectedChecksum()
     */
    @Override
    public Object getExpectedValue() {
        return null;
    }

}
