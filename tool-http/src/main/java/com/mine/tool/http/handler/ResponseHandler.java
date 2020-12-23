package com.mine.tool.http.handler;

import okhttp3.Response;

import java.io.IOException;

/**
 * 功能 :
 * 结果集处理类
 */
public interface ResponseHandler<T> {
    T handle(Response response) throws IOException;
}
