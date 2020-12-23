package com.mine.tool.http.handler.response;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import java.io.IOException;

/**
 * 功能 :
 */
@Slf4j
public class PingResponseHandler extends ResponseAdapter<Boolean> {
    @Override
    public Boolean handle(Response response) throws IOException {
        return validStatus(response);
    }
}
