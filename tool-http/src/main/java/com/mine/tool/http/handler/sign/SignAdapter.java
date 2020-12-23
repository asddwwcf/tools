package com.mine.tool.http.handler.sign;


import com.mine.tool.http.handler.SignHandler;
import com.mine.tool.http.handler.request.RequestInfo;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

/**
 * 功能 :
 * 签名适配器
 */
public class SignAdapter implements SignHandler {

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    protected String signKey;

    static final DateTimeFormatter DT_FORMAT = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss");
    static final ZoneId ZONE_GMT = ZoneId.of("Z");

    @Override
    public void sign(SignInfo signInfo, RequestInfo requestInfo) {
        // do nothing.
    }

    /**
     * 用算法生成签名串
     * @param appSecretKey 固定加密盐
     * @param paramStitch 按照固定格式拼接好的参数串
     * @param arithmeticName 算法名称(HMAC_SHA_256)
     * @return
     */
    protected final byte[] signature(String appSecretKey, String paramStitch, String arithmeticName) {
        Mac hmacSha = crypto(appSecretKey,arithmeticName);
        byte[] hash = hmacSha.doFinal(paramStitch.getBytes(StandardCharsets.UTF_8));
        return hash;
    }

    /**字节数组转16进制**/
    protected String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for(int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**字节数组转base64字符串**/
    protected String bytesToBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 创建算法对象
     * @param input 传入参数
     * @param arithmeticName 算法名称
     */
    protected final Mac crypto(String input,String arithmeticName) {
        Mac hmacSha256;
        try {
            hmacSha256 = Mac.getInstance(arithmeticName);
            SecretKeySpec secKey =
                    new SecretKeySpec(input.getBytes(StandardCharsets.UTF_8), arithmeticName);
            hmacSha256.init(secKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No such algorithm: " + e.getMessage());
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Invalid key: " + e.getMessage());
        }
        return hmacSha256;
    }

    protected final long epochNow() {
        return Instant.now().getEpochSecond();
    }

    protected final String gmtNow() {
        return Instant.ofEpochSecond(epochNow()).atZone(ZONE_GMT).format(DT_FORMAT);
    }

}
