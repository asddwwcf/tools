package com.mine.tool.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

/**
 * 所有异常类的父类
 */
@Data
@NoArgsConstructor // 默认无参构造函数
@AllArgsConstructor // 默认全参构造函数
public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;
	private Object code;
	private String message;
	private Object data;

	public BaseException(Object code,String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public BaseException(Supplier<Object> code, Supplier<String> message) {
		super(message.get());
		this.code = code.get();
		this.message = message.get();
	}

	public BaseException(Supplier<Object> code, Supplier<String> message, Object data) {
		super(message.get());
		this.code = code.get();
		this.message = message.get();
		this.data = data;
	}

}
