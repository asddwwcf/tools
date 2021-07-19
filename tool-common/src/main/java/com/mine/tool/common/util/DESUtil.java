package com.mine.tool.common.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.security.spec.AlgorithmParameterSpec;

public class DESUtil {
    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final String AES_NAME = "DES";
    // 加密模式
    public static final String ALGORITHM = "DES/CBC/PKCS7Padding";
    // 密钥
    public static final String KEY = "87654321";
    // 偏移量
    public static final String IV = "12345678";
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * 加密
     * @param content
     * @return
     */
    public String encrypt(String content) {
        byte[] result = null;
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(CHARSET), AES_NAME);
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);
            result = cipher.doFinal(content.getBytes(CHARSET));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(result);
    }

    /**
     * 解密
     * @param content
     * @return
     */
    public String decrypt(String content) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(CHARSET), AES_NAME);
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);
            return new String(cipher.doFinal(Base64.decodeBase64(content)), CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    public static void main(String[] args) {
        DESUtil aes = new DESUtil();
        String contents = "121456465";
        String encrypt = aes.encrypt(contents);
        System.out.println("加密后:" + encrypt);
        String decrypt = aes.decrypt(encrypt);
        System.out.println("解密后:" + decrypt);
    }
}