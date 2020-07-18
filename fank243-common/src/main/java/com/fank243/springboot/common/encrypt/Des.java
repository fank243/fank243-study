package com.fank243.springboot.common.encrypt;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * Des 加密算法
 *
 * @author FanWeiJie
 * @date 2018/8/18 12:03
 **/
class Des {
    /**
     * DES 加密算法
     * 
     * @param src 源字符串
     * @param key 加密KEY
     * @return 密文
     * @throws Exception Exception
     */
    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();

        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(dks);

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(1, secretKey, sr);

        return cipher.doFinal(src);
    }

    /**
     * DES 解密算法
     *
     * @param src 密文
     * @param key 加密KEY
     * @return 明文
     * @throws Exception Exception
     */
    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();

        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(dks);

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(2, secretKey, sr);

        return cipher.doFinal(src);
    }
}
