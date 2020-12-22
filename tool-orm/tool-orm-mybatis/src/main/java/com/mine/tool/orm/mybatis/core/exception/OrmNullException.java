package com.mine.tool.orm.mybatis.core.exception;

import com.bruce.tool.common.exception.BaseRuntimeException;
import com.mine.tool.orm.mybatis.core.constant.SQLCode;

/**
 * 功能 :
 *
 */
public class OrmNullException extends BaseRuntimeException {

    public OrmNullException() {
        super(SQLCode.FE004.getCode(),"查询异常:传入类为空");
    }

    public OrmNullException(String message){
        super(SQLCode.FE004.getCode(),message);
    }
}
