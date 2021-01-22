package com.mine.tool.common.util.trace;

import com.mine.tool.common.util.LogUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 功能 :
 * 生成流程串号,用于跟踪
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TraceNoUtils {

    private static final SnowFlake SNOW_FLAKE = new SnowFlake(1, 1);

    public static String fetchTraceNo() {
        return String.valueOf(SNOW_FLAKE.nextId());
    }

    public static Long nextId() {
        return SNOW_FLAKE.nextId();
    }

    public static void main(String[] args) {
        LogUtils.info(log, fetchTraceNo());
    }
}
