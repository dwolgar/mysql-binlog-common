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

public final class StringColumn extends Column {
    private static final long serialVersionUID = 749371729285531226L;
    private final String value;

    public StringColumn(String name, int type, String value) {
        super(name, type);
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "StringColumn [value=" + value + ", getName()=" + getName()
                + "]";
    }
}
