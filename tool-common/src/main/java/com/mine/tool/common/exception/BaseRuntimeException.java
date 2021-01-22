package com.mine.tool.common.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

/**
 * 所有异常类的父类
 */
@NoArgsConstructor
public class BaseRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    @Getter
    protected Object code;
    @Getter
    protected String message;
    @Getter
    protected Object data;

    public BaseRuntimeException(Throwable e) {
        super(e);
    }

    public BaseRuntimeException(Supplier<Throwable> e) {
        super(e.get());
    }

    public BaseRuntimeException(Object code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseRuntimeException(Object code, String message, Throwable e) {
        super(e);
        this.code = code;
        this.message = message;
    }

    public BaseRuntimeException(Object code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseRuntimeException(Object code, String message, Object data, Throwable e) {
        super(e);
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseRuntimeException(Supplier<Object> code, Supplier<String> message) {
        this.code = code.get();
        this.message = message.get();
    }

    public BaseRuntimeException(Supplier<Object> code, Supplier<String> message, Supplier<Throwable> e) {
        super(e.get());
        this.code = code.get();
        this.message = message.get();
    }

    public BaseRuntimeException(Supplier<Object> code, Supplier<String> message, Object data) {
        this.code = code.get();
        this.message = message.get();
        this.data = data;
    }

    public BaseRuntimeException(Supplier<Object> code, Supplier<String> message, Supplier<Object> data, Supplier<Throwable> e) {
        super(e.get());
        this.code = code.get();
        this.message = message.get();
        this.data = data.get();
    }

}
