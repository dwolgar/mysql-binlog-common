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

import java.math.BigDecimal;

public final class DecimalColumn extends Column {
    private static final long serialVersionUID = 3991981843076262734L;
    
    private final BigDecimal value;
    private final int precision;
    private final int scale;

    public DecimalColumn(int type, BigDecimal value, int precision, int scale) {
        super(type);
        this.value = value;
        this.scale = scale;
        this.precision = precision;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public int getPrecision() {
        return precision;
    }

    public int getScale() {
        return scale;
    }

    @Override
    public String toString() {
        return "DecimalColumn [value=" + value + ", precision=" + precision + ", scale=" + scale + "]";
    }

}
