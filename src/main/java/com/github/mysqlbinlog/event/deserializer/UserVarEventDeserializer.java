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
import com.github.mysqlbinlog.model.event.BinlogEvent;
import com.github.mysqlbinlog.model.event.UserVarEvent;
import com.github.mysqlbinlog.model.variable.DecimalUserVariable;
import com.github.mysqlbinlog.model.variable.IntUserVariable;
import com.github.mysqlbinlog.model.variable.RealUserVariable;
import com.github.mysqlbinlog.model.variable.RowUserVariable;
import com.github.mysqlbinlog.model.variable.StringUserVariable;
import com.github.mysqlbinlog.model.variable.UserVariable;

import java.io.IOException;

public class UserVarEventDeserializer implements BinlogEventDeserializer<UserVarEvent> {

    public BinlogEvent deserialize(UserVarEvent event, MysqlBinlogByteArrayInputStream is, BinlogDeserializerContext context) throws IOException {

        int varNameLength = is.readInt(4, true);
        event.setVarName(is.readString(varNameLength));
        event.setIsNull(is.readInt(1, true));
        if (event.getIsNull() == 0) {
            event.setVarType(is.readInt(1, true));
            event.setVarCollation(is.readInt(4, true));
            event.setVarValueLength(is.readInt(4, true));
            event.setVarValue(parseUserVariable(is, event));
        }
        return event;
    }

    protected static UserVariable parseUserVariable(MysqlBinlogByteArrayInputStream is, UserVarEvent event) throws IOException {
        final int type = event.getVarType();
        switch (type) {
        case MysqlConstants.DECIMAL_RESULT:
            return new DecimalUserVariable(is.read(event.getVarValueLength()));
        case MysqlConstants.INT_RESULT:
            UserVariable var = new IntUserVariable(is.readLong(event.getVarValueLength(), true));
            is.readInt(1, true);
            return var;
        case MysqlConstants.REAL_RESULT:
            return new RealUserVariable(Double.longBitsToDouble(is.readLong(event.getVarValueLength(), true)));
        case MysqlConstants.ROW_RESULT:
            return new RowUserVariable(is.read(event.getVarValueLength()));
        case MysqlConstants.STRING_RESULT:
            return new StringUserVariable(event.getVarCollation(), new String(is.read(event.getVarValueLength())));
        default:
            return null;
        }
    }

}
