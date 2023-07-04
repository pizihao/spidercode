package com.example.server;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.lang.StringUtils;

import java.security.Key;

@SuppressWarnings({"unused", "java:S112"})
public class AesHelper {

    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 指定随机字符串（密码）生成密钥
     *
     * @param randomKey 加解密的密码
     */
    public static byte[] getSecretKey(String randomKey) throws NoSuchAlgorithmException {
        //秘钥生成器，指定秘钥算法
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);

        //初始化此密钥生成器，指定AES的秘钥长度为128
        if (StringUtils.isBlank(randomKey)) {
            keyGenerator.init(128);
        } else {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(randomKey.getBytes());
            keyGenerator.init(128, random);
        }
        //生成密钥
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    /**
     * 加密
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[]   加密数据
     */
    public static byte[] encrypt(byte[] data, Key key) {
        return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 加密
     *
     * @param data 待加密数据
     * @param key  二进制密钥
     * @return byte[]   加密数据
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 加密
     *
     * @param data            待加密数据
     * @param key             二进制密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[]   加密数据
     */
    public static byte[] encrypt(byte[] data, byte[] key, String cipherAlgorithm) {
        Key k = toKey(key);
        return encrypt(data, k, cipherAlgorithm);
    }

    /**
     * 加密
     *
     * @param data            待加密数据
     * @param key             密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[]   加密数据
     */
    public static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm) {
        //获取算法
        try {
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            //设置加密模式，并指定秘钥
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //加密数据
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException |
                 NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解密
     *
     * @param data 待解密数据
     * @param key  二进制密钥
     * @return byte[]   解密数据
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 解密
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[]   解密数据
     */
    public static byte[] decrypt(byte[] data, Key key) {
        return decrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }

    /**
     * 解密
     *
     * @param data            待解密数据
     * @param key             二进制密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[]   解密数据
     */
    public static byte[] decrypt(byte[] data, byte[] key, String cipherAlgorithm) {
        Key k = toKey(key);
        return decrypt(data, k, cipherAlgorithm);
    }

    /**
     * 解密
     *
     * @param data            待解密数据
     * @param key             密钥
     * @param cipherAlgorithm 加密算法/工作模式/填充方式
     * @return byte[]   解密数据
     */
    public static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm) {
        try {
            //获取算法
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);
            //设置解密模式，并指定秘钥
            cipher.init(Cipher.DECRYPT_MODE, key);
            //解密数据
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 转换密钥
     *
     * @param secretKey 二进制密钥
     * @return 密钥
     */
    public static Key toKey(byte[] secretKey) {
        //生成密钥
        return new SecretKeySpec(secretKey, KEY_ALGORITHM);
    }


    public static void main(String[] args) throws Exception {

        //加解密的密码
        for (int i = 0; i < 100; i++) {
            String password = "12.1231";
            Key key = toKey(getSecretKey(password));

//        String data = RandomStringUtils.random(32, true, true);
            String data = "4pqhn9itsEJYs4jHI6oaDEWvbGG4DmoR";
//            System.out.println("明文 ：" + data);

            byte[] encryptData = encrypt(data.getBytes(), key);
            //把密文转为16进制
            String encryptDataHex = Hex.encodeHexString(encryptData);
            System.out.println("加密 : " + encryptDataHex);

            // 解密
            byte[] decryptData = decrypt(Hex.decodeHex(encryptDataHex.toCharArray()), key);
//            System.out.println("解密 : " + new String(decryptData));
        }
    }
}
