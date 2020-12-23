package com.mine.tool.http.handler;


import com.mine.tool.http.handler.request.RequestInfo;
import com.mine.tool.http.handler.sign.SignInfo;

/**
 * 功能 :
 * 签名处理类
 */
public interface SignHandler {
    /**默认签名放置到请求头中**/
    void sign(SignInfo signInfo, RequestInfo requestInfo);

}
