package com.mine.tool.common;

import com.mine.tool.common.util.IPUtils;
import com.mine.tool.common.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 功能 :
 */
@Slf4j
public class IPUtilsTest {

    @Test
    public void main(){
        long begin = System.currentTimeMillis();
        LogUtils.info(log,"本机的内网IP是：" + IPUtils.local()+",耗时:"+(System.currentTimeMillis()-begin));
    }
}
