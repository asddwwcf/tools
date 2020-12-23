package com.mine.tool.http.handler.ssl;

import lombok.NoArgsConstructor;
import okhttp3.TlsVersion;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.Collection;

@NoArgsConstructor(staticName = "build")
public class SSLSpecifiedHandler {

    private X509TrustManager manager;

    public X509TrustManager trustManager(){
        return this.manager;
    }

    public SSLSocketFactory factory(String cert, String certPassword) {

        File file = new File(cert);

        try(InputStream certStream = new FileInputStream(file)){
            manager = buildManager(certStream,certPassword);
            // 创建 SSLContext
            SSLContext sslContext = SSLContext.getInstance(TlsVersion.TLS_1_2.javaName());
            sslContext.init(null , new TrustManager[]{manager}, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException("SSL配置错误");
        }
    }

    private X509TrustManager buildManager(InputStream in, String certPassword)
            throws GeneralSecurityException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(in);
        if (certificates.isEmpty()) {
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }

        // Put the certificates a key store.
        // Any password will work.
        char[] password = certPassword.toCharArray();
        KeyStore keyStore = newEmptyKeyStore(password);
        int index = 0;
        for (Certificate certificate : certificates) {
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificate);
        }

        // Use it to build an X509 trust manager.
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, password);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:"
                    + Arrays.toString(trustManagers));
        }
        return (X509TrustManager) trustManagers[0];
    }

    private KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            // By convention, 'null' creates an empty key store.
            InputStream in = null;
            keyStore.load(in, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

}