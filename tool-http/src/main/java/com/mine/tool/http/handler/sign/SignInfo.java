package com.mine.tool.http.handler.sign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能 :
 * 签名需要的信息对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInfo {
    protected String appKey;
    protected String appSecret;
    /**生成秘钥所用的特定字符串,可以为空**/
    protected String phrase;
}
