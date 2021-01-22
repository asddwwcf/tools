package com.mine.tool.common.arithmetic;

import com.mine.tool.common.exception.ExceptionUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;
import java.util.Objects;

/**
 * 功能 :
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RSA {

    private static final String KEY_ALGORITHM = "RSA";
    private static final String RSA_SIGNATURE_ALGORITHM = "SHA1withRSA";
    private static final String RSA2_SIGNATURE_ALGORITHM = "SHA256WithRSA";

    /**
     * 初始化密钥
     */
    public static KeyPair init() {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGen.initialize(1024);
            return keyPairGen.generateKeyPair();
        } catch (Exception e) {
            ExceptionUtils.printStackTrace(e);
        }
        return null;
    }

    /**
     * 从字节数组中,获取公钥
     * @param fileBytes 文件字节码
     * @param password 证书密码
     * @return 返回公钥
     */
    public static String publicKey(byte[] fileBytes, String password) {
        try {
            KeyStore ks = keyStore(fileBytes, password);
            if (Objects.isNull(ks)) {
                return null;
            }
            Enumeration<String> enumeration = ks.aliases();
            if (!enumeration.hasMoreElements()) {
                return null;
            }
            String alias = enumeration.nextElement();
            Certificate certificate = ks.getCertificate(alias);
            PublicKey publicKey = certificate.getPublicKey();
            return Base64.encode(publicKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从字节数组中,获取私钥
     * @param fileBytes 文件字节码
     * @param password 证书密码
     * @return 返回私钥
     */
    public static String privateKey(byte[] fileBytes, String password) {
        try {
            KeyStore ks = keyStore(fileBytes, password);
            if (Objects.isNull(ks)) {
                return null;
            }
            Enumeration<String> enumeration = ks.aliases();
            if (!enumeration.hasMoreElements()) {
                return null;
            }
            String alias = enumeration.nextElement();
            PrivateKey key = (PrivateKey) ks.getKey(alias, password.toCharArray());
            return Base64.encode(key.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 取得公钥
     */
    public static String publicKey(KeyPair keyPair) {
        if (Objects.isNull(keyPair)) {
            return null;
        }
        return Base64.encode(keyPair.getPublic().getEncoded());
    }

    /**
     * 取得私钥
     */
    public static String privateKey(KeyPair keyPair) {
        if (Objects.isNull(keyPair)) {
            return null;
        }
        return Base64.encode(keyPair.getPrivate().getEncoded());
    }

    /**
     * 用公钥加密
     *
     * @param data            元数据字节码
     * @param base64PublicKey base64编码过的公钥(publicKey)
     * @return 加密结果
     */
    public static byte[] encrypt(byte[] data, String base64PublicKey) {
        try {
            // 对公钥解密
            byte[] keyBytes = Base64.decode(base64PublicKey);
            // 取得公钥
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);
            // 对数据加密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            return cipher.doFinal(data);
        } catch (Exception e) {
            ExceptionUtils.printStackTrace(e);
        }
        return new byte[0];
    }

    /**
     * 用私钥解密
     *
     * @param data             元数据字节码
     * @param base64PrivateKey base64编码过的私钥(privateKey)
     * @return 解码结果
     */
    public static byte[] decrypt(byte[] data, String base64PrivateKey) {
        try {
            // 对密钥解密
            byte[] keyBytes = Base64.decode(base64PrivateKey);

            // 取得私钥
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

            // 对数据解密
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            return cipher.doFinal(data);
        } catch (Exception e) {
            ExceptionUtils.printStackTrace(e);
        }
        return new byte[0];
    }

    /**
     * 签名-私钥签名
     *
     * @param data             元数据字节码
     * @param base64PrivateKey base64编码过的私钥
     * @return 签名结果
     */
    public static byte[] sign(byte[] data, String base64PrivateKey) {
        return sign(data, base64PrivateKey, false);
    }

    /**
     * 验签-公钥验签
     *
     * @param data            需要验签的字符串
     * @param base64PublicKey base64编码之后的公钥
     * @param sign            需要验证的签名
     * @return
     */
    public static boolean verify(byte[] data, String base64PublicKey, String sign) {
        return verify(data, base64PublicKey, sign, false);
    }


    /**
     * 签名-私钥签名
     *
     * @param data             元数据字节码
     * @param base64PrivateKey base64编码过的私钥
     * @param isRsa2           是否使用rsa2方式签名
     * @return 签名结果
     */
    public static byte[] sign(byte[] data, String base64PrivateKey, boolean isRsa2) {
        try {
            // Base64 --> Key
            byte[] bytes = Base64.decode(base64PrivateKey);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            // Sign
            Signature signature = Signature.getInstance(isRsa2 ? RSA2_SIGNATURE_ALGORITHM : RSA_SIGNATURE_ALGORITHM);
            signature.initSign(privateKey);
            signature.update(data);
            return signature.sign();
        } catch (Exception e) {
            ExceptionUtils.printStackTrace(e);
        }
        return new byte[0];
    }

    /**
     * 验签-公钥验签
     *
     * @param data            需要验签的字符串
     * @param base64PublicKey base64编码之后的公钥
     * @param sign            需要验证的签名
     * @param isRsa2          是否使用rsa2方式签名
     * @return
     */
    public static boolean verify(byte[] data, String base64PublicKey, String sign, boolean isRsa2) {
        try {
            // Base64 --> Key
            byte[] bytes = Base64.decode(base64PublicKey);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            // verify
            Signature signature = Signature.getInstance(isRsa2 ? RSA2_SIGNATURE_ALGORITHM : RSA_SIGNATURE_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(data);
            return signature.verify(Base64.decode(sign));
        } catch (Exception e) {
            ExceptionUtils.printStackTrace(e);
        }
        return false;
    }

    private static KeyStore keyStore(byte[] fileBytes, String password) {
        ByteArrayInputStream bis = new ByteArrayInputStream(fileBytes);
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(bis, password.toCharArray());
            return ks;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
