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

package com.github.mysqlbinlog.model.event.extra;

import java.io.Serializable;
import java.util.Arrays;

public class ColumnExtraData implements Serializable {
    private static final long serialVersionUID = -7293709242196558208L;
    
    private final String name;
    private String[] valueSet;

    public ColumnExtraData(String name, String columnType) {
        this.name = name;
        
        if (columnType.startsWith("enum")) {
            this.valueSet = columnType.substring(6, columnType.length() - 2).split("','");
        }
        else if (columnType.startsWith("set")) {
            this.valueSet = columnType.substring(5, columnType.length() - 2).split("','");
        }
        else {
            valueSet = null;
        }
    }
    
    public String getName() {
        return name;
    }

    public String[] getValueSet() {
        return valueSet;
    }

    @Override
    public String toString() {
        return "ColumnMetaData [name=" + name + ", valueSet="
                + Arrays.toString(valueSet) + "]";
    }
}
