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

import com.github.mysql.constant.MysqlConstants;
import com.github.mysql.io.MysqlBinlogByteArrayOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;

public class AuthenticateCmdPacket implements CmdPacket {
    public static String DEFAULT_ENCODING = "utf-8";
    
    private final String schema;
    private final String username;
    private final String password;
    private final String salt;
    private final String encoding;
    private final int clientCapabilities;
    private final int collation;
    
    public AuthenticateCmdPacket(String schema, String username, String password, String encoding, String salt, 
            int clientCapabilities, int collation) {
        this.schema   = schema;
        this.username = username;
        this.password = password;
        this.encoding = (encoding == null || encoding.length() == 0 ? AuthenticateCmdPacket.DEFAULT_ENCODING : encoding);
        this.salt = salt;
        if (clientCapabilities == 0) {
            int newClientCapabilities = MysqlConstants.CLIENT_LONG_PASSWORD
                                      | MysqlConstants.CLIENT_PROTOCOL_41
                                      | MysqlConstants.CLIENT_SECURE_CONNECTION;
            if (schema != null) {
                newClientCapabilities |= MysqlConstants.CLIENT_CONNECT_WITH_DB;
            }

            this.clientCapabilities = newClientCapabilities;
        } else {
            this.clientCapabilities = clientCapabilities;
        }
        
        this.collation = collation;
    }

    private static byte[] concat(byte[] array1, byte[] array2) {
        byte[] result = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, result, 0, array1.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        
        return result;
    }
    
    private static byte[] xor(byte[] array1, byte[] array2) {
        byte[] result = new byte[array1.length];
        
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte)(array1[i] ^ array2[i]);
        }
        
        return result;
    }

    
    private static byte[] generatePasswordMySQL411(String password, String salt, String encoding) {
        MessageDigest sha;
        try {
            sha = MessageDigest.getInstance("SHA-1");
            
            byte[] stage1 = sha.digest(password.getBytes(encoding));
            byte[] stage2 = sha.digest(concat(salt.getBytes(encoding), sha.digest(stage1)));
            
            return xor(stage1, stage2);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] getBody() throws IOException {
        MysqlBinlogByteArrayOutputStream os = new MysqlBinlogByteArrayOutputStream(new ByteArrayOutputStream());
        
        os.writeInteger(this.clientCapabilities, 4);
        os.writeInteger(0, 4);
        os.writeInteger(this.collation, 1);
        for (int i = 0; i < 23; i++) {
            os.write(0);
        }
        os.write(this.username.getBytes(this.encoding));
        os.write(0);
    
        byte[] passwordData = generatePasswordMySQL411(this.password, this.salt, this.encoding);
        os.writeInteger(passwordData.length, 1);
        os.write(passwordData);
        
        os.close();
        
        return ((ByteArrayOutputStream)(os.getOutputStream())).toByteArray();
    }

    public String getSchema() {
        return schema;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public int getClientCapabilities() {
        return clientCapabilities;
    }

    public int getCollation() {
        return collation;
    }

    public String getEncoding() {
        return encoding;
    }

    @Override
    public String toString() {
        return "AuthenticateCmdPacket [schema=" + schema + ", username="
                + username + ", password=" + password + ", salt=" + salt
                + ", encoding=" + encoding + ", clientCapabilities="
                + clientCapabilities + ", collation=" + collation + "]";
    }
}
