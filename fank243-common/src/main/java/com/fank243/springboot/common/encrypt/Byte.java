package com.fank243.springboot.common.encrypt;

public class Byte {
    static String byte2hex(byte[] buffer) {
        StringBuilder hs = new StringBuilder();
        String tmp;
        for (byte aBuffer : buffer) {
            tmp = Integer.toHexString(aBuffer & 255);
            if (tmp.length() == 1) {
                hs.append("0").append(tmp);
            } else {
                hs.append(tmp);
            }
        }

        return hs.toString().toUpperCase();
    }

    static byte[] hex2byte(byte[] buffer) {
        if (buffer.length % 2 != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        } else {
            byte[] b2 = new byte[buffer.length / 2];

            for (int n = 0; n < buffer.length; n += 2) {
                String item = new String(buffer, n, 2);
                b2[n / 2] = (byte) Integer.parseInt(item, 16);
            }

            return b2;
        }
    }
}
