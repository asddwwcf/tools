package com.mine.tool.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 功能:
 * 系统级别的错误码常量
 * 每个业务项目,得有自己的业务错误码定义
 */
@AllArgsConstructor
public enum ErrorCode {
	//
	BUSINESS_ERROR("10000","网络异常","network error"),
	SYSTEM_ERROR("9999","网络异常","network error"),
	FORM_VALID_ERROR("9998","参数校验异常","form core valid error"),
	URL_IS_NULL("9997","请求地址为空","request url is null"),
	REQUEST_METHOD_ERROR("9996","请求方法错误","request method error"),
	REQUEST_PARAM_TYPE_ERROR("9995","请求参数类型错误","request argument type error"),
	SQL_UNIQUE_KEY_ERROR("9994","违反数据库唯一约束","unique index error"),
	MYSQL_EMOJI_UNSUPPORT_CODE("0004","暂不支持表情符号输入，请删除表情再试","emoji not support error"),
	RUNNING("0005","任务排队执行中,请耐心等待","be queuing"),
	RESUBMIT("0006","请求过于频繁,请稍后再试","too frequently"),
	EXPORT_NO_RECORD("0007","没有记录可以导出","no record"),
	EXPORT_NO_TOO_MORE("0008","本次导出请求超过20000条数据，每次最多允许导出20000条数据！","more than 20000"),

	;
	@Getter
	private String code;
	@Getter
	private String message;
	@Getter
	//英文错误信息
	private String messageENUS;

	public static String getMessageByCode(String code,I18N i18N){
		ErrorCode target = search(code);
		if( null == target ){
			return "";
		}
		return getMessageByLanguage(i18N,target.getMessage(),target.getMessageENUS());
	}

	public static ErrorCode search(String code){
		ErrorCode target = null;
		for (ErrorCode errorCode  : ErrorCode.values() ) {
			if( errorCode.getCode().equals(code) ){
				target = errorCode;
			}
		}
		return target;
	}

	public static String getMessageByLanguage(I18N i18N,String ...messages) {
		I18N[] ns = I18N.values();
		Integer size = messages.length;
		String message = messages[0];
		for ( int i = 0,length = ns.length;i<length;i++ ) {
			if( !i18N.equals(ns[i]) ){
				continue;
			}

			if( i < size - 1 ){
				message =  messages[i];
			}else{
				// 如果没有对应消息,默认返回中文
				message = messages[0];
			}
		}
		return message;
	}

}
