package com.mine.tool.http.handler;

import lombok.Builder;
import lombok.Data;

/**
 * 功能 :
 */
@Data
@Builder
public class Timeout {
    private Integer connectTimeout = 5;
    private Integer writeTimeout = 5;
    private Integer readTimeout = 5;
}
