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

package com.github.mysqlbinlog.model.event;

import com.github.mysqlbinlog.model.variable.UserVariable;



/**
 * USER_VAR_EVENT
 * 
 * Written every time a statement uses a user variable; precedes other events for the statement. 
 * Indicates the value to use for the user variable in the next statement. 
 * This is written only before a QUERY_EVENT and is not used with row-based logging.
 */
public final class UserVarEvent extends BinlogEvent {
    private static final long serialVersionUID = -8924783249806728611L;

    private String varName;
    private int isNull;
    private int varType;
    private int varCollation;
    private int varValueLength;
    private UserVariable varValue;

    public UserVarEvent() {}

    public UserVarEvent(BinlogEventHeader header, byte[] rawData) {
        super(header, rawData);
    }

    public String getVarName() {
        return varName;
    }
    public void setVarName(String varName) {
        this.varName = varName;
    }

    public int getIsNull() {
        return isNull;
    }
    public void setIsNull(int isNull) {
        this.isNull = isNull;
    }

    public int getVarType() {
        return varType;
    }
    public void setVarType(int variableType) {
        this.varType = variableType;
    }

    public int getVarCollation() {
        return varCollation;
    }
    public void setVarCollation(int varCollation) {
        this.varCollation = varCollation;
    }

    public int getVarValueLength() {
        return varValueLength;
    }
    public void setVarValueLength(int varValueLength) {
        this.varValueLength = varValueLength;
    }

    public UserVariable getVarValue() {
        return varValue;
    }
    public void setVarValue(UserVariable varValue) {
        this.varValue = varValue;
    }
    
    @Override
    public String toString() {
        return "UserVarEvent [varName=" + varName + ", isNull=" + isNull
                + ", varType=" + varType + ", varCollation=" + varCollation
                + ", varValueLength=" + varValueLength + ", varValue="
                + varValue + "]";
    }

}
