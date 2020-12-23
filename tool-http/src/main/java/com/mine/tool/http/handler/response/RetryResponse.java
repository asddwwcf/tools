package com.mine.tool.http.handler.response;

import lombok.Builder;
import lombok.Data;

/**
 * 功能 :
 * 重试机制返回结果
 */
@Data
@Builder
public class RetryResponse {

    /**是否需要重试**/
    @Builder.Default
    private Boolean need = Boolean.FALSE;
    /**重试的原因或者目的**/
    @Builder.Default
    private String reason = "未知原因";
}
