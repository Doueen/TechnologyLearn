package modules.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SecurityTest {
    /**
     *  AES算法加解密
     * @param content 加密原文
     * @param slatKey 生成密钥的salt 16字节
     * @param vectorKey 初始化向量参数 16字节
     * @return 密文
     * @throws Exception the Exception
     */
    public static String encrypt(String content, String slatKey, String vectorKey) throws Exception {
        // 创建Cipher对象
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        // 创建密钥
        SecretKey secretKey = new SecretKeySpec(slatKey.getBytes(), "AES");
        // 创建初始化向量
        IvParameterSpec iv = new IvParameterSpec(vectorKey.getBytes());
        // 初始化cipher
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        byte[] encrypted = cipher.doFinal(content.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * AES解密
     * @param base64Content Base64编码的密文
     * @param saltKey salt
     * @param vectorKey 初始化向量参数
     * @return 原文
     * @throws Exception the Exception
     */
    public static String decrypt(String base64Content,String saltKey,String vectorKey) throws Exception{
        Cipher cipher =Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKey secretKey=new SecretKeySpec(saltKey.getBytes(),"AES");
        IvParameterSpec iv =new IvParameterSpec(vectorKey.getBytes());
        cipher.init(Cipher.DECRYPT_MODE,secretKey,iv);
        byte[] content =Base64.getDecoder().decode(base64Content);
        byte[] encrypted=cipher.doFinal(content);
        return new String(encrypted);
    }

    public static final String RSA = "RSA";
    public static final String ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";

    /**
     * 随机生成 RSA 密钥对
     *
     * @param keyLength 密钥长度, 范围: 512～2048, 一般 1024
     * @return 密钥对
     */
    public static KeyPair generateRSAKeyPair(int keyLength) {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(RSA);
            kpg.initialize(keyLength);
            return kpg.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    /**
     * 公钥加密
     *
     * @param data      原文
     * @param publicKey 公钥
     * @return 加密后的数据
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] publicKey) {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        try {
            KeyFactory kf = KeyFactory.getInstance(RSA);
            PublicKey keyPublic = kf.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, keyPublic);
            return cipher.doFinal(data);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 私钥加密
     *
     * @param data       待加密数据
     * @param privateKey 密钥
     * @return 加密后的数据
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] privateKey) {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        try {
            KeyFactory kf = KeyFactory.getInstance(RSA);
            PrivateKey keyPrivate = kf.generatePrivate(keySpec);
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, keyPrivate);
            return cipher.doFinal(data);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 公钥解密
     *
     * @param data      待解密数据
     * @param publicKey 密钥
     * @return 解密后的数据
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] publicKey) {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        try {
            KeyFactory kf = KeyFactory.getInstance(RSA);
            PublicKey keyPublic = kf.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, keyPublic);
            return cipher.doFinal(data);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 私钥解密
     *
     * @param data  待解密的数据
     * @param privateKey 私钥
     * @return 解密后的数据
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] privateKey) {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        try {
            KeyFactory kf = KeyFactory.getInstance(RSA);
            PrivateKey keyPrivate = kf.generatePrivate(keySpec);
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, keyPrivate);
            return cipher.doFinal(data);
        } catch (Exception e) {
        }
        return null;
    }



}
