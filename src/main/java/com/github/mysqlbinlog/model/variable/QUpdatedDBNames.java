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

import java.util.Arrays;

public class QUpdatedDBNames extends StatusVariable {
    private static final long serialVersionUID = -3391093668152198594L;
    private final int accessedDbCount;
    private final String[] accessedDbs;

    public QUpdatedDBNames(int accessedDbCount, String[] accessedDbs) {
        this.accessedDbCount = accessedDbCount;
        this.accessedDbs = accessedDbs;
    }

    public int getAccessedDbCount() {
        return accessedDbCount;
    }

    public String[] getAccessedDbs() {
        return accessedDbs;
    }

    @Override
    public String toString() {
        return "QUpdatedDBNames [accessedDbCount=" + accessedDbCount
                + ", accessedDbs=" + Arrays.toString(accessedDbs) + "]";
    }
}
