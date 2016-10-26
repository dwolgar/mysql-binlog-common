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

package com.github.mysqlbinlog.model.variable;


public class QAutoIncrement extends StatusVariable {
    private static final long serialVersionUID = -2189013512878709870L;
    private final int autoIncrementIncrement;
    private final int autoIncrementOffset;

    public QAutoIncrement(int autoIncrementIncrement, int autoIncrementOffset) {
        this.autoIncrementIncrement = autoIncrementIncrement;
        this.autoIncrementOffset = autoIncrementOffset;
    }

    public int getAutoIncrementIncrement() {
        return autoIncrementIncrement;
    }

    public int getAutoIncrementOffset() {
        return autoIncrementOffset;
    }
    
    @Override
    public String toString() {
        return "QAutoIncrement [autoIncrementIncrement=" + autoIncrementIncrement + "], autoIncrementOffset [" + autoIncrementOffset + "]";
    }

}
