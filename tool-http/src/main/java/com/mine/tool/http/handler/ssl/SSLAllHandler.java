package com.mine.tool.http.handler.ssl;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.security.SecureRandom;

public class SSLAllHandler {

    public static SSLSocketFactory factory() {
        SSLSocketFactory factory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllManager()}, new SecureRandom());
            factory = sc.getSocketFactory();
        } catch (Exception ignored) {
        }
        return factory;
    }

}