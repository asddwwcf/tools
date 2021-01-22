package com.mine.tool.common.util.file.filter;

/**
 * 功能 :
 */
public interface LineFilter {

    /**
     * @param count 总行数
     * @param index 当前行号
     * @param line 当前行的字符串值
     * @return
     */
    boolean doFilter(Long count,Long index,String line);
}
