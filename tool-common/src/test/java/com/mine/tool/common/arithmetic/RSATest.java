package com.mine.tool.common.arithmetic;

import com.mine.tool.common.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyPair;

/**
 * 功能 :
 */
@Slf4j
public class RSATest {

    /**生成签名**/
    @Test
    public void createKeys() {
        KeyPair keyPair = RSA.init();
        LogUtils.info(log,RSA.publicKey(keyPair));
        LogUtils.info(log,RSA.privateKey(keyPair));
    }

    /**签名和验签**/
    @Test
    public void signAndVerify() {
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwf/Gzur6atV+X/PoQ8Mfg46gyNUJ7VdiVOT9FAfFRuiMpFTkoqzkl2U5UkUW1f+Mtpr7pwO3ZrcTyrtgrvQxPd6QPS78G3tYYSZommF9JCWjiFyY/dbnLU03HsB+01V+iMnw1M+yNQIvJ2dPsCcAUHZy77kxw9G1cveudwSJajABDsOtblf1Faut3rdCt1Z0e6k8yipgvcL2V3Qr3uGVYwvtP72cxE+SFn22FfdIzNBMCPYre+l95ZLIVy7dUKFygiBddxZfK89pOfNy8Xuqxv5LeLtkfY8j4iND7EeZzKzt1aEFOxIr8OzLRT6yv1B7F5WqNOwVD0l7aLESaVzH7QIDAQAB";
        String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDB/8bO6vpq1X5f8+hDwx+DjqDI1QntV2JU5P0UB8VG6IykVOSirOSXZTlSRRbV/4y2mvunA7dmtxPKu2Cu9DE93pA9Lvwbe1hhJmiaYX0kJaOIXJj91uctTTcewH7TVX6IyfDUz7I1Ai8nZ0+wJwBQdnLvuTHD0bVy9653BIlqMAEOw61uV/UVq63et0K3VnR7qTzKKmC9wvZXdCve4ZVjC+0/vZzET5IWfbYV90jM0EwI9it76X3lkshXLt1QoXKCIF13Fl8rz2k583Lxe6rG/kt4u2R9jyPiI0PsR5nMrO3VoQU7Eivw7MtFPrK/UHsXlao07BUPSXtosRJpXMftAgMBAAECggEAGGvUYRPfdjdt8zJHGNClVPBNeTqoR0Jq1HkHCtB3mxeHLOCq73eIRy6s6phb6ZCD7DDSYRHssnd1fF3eK+f9kuZDjhAuGF+Mnp4xKM4A7N34GiBd+gZlj6RavfpJfN0UUq/pK0ECE/40ZWdx9qDSP07J5TeQ9i+eAd0AD2d4zO8J7Y/AgNf5UAoQ8oTbSRhwCDn4v/s6khWA+pKAPLp9xvj/B0ujTpEsfcd7sfr+4KmStgh5W0C811zh28t+Ef9aVtBDhk50kImwKlEUpnCSPETqlSlJmvTAr/gvQDB3cSfr0eG/5qukLYZgA83FuR4zgEUZInQrhRhS2ZQcwkeAgQKBgQDnRLZLFup+vZzRC9A354rkP9cL+RcnMfDougeaKOew5bqylpzcLvtj8lsQEKUUjQeBocf7DctxMjIMNF0sCsdEZ5T9i+D0e4m1mtIuuyS/M44XWfVPx08SHYhtbTBJIV6FsNaAiarNJYByi9mZIEQWKFLiCVn86EzU6/HU55zl/QKBgQDWvsRmG9WoPYPwuiV7NHdP6xSA0HSEyPKqAVuZ/SMVyEM0ErwCXHdl142oRYhLWKgaqN2nSODK+bN8YPZAFxOTYaWtifXW9Bi/eZtPRiCIv9aD3Z04oy/CLxLjJSyNIL6k6Du5PsrD2uvVVhrEIt4T7k+1gPzW4yjW1eX0orIUsQKBgE8LrUL9Eik/pdnNr91rxo09RMNS4BcFetIQw4htcsODwcxIrlSTulju/TEAf2wGyAoGwdONY3vNLE3/l9j11iYLQWFn7fuiPe9Ue4J9dJHljStuE+qwtNX8QiUkrNxSC3ymdKp6Y2a66qw8IKO144+vOKUJjThkLd6Yxo9+mqIBAoGBAIxyK6TUY4Iq6VXRiJ3xjG7aQoiUzP/jJMIHLU+i/QVL+pfeANRSgUw8E8uP3jnY++ijtIOp6EsX0ElyaNs1vHnRjTfYWM6CyoTAFdFc/f4x6SxJDoBySdPoUaP5Ng/+8HCN6jGXXamGTmMyc0L/wtqqK7t7ZuFro9QOBN1gYrOhAoGAI+ulsA6mHdfCg45vSKFQv85IEdhkyS3wemVJYCJOHFepmmOkBNLcAaWbNfCeohnnsHsZl6W9M5z3C//Prgb9DHhLXY9E58aZTl+BTCcQ1jXz8pVO2GdOA47bR4fhDJi/PGDsJ89IZFvqnVUTpVjwZ41pkFwzSe49CnkIRbDSWFw=";
        String payload = "a=1&b=2&c=3";
        byte[] sign = RSA.sign(payload.getBytes(),privateKey);
        LogUtils.info(log,Base64.encode(sign));
        boolean verify = RSA.verify(payload.getBytes(),publicKey,Base64.encode(sign));
        LogUtils.info(log,verify);
    }

    /**加密解密**/
    @Test
    public void encryptAndDecrypt() {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVf5PG0jtXFp9W8evKSJi1EBIEyB/60OcPOx7IQ20Yix9KJ5g2DivGnEjfFyNiP55kjNsyFVGkTnW2IVW/oSj99WV+rekui1MPfGaQNFXYK9ankfPZTA1/Vbeymb86ZAZqA8lI+NRViLGkgVixSfNc/oih8raC92ymab6tVqVFDQIDAQAB";
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJV/k8bSO1cWn1bx68pImLUQEgTIH/rQ5w87HshDbRiLH0onmDYOK8acSN8XI2I/nmSM2zIVUaROdbYhVb+hKP31ZX6t6S6LUw98ZpA0Vdgr1qeR89lMDX9Vt7KZvzpkBmoDyUj41FWIsaSBWLFJ81z+iKHytoL3bKZpvq1WpUUNAgMBAAECgYBt0zAromvneX4K8GRI0XYlpa2nB6G+r1LfNI5Tjn0Jx8JvxpCiPVzZZhx+j0/2MEhbE8M/krvMWbtN1kVZJrqmXspB+OygCtKmHpdeSwjoBRP4TajEOrgS4yTryv6HYzKxmgz33aw2nXaTX5C2qPZpU9wf36dnF5nzV5wk7AF8vQJBAMsd2jDGUH1RoR1OMlvhwFO06gTbjFrl/ZWQ0WXNdeW8rPO77lJtSiifRV5aywkruoBmpz7dRGMpymXbYY+LH3cCQQC8a/SXr/5GZZp/izx3hMHWZf8Hrc0xJsD+OK1L0TaTqbzTJSY6nEIWHj73aTYdZ1UExNlGJt9lCG7+AR+BXoibAkEAx38lm/xceAnh9fek7Kv5i/3IUEcXPvxgKjPYB2Za4u+C683s0Ra43Nc6eecxPmutvYmVwN/w2Hjma06jLyqVFwJBAIYs5i+CjzL4NW3v6+48ZoBTf6mrNXxz2WjvWVCtOg0rCSDeyntgPJtdjH9It9V2eQ99Ui/njJt4xvkwOYw5klMCQEacgPDAMG/kVkv2djR58Z2DIt8ji4KNrGDV2jZWmeaw/mZheAknFq3TrqAhwufER7Qtq3JWQuUe8XfGsJREWq4=";
        String payload = "123456";
        byte[] encrypts = RSA.encrypt(payload.getBytes(),publicKey);
        LogUtils.info(log,Base64.encode(encrypts));
        byte[] decrypts = RSA.decrypt(Base64.decode(Base64.encode(encrypts)),privateKey);
        LogUtils.info(log,new String(decrypts));
    }

    @Test
    public void testP12() throws IOException {
        FileInputStream fis = new FileInputStream("/Users/mine/logs/server.p12");
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        String publicKey = RSA.publicKey(buffer, "kgb123456");
        System.out.println(publicKey);
        String privateKey = RSA.privateKey(buffer, "kgb123456");
        System.out.println(privateKey);
    }
}
