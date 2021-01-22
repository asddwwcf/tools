package com.mine.tool.common.enums;

import com.mine.tool.common.util.string.EnumHandler;
import com.mine.tool.common.util.string.JsonUtils;
import org.junit.Test;

/**
 * 功能 :
 */
public class EnumHandlerTest {

    @Test
    public void testEnumOne(){
        System.out.println(JsonUtils.objToStr(EnumHandler.toList(KeyGroup.values())));
        System.out.println(JsonUtils.objToStr(EnumHandler.toList(KeyGroup.values(),"name")));
        System.out.println(JsonUtils.objToStr(EnumHandler.toList(KeyDemo.values())));
    }
}
