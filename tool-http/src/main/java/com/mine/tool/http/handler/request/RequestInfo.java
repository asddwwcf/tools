package com.mine.tool.http.handler.request;

import lombok.Builder;
import lombok.Data;
import okhttp3.HttpUrl;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 功能 :
 */
@Data
@Builder
public class RequestInfo {
    private HttpUrl httpUrl;
    private RequestMethod method;
    private Map<String,Object> urlParams;
    private Map<String,Object> bodyParams;
    private Map<String,Object> headerParams;
}
