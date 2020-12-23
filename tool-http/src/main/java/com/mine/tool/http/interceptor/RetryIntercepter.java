package com.mine.tool.http.interceptor;

import com.mine.tool.http.handler.RetryHandler;
import com.mine.tool.http.handler.response.RetryResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Objects;

/**
 * 功能 :
 * OKhttp重试拦截器
 */
@Slf4j
public class RetryIntercepter implements Interceptor {

    /**重试次数,默认重试3次**/
    private int maxRetry = 3;
    /**重试的时间间隔**/
    private Integer[] interval = new Integer[]{50};

    /**判断是否需要重试@Builder.Default**/
    private RetryHandler retryHandler;

    public RetryIntercepter(Integer maxRetry) {
        this.maxRetry = maxRetry;
    }

    public RetryIntercepter(Integer maxRetry,Integer[] interval) {
        this.maxRetry = maxRetry;
        if( Objects.nonNull(interval) && interval.length > 0 ){
            this.interval = interval;
        }
    }

    public RetryIntercepter(Integer maxRetry, RetryHandler retryHandler) {
        this.retryHandler = retryHandler;
        this.maxRetry = maxRetry;
    }

    public RetryIntercepter(Integer maxRetry, Integer[] interval, RetryHandler retryHandler) {
        this.retryHandler = retryHandler;
        this.maxRetry = maxRetry;
        if( Objects.nonNull(interval) && interval.length > 0 ){
            this.interval = interval;
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = null;
        try {
            response = chain.proceed(request);
        } catch (SocketTimeoutException e) {
            response = timeoutRetry(chain, request);
        }
        // retryHandler和默认的 条件互斥
        if(Objects.isNull(retryHandler) ){
            return serverErrorRetry(chain, request, response);
        }

        byte[] content = response.body().bytes();
        okhttp3.MediaType mediaType = response.body().contentType();
        // 传入的判断条件
        RetryResponse retryResponse = retryHandler.needRetry(content);
        if( Objects.nonNull(retryResponse) && retryResponse.getNeed() ) {
            for (int i = 0; i < maxRetry; i++) {
                response = requestAgain(chain, request, i, retryResponse.getReason());
                content = response.body().bytes();
                retryResponse = retryHandler.needRetry(content);
                if( Objects.isNull(retryResponse) || !retryResponse.getNeed() ){
                    break;
                }
            }
        }
        return response.newBuilder().body(okhttp3.ResponseBody.create(mediaType, content)).build();
    }

    /**服务器500重试**/
    private Response serverErrorRetry(Chain chain, Request request, Response response) throws IOException {
        if( Objects.nonNull(response) && response.code() >= 500 ){
            for (int i = 0;i < maxRetry; i++) {
                response = requestAgain(chain, request, i, "HTTP状态码:"+response.code());
                if( Objects.isNull(response) || response.code() < 500 ){
                    break;
                }
            }
        }
        return response;
    }

    /**超时重试**/
    private Response timeoutRetry(Chain chain, Request request) throws IOException {
        Response response = null;
        for (int i = 0;i < maxRetry; i++) {
            try {
                response = requestAgain(chain, request, i, "接口访问超时");
                break;
            } catch (SocketTimeoutException e1) {
                // 仍然超时,则继续访问
            }
        }
        if( Objects.isNull(response) ){
            throw new SocketTimeoutException("重试"+maxRetry+"之后,仍然超时,终止尝试.");
        }
        return response;
    }

    /**重新发一次请求**/
    private Response requestAgain(Chain chain, Request request, Integer index, String reason) throws IOException {
        try {
            Integer delayTime;
            if( index > this.interval.length - 1 ){
                delayTime = this.interval[this.interval.length - 1];
            }else{
                delayTime = this.interval[index];
            }
            Thread.sleep(delayTime);
            log.debug("第{}次重新发送请求,延迟时间:{},重试原因: {}",(index + 1),delayTime,reason);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        return chain.proceed(request);
    }
}
