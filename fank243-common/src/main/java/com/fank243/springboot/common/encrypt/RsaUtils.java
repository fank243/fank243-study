package com.fank243.springboot.common.encrypt;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA 加密解密
 * 
 * 1. 首先服务器生成一个秘钥对(公钥和私钥), 然后将公钥交给客户端, 服务器端保存私钥
 * 
 * 2. 客户端通过公钥对JSON进行加密(加密方法pubEncrypt(pubKey,content))得到一个加密的串encryptString, 将这个encryptString传递给服务器
 * 
 * 3. 服务器拿到这个string之后, 首先进行签名验证，验证通过之后使用(priDecrypt(priKey,content))解密得到原始字符串
 * 
 * @author FanWeiJie
 * @date 2019-11-30 10:14:56
 */
public class RsaUtils {

    /** 非对称密钥算法 */
    private static final String KEY_ALGORITHM = "RSA";

    /** 签名算法 */
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /** 密钥长度 */
    private static final int KEY_SIZE = 1024;

    /** 密钥长度 注意明文长度是否超过密文长度减去11字节 */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /** 分块加密长度 */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /** 公钥类型 */
    private static final int PUBLIC_SECRET = 1;

    /** 私钥类型 */
    private static final int PRIVATE_SECRET = 2;

    /**
     * 获取密钥对
     *
     * @return 密钥对
     */
    private static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        // 随机数生成器
        SecureRandom random = new SecureRandom();
        // 设置秘钥的长度为 KEY_SIZE = 1024
        generator.initialize(KEY_SIZE, random);
        return generator.generateKeyPair();
    }

    /**
     * 获取私钥
     *
     * @param privateKey 私钥字符串
     * @return PrivateKey
     */
    private static String getPrivateKey(PrivateKey privateKey) {
        return new String(Base64Utils.encode(privateKey.getEncoded()));
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥字符串
     * @return PublicKey
     */
    private static String getPublicKey(PublicKey publicKey) {
        return new String(Base64Utils.encode(publicKey.getEncoded()));
    }

    /**
     * 私钥解密
     *
     * @param priKey 私钥
     * @param sign 密文
     * @return 明文
     */
    public static String priDecrypt(String priKey, String sign) {
        try {
            return processBlock(sign, priKey, PRIVATE_SECRET, Cipher.DECRYPT_MODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 公钥解密
     *
     * @param pubKey 公钥
     * @param sign 密文
     * @return 明文
     */
    public static String pubDecrypt(String pubKey, String sign) {
        try {
            return processBlock(sign, pubKey, PUBLIC_SECRET, Cipher.DECRYPT_MODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 公钥加密
     *
     * @param pubKey 公钥
     * @param plainTxt 源数据
     * @return 密文
     */
    public static String pubEncrypt(String pubKey, String plainTxt) {
        try {
            return processBlock(plainTxt, pubKey, PUBLIC_SECRET, Cipher.ENCRYPT_MODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 私钥加密
     *
     * @param priKey 私钥
     * @param plainTxt 源数据
     * @return 密文
     */
    public static String priEncrypt(String priKey, String plainTxt) {
        try {
            return processBlock(plainTxt, priKey, PRIVATE_SECRET, Cipher.ENCRYPT_MODE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 分段加密
     * 
     * @param plainTxt 明文
     * @param key 公钥或私钥
     * @param keyType 1:公钥，2：私钥
     * @param mode 加密模式，{@link Cipher}
     */
    private static String processBlock(String plainTxt, String key, int keyType, int mode) throws Exception {
        byte[] keyBytes = Base64Utils.decode(key);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        Key keySecret = null;
        if (keyType == PUBLIC_SECRET) {
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            keySecret = keyFactory.generatePublic(x509KeySpec);
        }
        if (keyType == PRIVATE_SECRET) {
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            keySecret = keyFactory.generatePrivate(pkcs8KeySpec);
        }
        int maxSize = (mode == Cipher.ENCRYPT_MODE ? MAX_ENCRYPT_BLOCK : MAX_DECRYPT_BLOCK);
        cipher.init(mode, keySecret);
        byte[] data =
            (mode == Cipher.ENCRYPT_MODE ? plainTxt.getBytes(StandardCharsets.UTF_8) : Base64Utils.decode(plainTxt));
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > maxSize) {
                cache = cipher.doFinal(data, offSet, maxSize);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * maxSize;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        if (mode == Cipher.ENCRYPT_MODE) {
            return new String(Base64.getEncoder().encode(encryptedData));
        }
        if (mode == Cipher.DECRYPT_MODE) {
            return new String(encryptedData);
        }
        return null;
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param priKey 私钥
     * @param plainTxt 明文
     */
    public static String addSign(String priKey, String plainTxt) {
        try {
            byte[] keyBytes = Base64Utils.decode(priKey);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateKey);
            signature.update(plainTxt.getBytes());
            return Base64Utils.encode(signature.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 用公钥校验数字签名
     *
     * @param pubKey 公钥
     * @param plainTxt 明文
     * @param sign 数字签名
     */
    public static boolean verifySign(String pubKey, String plainTxt, String sign) {
        try {
            byte[] keyBytes = Base64Utils.decode(pubKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(plainTxt.getBytes());
            return signature.verify(Base64Utils.decode(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        String content =
            "(version=1.1.0, apiVer=10, timestamp=1575172800000, signature=756a6fcdaf2a49207ad85672e3ccd244, deviceType=1, deviceNumber=ABCDEFGHIJKLMNOPA134D, payload={\"mobile\":\"13212345763\",\"verCode\":123456})";
         KeyPair keyPair = getKeyPair();
        String priKey = getPrivateKey(keyPair.getPrivate());
        String pubKey = getPublicKey(keyPair.getPublic());
        // String priKey =
        // "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIBPiu0/gAptfUD3dLcUCctlhYm9WI4l+3RQa2SVYUlM7P8HCec3RwIQfnTv5ISP/2FZR0TKsAHbWKzi+MyHkoWf04Tv4BOSWoqc2YJN4rdOcBffZB6+IeaynkvXZLTPpPLYUk0zh1qdNsMnv4nDGy3ZmbuvhXAAUJZdGsXSTe9VAgMBAAECgYBRd6+i4V2fwVL0y+lsUKnDXetCwFkYsB8PA/yb+YiyrMgRSNvN1bgdJHHWN3algHRK0A/DKtpD44xTAS2tVyr9BZIYWuyqzvrUJUYDuEJzDSg2duHi7uTljoqf+oV7NkaOhIKLiUY/Eq1FcufmMK9RzDXcmmuBXuaEQ11gM62QpQJBAOe7Rn4rl+LwqijTMvpPSH8RadZmjdY2CLitgDZF7MZ25LHIyaQv6Yn2ljeHH1pSaFpZTvd4TRnhX0LHEplHFfsCQQCNv43R6czHn/KmnpiGHYehaUBtNT6ohINgRPRf52UEc0TeMvCJCXEU8lMX5NZQzlFbvj6vsiiZynB5F4DXhx7vAkACLLC5qGERgR6nqWu2C55ritdKasXz96fXS9GDMN7K8fytamvx/X0cjVLVjJAPwew2xNXH3roifwnsz39uLsuXAkEAgZ3f+UtpzkEcifAMWNc35wlvuG0v1rkVyTpEnE3juaYOG4FJIoZXuWe9Q+Qldc6z5siYWeD372mGX7R2RJ9yUQJAC+rPcyDI7C9LuEvNfdcxO4K6ZQww8F1RqriGjWkoDme+MhKDchsLPi8iV76LvSfK8D8lCpUGG3nyjSQLhUqDbw==";
        // String pubKey =
        // "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCAT4rtP4AKbX1A93S3FAnLZYWJvViOJft0UGtklWFJTOz/BwnnN0cCEH507+SEj/9hWUdEyrAB21is4vjMh5KFn9OE7+ATklqKnNmCTeK3TnAX32QeviHmsp5L12S0z6Ty2FJNM4danTbDJ7+Jwxst2Zm7r4VwAFCWXRrF0k3vVQIDAQAB";

        System.out.println("公钥：" + pubKey);
        System.out.println("私钥：" + priKey);
        System.out.println("明文：" + content);

        System.out.println("\n========================公钥加密 》 私钥解密======================================");

        String pubSign = pubEncrypt(pubKey, content);
        System.out.println("公钥加密：" + pubSign);
        String txt =
            "Q1nIaHF350BXACYeelESTaBDy/Ynx1Hfjlv7lHBKxMl310oehOJ+E/6xUcST4cThu3E5B6pQUcLlbmgjSLdfmEKkJ8t6suFt0rAaTiWAGFclaBOKNJ5SR7e1MjPPNPq0d6RYicbhWipbG2q5aYP8uJFNi7X6YxpCGAMsl4yfeYg=";
        String priText = priDecrypt(priKey, txt);
        System.out.println("私钥解密：" + priText);

        System.out.println("\n========================私钥加密 》 公钥解密======================================");

        String priSign = priEncrypt(priKey, content);
        System.out.println("私钥加密：" + priSign);
        String pubText = pubDecrypt(pubKey, priSign);
        System.out.println("公钥解密：" + pubText);

        System.out.println("\n========================私钥加签 》 公钥验签======================================");
        priSign = addSign(priKey, content);
        System.out.println("私钥加签：" + priSign);
        boolean flag = verifySign(pubKey, content, priSign);
        System.out.println("公钥验签：" + flag);
    }

}
