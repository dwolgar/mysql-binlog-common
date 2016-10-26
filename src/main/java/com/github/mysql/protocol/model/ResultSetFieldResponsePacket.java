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

package com.github.mysql.protocol.model;

public class ResultSetFieldResponsePacket implements ResponsePacket {
    private static final long serialVersionUID = 6240782193900034983L;

    private String catalog;
    private String db;
    private String table;
    private String orginalTable;
    private String column;
    private String originalColumn;
    private int fixed12;
    private int charset;
    private long fieldLength;
    private int fieldType;
    private int fieldOptions;
    private int decimalPrecision;
    private int reserved;
    private String defaultValue;

    public ResultSetFieldResponsePacket() {

    }

    @Override
    public String toString() {
        return "MysqlResultSetFieldResponsePacket [catalog=" + catalog
                + ", db=" + db + ", table=" + table + ", orginalTable="
                + orginalTable + ", column=" + column + ", originalColumn="
                + originalColumn + ", fixed12=" + fixed12 + ", charset="
                + charset + ", fieldLength=" + fieldLength + ", fieldType="
                + fieldType + ", fieldOptions=" + fieldOptions
                + ", decimalPrecision=" + decimalPrecision + ", reserved="
                + reserved + ", defaultValue=" + defaultValue + "]";
    }



    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getOrginalTable() {
        return orginalTable;
    }

    public void setOrginalTable(String orginalTable) {
        this.orginalTable = orginalTable;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getOriginalColumn() {
        return originalColumn;
    }

    public void setOriginalColumn(String originalColumn) {
        this.originalColumn = originalColumn;
    }

    public int getFixed12() {
        return fixed12;
    }

    public void setFixed12(int fixed12) {
        this.fixed12 = fixed12;
    }

    public int getCharset() {
        return charset;
    }

    public void setCharset(int charset) {
        this.charset = charset;
    }

    public long getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(long fieldLength) {
        this.fieldLength = fieldLength;
    }

    public int getFieldType() {
        return fieldType;
    }

    public void setFieldType(int fieldType) {
        this.fieldType = fieldType;
    }

    public int getFieldOptions() {
        return fieldOptions;
    }

    public void setFieldOptions(int fieldOptions) {
        this.fieldOptions = fieldOptions;
    }

    public int getDecimalPrecision() {
        return decimalPrecision;
    }

    public void setDecimalPrecision(int decimalPrecision) {
        this.decimalPrecision = decimalPrecision;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
