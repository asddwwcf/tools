package com.mine.tool.http.handler;

import lombok.Data;

/**
 * 功能 :
 * http请求的日志对象
 */
@Data
public class LogInfo {
    private boolean print=true;
    private String begin;
    private String headerInfo;
    private String requestInfo;
    private String urlParams;
    private String bodyParams;
    private String responseInfo;
    private String end;
    /**请求耗时**/
    private Long duration;
}
