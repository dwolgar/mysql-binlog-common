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

public final class EnumColumn extends Column {
    private static final long serialVersionUID = 1349490898147876486L;
    private final int value;
    private final String stringValue;

    public EnumColumn(String name, int type, int value, String stringValue) {
        super(name, type);
        this.value = value;
        this.stringValue = stringValue;
    }

    public Integer getValue() {
        return this.value;
    }
    
    public String getStringValue() {
        return this.stringValue;
    }

    @Override
    public String toString() {
        return "EnumColumn [value=" + value + ", stringValue=" + stringValue
                + ", getName()=" + getName() + "]";
    }
}
