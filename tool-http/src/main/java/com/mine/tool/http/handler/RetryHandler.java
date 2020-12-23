package com.mine.tool.http.handler;

import com.mine.tool.http.handler.response.RetryResponse;

/**
 * 功能 :
 */
public interface RetryHandler {

    RetryResponse needRetry(byte[] response);
}
