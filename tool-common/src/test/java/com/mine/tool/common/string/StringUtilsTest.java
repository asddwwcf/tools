package com.mine.tool.common.string;

import com.mine.tool.common.util.LogUtils;
import com.mine.tool.common.util.string.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 功能 :
 */
@Slf4j
public class StringUtilsTest {

    @Test
    public void formatJson(){
        String content = "{\"name\":\"zhangsan\",\"age\":23,\"email\":\"chentging@aliyun.com\"}";
        LogUtils.info(log,StringUtils.formatJson(content));
    }
}
