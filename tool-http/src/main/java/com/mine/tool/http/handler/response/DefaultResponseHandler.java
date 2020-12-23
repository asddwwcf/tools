package com.mine.tool.http.handler.response;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import java.io.IOException;

/**
 * 功能 :默认结果解析-字符串处理
 */
@Slf4j
public class DefaultResponseHandler extends ResponseAdapter<String> {
    @Override
    public String handle(Response response) throws IOException {
        if( HttpStatus.NOT_FOUND.value() == response.code() ){
            String result = response.body().string();
            log.info("{}",result);
            return "404:"+HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return response.body().string();
    }
}
