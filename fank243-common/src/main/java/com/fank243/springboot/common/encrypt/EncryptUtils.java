package com.fank243.springboot.common.encrypt;

import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 常用的加密算法
 * <p>
 * SHA1加解密算法、BASE64加解码、MD5加密算法、ABS加解密算法
 * 
 * @author FanWeiJie
 * @date 2016年3月31日
 */
public class EncryptUtils {

    /** AES KEY **/
    private static final String AES_KEY = "qQntpvDvkRjHLA76Y75sLk9zTwaKCVV6";
    /** DES KEY **/
    private static final String DES_KEY = "5PsdeHqUGdZDRRRyNVECoFTIaPHikgUC";

    /**
     * sha1加密算法
     * 
     * @param decript 待加密字符串
     * @author FanWeiJie
     * @date 2015年7月13日
     */
    public static String sha1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes(StandardCharsets.UTF_8.name()));
            byte[] messageDigest = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            // 字节数组转换为 十六进制 数
            for (byte aMessageDigest : messageDigest) {
                String shaHex = Integer.toHexString(aMessageDigest & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * sha1解密算法
     *
     * @param decript 待加密字符串
     * @author FanWeiJie
     * @date 2015年7月13日
     */
    public static String sha(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA");
            digest.update(decript.getBytes(StandardCharsets.UTF_8.name()));
            byte[] messageDigest = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            // 字节数组转换为 十六进制 数
            for (byte aMessageDigest : messageDigest) {
                String shaHex = Integer.toHexString(aMessageDigest & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * md5 32位加密算法
     *
     * @param input 待加密字符串
     * @author FanWeiJie
     * @date 2015年7月13日
     */
    public static String md5(String input) {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("md5");
            // 使用指定的字节更新摘要
            mdInst.update(input.getBytes(StandardCharsets.UTF_8.name()));
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            StringBuilder hexString = new StringBuilder();
            // 字节数组转换为 十六进制 数
            for (byte aMd : md) {
                String shaHex = Integer.toHexString(aMd & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * AES加密算法
     *
     * @param content 需要加密的内容
     */
    public static String encryptAES(String content) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(AES_KEY.getBytes(StandardCharsets.UTF_8.name())));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // 创建密码器
            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            return new String(cipher.doFinal(byteContent));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * AES加密算法
     *
     * @param content 需要加密的内容
     * @param password 加密密码
     */
    public static String encryptAES(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes(StandardCharsets.UTF_8.name())));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // 创建密码器
            byte[] byteContent = content.getBytes(StandardCharsets.UTF_8);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            return new String(cipher.doFinal(byteContent));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * AES解密算法
     *
     * @param content 待解密内容
     */
    public static String decryptAES(String content) {
        return decryptAES(content.getBytes(), AES_KEY);
    }

    /**
     * AES解密算法
     *
     * @param content 待解密内容
     * @param key KEY
     */
    public static String decryptAES(String content, String key) {
        return decryptAES(content.getBytes(), key);
    }

    /**
     * AES解密算法
     *
     * @param content 待解密内容
     * @param password 解密密钥
     */
    private static String decryptAES(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes(StandardCharsets.UTF_8.name())));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);

            return new String(cipher.doFinal(content));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * DES 加密
     *
     * @param input 明文
     * @return 密文
     */
    public static String encryptDES(String input) {
        return encryptDES(input, DES_KEY, StandardCharsets.UTF_8);
    }

    /**
     * DES 加密
     *
     * @param input 明文
     * @param key 加密KEY
     * @return 密文
     */
    public static String encryptDES(String input, String key) {
        return encryptDES(input, key, StandardCharsets.UTF_8);
    }

    /**
     * DES 加密
     *
     * @param input 明文
     * @param key 加密KEY
     * @param charset 编码方式
     * @return 密文
     */
    public static String encryptDES(String input, String key, Charset charset) {
        if (StringUtils.isBlank(input) || StringUtils.isBlank(key)) {
            return null;
        }
        try {
            return Byte.byte2hex(Des.encrypt(input.getBytes(charset), key.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * DES 解密
     *
     * @param input 密文
     * @return 明文
     */
    public static String decryptDES(String input) {
        return decryptDES(input, DES_KEY, StandardCharsets.UTF_8);
    }

    /**
     * DES 解密
     *
     * @param input 密文
     * @param key 加密KEY
     * @return 明文
     */
    public static String decryptDES(String input, String key) {
        return decryptDES(input, key, StandardCharsets.UTF_8);
    }

    /**
     * DES 解密
     *
     * @param input 密文
     * @param key 加密KEY
     * @param charset 编码方式
     * @return 明文
     */
    public static String decryptDES(String input, String key, Charset charset) {
        if (StringUtils.isBlank(input) || StringUtils.isBlank(key)) {
            return null;
        }
        try {
            return new String(Des.decrypt(Byte.hex2byte(input.getBytes()), key.getBytes()), charset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
        String str = 123 + "_" + System.currentTimeMillis();
        String s = encryptDES(str, "Z0m3FAQqwy2vteCE");

        System.out.println(s);
        System.out.println(s.length());

        String s1 = decryptDES(s.toLowerCase(), "Z0m3FAQqwy2vteCE");

        System.out.println(s1);
    }

}
