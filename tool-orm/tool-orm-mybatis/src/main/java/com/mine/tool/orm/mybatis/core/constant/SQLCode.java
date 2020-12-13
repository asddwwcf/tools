package com.mine.tool.orm.mybatis.core.constant;

import lombok.Getter;

/**
 * 功能 :
 * orm异常码
 * @author : Bruce(刘正航) 21:44 2019-01-20
 */
public enum SQLCode {
    //
    FE001("FE001","查询异常:表没有主键"),
    FE002("FE002","查询异常:表没有指定字段"),
    FE003("FE003","查询异常:对象的所有字段都没有值,无法完成查询"),
    FE004("FE004","查询异常:对象为空"),
    FE005("FE005","字段异常:不允许的字段类型"),

    ;
    @Getter
    private String code;
    @Getter
    private String message;

    SQLCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
