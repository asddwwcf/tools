package com.mine.tool.http.handler.response;

import com.mine.tool.http.handler.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * 功能 :
 */
@Slf4j
public abstract class ResponseAdapter<T> implements ResponseHandler<T> {

    protected boolean validStatus(Response response) throws IOException {
        if( HttpStatus.OK.value() == response.code() ){
            return true;
        }
        return false;
    }
}
