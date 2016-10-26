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

public final class NullColumn extends Column {
    private static final long serialVersionUID = -3548523546268990115L;
    private final int columnType;

    public NullColumn(int type, int columnType) {
        super(type);
        this.columnType = columnType;
    }

    public int getColumnType() {
        return this.columnType;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public String toString() {
        return "NullColumn [type=" + this.columnType + "]";
    }

}
