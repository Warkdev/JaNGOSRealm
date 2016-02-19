/*
 * Copyright 2016 Warkdev.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.jangos.realm.utils;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * BigInteger wrapper used for the authentication (SRP6 protocol)
 *
 * @author Warkdev.
 */
public class BigNumber {

    private BigInteger bigInteger;

    public BigNumber(byte[] val) {
        if (val[0] < 0) {
            byte[] tmp = new byte[val.length + 1];
            System.arraycopy(val, 0, tmp, 1, val.length);
            val = tmp;
        }
        this.bigInteger = new BigInteger(val);
    }

    public BigNumber(String val, int radix) {
        bigInteger = new BigInteger(val, radix);
    }

    public BigNumber(BigInteger val) {
        bigInteger = val;
    }

    public BigNumber(String val) {
        bigInteger = new BigInteger(val, 16);
    }

    public BigNumber() {
        bigInteger = null;
    }

    public BigInteger getInt() {
        return bigInteger.abs();
    }

    static public BigNumber valueOf(long val) {
        return new BigNumber(BigInteger.valueOf(val));
    }

    public BigNumber multiply(BigNumber val) {
        return new BigNumber(bigInteger.multiply(val.getInt()));
    }

    public BigNumber substract(BigNumber val) {
        return new BigNumber(bigInteger.subtract(val.getInt()));
    }
    
    public BigNumber remainder(BigNumber val) {
        return new BigNumber(bigInteger.remainder(val.getInt()));
    }

    public BigNumber add(BigNumber val) {
        return new BigNumber(bigInteger.add(val.getInt()));
    }

    public BigNumber modPow(BigNumber val1, BigNumber val2) {
        return new BigNumber(bigInteger.modPow(val1.getInt(), val2.getInt()));
    }

    public BigNumber setRand(int amount) {
        bigInteger = new BigInteger(1, new SecureRandom().generateSeed(amount));
        return this;
    }

    public String toHexString() {
        return bigInteger.toString(16).toUpperCase();
    }

    public byte[] asByteArray() {
        return asByteArray(0, true);
    }

    public byte[] asByteArray(int reqSize) {
        return asByteArray(reqSize, true);
    }
    
    public byte[] asByteArray(int reqSize, boolean reverse) {
        byte[] array = this.bigInteger.toByteArray();

        if (array[0] == 0) {
            byte[] tmp = new byte[array.length - 1];
            System.arraycopy(array, 1, tmp, 0, tmp.length);
            array = tmp;
        }

        int length = array.length;
        if(reverse){
            for (int i = 0; i < length / 2; i++) {
                byte j = array[i];
                array[i] = array[length - 1 - i];
                array[length - 1 - i] = j;
            }
        }

        if (reqSize > length) {
            byte[] newArray = new byte[reqSize];
            System.arraycopy(array, 0, newArray, 0, length);
            return newArray;
        }

        return array;
    }   
    
    public String toASCII() {
        String hex = bigInteger.toString(16).toUpperCase();
        if (hex.length() % 2 != 0) {
            System.err.println("requires EVEN number of chars");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        //Convert Hex 0232343536AB into two characters stream.
        for (int i = 0; i < hex.length() - 1; i += 2) {
            /*
             * Grab the hex in pairs
             */
            String output = hex.substring(i, (i + 2));
            /*
             * Convert Hex to Decimal
             */
            int decimal = Integer.parseInt(output, 16);
            sb.append((char) decimal);
        }
        return sb.toString();
    }

    public void setBinary(byte[] array) {
        setBinary(array, true);
    }

    public void setBinary(byte[] array, boolean reverse) {
        if (reverse) {
            // Reverse array		
            int length = array.length;
            for (int i = 0; i < length / 2; i++) {
                byte j = array[i];
                array[i] = array[length - 1 - i];
                array[length - 1 - i] = j;
            }
        }

        // Add the first byte indicates the sign of the BigInteger
        if (array[0] < 0) {
            byte[] tmp = new byte[array.length + 1];
            System.arraycopy(array, 0, tmp, 1, array.length);
            array = tmp;
        }

        bigInteger = new BigInteger(array);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        byte[] array = asByteArray();
        result.append("(").append(array.length).append(")");
        for (int i = 0; i < array.length; i++) {
            result.append(array[i]);
            result.append(" ");
        }
        return result.toString();
    }

    public String toCharString() {
        StringBuilder result = new StringBuilder();
        byte[] array = asByteArray();
        result.append("(").append(array.length).append(")");
        for (int i = 0; i < array.length; i++) {
            result.append((char) array[i]);
            result.append(" ");
        }
        return result.toString();
    }

    public byte[] getBytes() {
        return bigInteger.toByteArray();
    }

    public boolean equals(BigNumber b) {
        return (b.toHexString().equals(this.toHexString()));
    }
}
