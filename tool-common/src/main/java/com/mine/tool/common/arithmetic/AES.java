package com.mine.tool.common.arithmetic;

import com.mine.tool.common.arithmetic.constant.AesType;
import com.mine.tool.common.exception.ExceptionUtils;
import com.mine.tool.common.util.LogUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AES {

    private static final String KEY_ALGORITHM = "AES";
    /**算法/模式/补码方式**/
    private static final String CIPHER_ALGORITHM_CBC = "AES/CBC/PKCS5Padding";
    /**算法/模式/补码方式**/
    private static final String CIPHER_ALGORITHM_ECB = "AES/ECB/PKCS5Padding";
    /**算法/模式/补码方式**/
    private static final String CIPHER_ALGORITHM_GCM = "AES/GCM/PKCS5Padding";

    /**CBC**/
    public static String encrypt(AesType type,String content, String secretKey){
        String encrypt;
        switch(type){
            case CBC:
                encrypt = executeCBC(content,secretKey,true);
                break;
            case ECB:
                encrypt = executeECB(content,secretKey,true);
                break;
            case GCM:
                encrypt = executeGCM(content,secretKey,true);
                break;
            default:
                encrypt = null;
        }
        return encrypt;
    }

    /**CBC**/
    public static String decrypt(AesType type,String content,String secretKey){
        if(AesType.CBC.equals(type)){
            return executeCBC(content,secretKey,false);
        }else if(AesType.ECB.equals(type)){
            return executeECB(content,secretKey,false);
        }else if(AesType.GCM.equals(type)){
            return executeGCM(content,secretKey,false);
        }else{
            return null;
        }
    }

    private static String executeCBC(String content, String salt, boolean encode) {
        try {
            String md5 = MD5.encode(salt);
            String key = md5.substring(0, 16);
            String ivKey = md5.substring(16);
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), KEY_ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(ivKey.getBytes());
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_CBC);
            if( encode ){
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
                byte[] encrypted = cipher.doFinal(content.getBytes());
                return Base64.encode(encrypted);
            }
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
            byte[] original = cipher.doFinal(Base64.decode(content));
            return new String(original);
        } catch (Exception e) {
            LogUtils.error(log,e);
        }
        return null;
    }

    private static String executeECB(String content,String secretKey,boolean encode) {
        try {
            String md5 = MD5.encode(secretKey);
            String key = md5.substring(0, 16);
            SecretKeySpec secretKey1 = new SecretKeySpec(key.getBytes(), KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
            if( encode ){
                cipher.init(Cipher.ENCRYPT_MODE, secretKey1);
                return Base64.encode(cipher.doFinal(content.getBytes()));
            }
            cipher.init(Cipher.DECRYPT_MODE, secretKey1);
            return new String(cipher.doFinal(Base64.decode(content)));
        } catch (Exception e) {
            ExceptionUtils.printStackTrace(e);
        }
        return null;
    }

    private static String executeGCM(String content, String secretKey, boolean encode) {
        try {
            String md5 = MD5.encode(secretKey);
            String key = md5.substring(0, 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),"AES");
            Cipher cipher= Cipher.getInstance(CIPHER_ALGORITHM_GCM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            if( encode ){
                byte[] iv = cipher.getIV();
                assert iv.length == 12;
                byte[]  encryptData = cipher.doFinal(content.getBytes());
                assert encryptData.length == content.getBytes().length + 16;
                byte[] message = new byte[12 + content.getBytes().length + 16];

                System.arraycopy(iv, 0, message, 0, 12);
                System.arraycopy(encryptData, 0, message, 12, encryptData.length);
                return Base64.encode(message);
            }

            byte[] message = Base64.decode(content);
            GCMParameterSpec params = new GCMParameterSpec(128, message, 0, 12);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, params);
            byte[]  decryptData = cipher.doFinal(message, 12, message.length - 12);
            return new String(decryptData);
        } catch (Exception e) {
            ExceptionUtils.printStackTrace(e);
        }
        return null;
    }

}