package com.mine.tool.common.util;

import com.mine.tool.common.exception.ExceptionUtils;
import com.mine.tool.common.util.string.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 功能 :
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IPUtils {

    private static final String UNKNOWN = "unknown";
    private static final String LOCAL = "127.0.0.1";
    private static final String[] HEADERS = {"X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR"};

    /**
     * 获取本机-局域网IP
     **/
    public static String local() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostAddress();
        } catch (UnknownHostException e) {
            ExceptionUtils.printStackTrace(e);
        }
        //获取本机IP
        return LOCAL;
    }

    /**
     * 获取请求来源IP
     **/
    public static String request(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        for (String key : HEADERS) {
            String proxy = request.getHeader(key);
            if (StringUtils.isNotBlank(proxy) && !UNKNOWN.equals(proxy)) {
                ipAddress = proxy;
            }
        }
        if (ipAddress.contains(",")) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(','));
            return ipAddress;
        }
        log.info("获取到的ip地址为:{}", ipAddress);
        return ipAddress;
    }

}