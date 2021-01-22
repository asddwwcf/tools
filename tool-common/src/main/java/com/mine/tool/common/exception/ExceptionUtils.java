package com.mine.tool.common.exception;

import com.mine.tool.common.util.ClassUtils;
import com.mine.tool.common.util.regex.RegexUtils;
import com.mine.tool.common.util.string.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;

/**
 * 异常工具类
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExceptionUtils {

	/**唯一约束错误**/
	private static final String DUPLICATE_ERROR = "Duplicate";
	/**特殊字符错误**/
	private static final String MOJI_CHARACTER = "Incorrect string value: '\\x";

	public static String printErrorInfo(Throwable ex) {
		return gatherErrorInfo(ex);
	}

	/**搜集错误信息**/
	private static String gatherErrorInfo(Throwable ex) {
		StringBuffer result = new StringBuffer();
		if( ex instanceof DuplicateKeyException){
			handleDuplicateKeyInfo(ex,result);
		}else if( ex instanceof MethodArgumentTypeMismatchException){
			handleArumentTypeExceptionInfo(ex,result);
		} else if( ex instanceof HttpRequestMethodNotSupportedException){
			handleRequestMethodExceptionInfo(ex,result);
		} else if( ex instanceof UncategorizedSQLException){
			handleSqlExceptionInfo(ex, result);
		} else if( ex instanceof MissingServletRequestParameterException){
			//必传参数异常
			handleRequiredBindException(ex,result);
		} else if( ex instanceof MissingPathVariableException) {
			//url路径中的参数校验异常
			handleRequiredPathException(ex, result);
		} else if( ex instanceof MethodArgumentNotValidException){
			handleMethodBindException(ex, result);
		} else if( ex instanceof BindException){
			//hibernate-validation异常
			handleBindException(ex,result);
		}else{
			handleBusinessExceptionInfo(ex, result);
		}
		return result.toString();
	}

	/**打印堆栈异常**/
	public static void printStackTrace(Throwable e) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream pout = new PrintStream(out);
		e.printStackTrace(pout);
		String sw = new String(out.toByteArray());
		pout.close();
		try {
			out.close();
		} catch (Exception ex) {
			log.error("打印异常堆栈",ex);
		}
		log.error(sw);
	}

    /*
     ****************************************私有方法区*******************************************
                   _               _                           _    _                 _
                  (_)             | |                         | |  | |               | |
      _ __   _ __  _ __   __ __ _ | |_  ___   _ __ ___    ___ | |_ | |__    ___    __| |
     | '_ \ | '__|| |\ \ / // _` || __|/ _ \ | '_ ` _ \  / _ \| __|| '_ \  / _ \  / _` |
     | |_) || |   | | \ V /| (_| || |_|  __/ | | | | | ||  __/| |_ | | | || (_) || (_| |
     | .__/ |_|   |_|  \_/  \__,_| \__|\___| |_| |_| |_| \___| \__||_| |_| \___/  \__,_|
     | |
     |_|
     ****************************************私有方法区*******************************************
     */

    /**唯一主键冲突**/
	private static void handleDuplicateKeyInfo(Throwable ex, StringBuffer result) {
		DuplicateKeyException unique = (DuplicateKeyException)ex;
		Throwable throwable = unique.getCause();
		if( throwable instanceof SQLIntegrityConstraintViolationException ){
			handleConstraintViolationInfo(throwable,result);
		}
	}

	/**处理唯一索引冲突的异常**/
	private static void handleConstraintViolationInfo(Throwable ex, StringBuffer result) {
		SQLIntegrityConstraintViolationException unique = (SQLIntegrityConstraintViolationException)ex;
		String message = unique.getMessage();
		if( message.contains(DUPLICATE_ERROR) ){
			message = "违反数据库唯一索引约束";
		}
		result.append(message);
	}

	/**参数类型错误**/
	private static void handleArumentTypeExceptionInfo(Throwable ex, StringBuffer	 result) {
		MethodArgumentTypeMismatchException argumentTypeException = (MethodArgumentTypeMismatchException)ex;
		Class<?> requiredType = argumentTypeException.getRequiredType();
		String message = "参数类型错误:"+argumentTypeException.getName()
				+"(" + ((null != requiredType)?requiredType.getName():"null")
				+ "),实际的传入值为:"+argumentTypeException.getValue();
		result.append(message);
	}

    /**请求方法不支持**/
	private static void handleRequestMethodExceptionInfo(Throwable ex, StringBuffer result) {
		HttpRequestMethodNotSupportedException methodException = (HttpRequestMethodNotSupportedException)ex;
		String message = "该方法不支持"+methodException.getMethod()+"请求";
		result.append(message);
	}

	/**特殊SQL异常，返回值处理**/
	private static void handleSqlExceptionInfo(Throwable ex, StringBuffer mav) {
		// 针对特殊符号，做特殊的提示信息:Caused by: java.sql.SQLException: Incorrect string value: '\xF0\x9F\x98\x91' for column 'remark' at row 1
		UncategorizedSQLException e = (UncategorizedSQLException)ex;
		String msg = e.getMessage();
		if( !StringUtils.isEmpty(msg) && msg.contains(MOJI_CHARACTER) ){
			mav.append(ErrorCode.MYSQL_EMOJI_UNSUPPORT_CODE.getMessage());
		}else{
			handleUnExpectedExceptionInfo(ex, mav);
		}
	}


	/**参数校验异常捕获**/
	private static void handleRequiredBindException(Throwable ex, StringBuffer mav) {
		MissingServletRequestParameterException bindException = (MissingServletRequestParameterException)ex;
		String message = "缺少必传参数:" + bindException.getParameterName();
		mav.append(message);
	}

	private static void handleRequiredPathException(Throwable ex, StringBuffer mav) {
		MissingPathVariableException pathVariableException = (MissingPathVariableException) ex;
		String message = "缺少必传参数:" + pathVariableException.getVariableName();
		mav.append(message);
	}

	private static void handleMethodBindException(Throwable ex, StringBuffer mav) {
		MethodArgumentNotValidException bindException = (MethodArgumentNotValidException)ex;
		handleBindError(mav, bindException.getBindingResult());
	}

	/**参数校验异常捕获**/
	private static void handleBindException(Throwable ex, StringBuffer mav) {
		BindException bindException = (BindException)ex;
		handleBindError(mav, bindException.getBindingResult());
	}

	private static void handleBindError(StringBuffer mav, BindingResult br) {
		if (br.hasFieldErrors()) {
			FieldError fieldError = br.getFieldError();
			if( null != fieldError && org.apache.commons.lang3.StringUtils.isNotBlank(fieldError.getDefaultMessage()) ){
				String error = fieldError.getDefaultMessage();
				mav.append(error);
			}else{
				mav.append(ErrorCode.FORM_VALID_ERROR.getMessage());
			}
		}
	}

	/**业务异常统一处理**/
	private static void handleBusinessExceptionInfo(Throwable ex, StringBuffer mav) {
		Object code = null;
		Method method = ClassUtils.getMethod(ex.getClass(),"getCode");
		try {
			if(Objects.nonNull(method)){
				code = method.invoke(ex);
			}
		} catch (Exception e) {
			ExceptionUtils.printStackTrace(e);
		}
		//为空,说明不是业务异常
		if( Objects.isNull(code) ){
			handleUnExpectedExceptionInfo(ex,mav);
		}else{
			setMessage(ex, mav);
		}
	}

	/**未定义系统异常统一处理**/
	private static void handleUnExpectedExceptionInfo(Throwable ex, StringBuffer mav) {
		String message = ex.getMessage();
		Throwable cause = ex.getCause();
		if (Objects.nonNull(cause)) {
			message = ex.getCause().getMessage();
		}

		if(StringUtils.isBlank(message)){
			return;
		}

		if (RegexUtils.containChinese(message)) {
			mav.append(message);
		} else {
			mav.append(ErrorCode.SYSTEM_ERROR.getMessage());
		}
	}

	private static void setMessage(Throwable ex, StringBuffer mav) {
		Object message = null;
		Method method = ClassUtils.getMethod(ex.getClass(),"getMessage");
		try {
			if(Objects.nonNull(method)){
				message = method.invoke(ex);
			}
        } catch (Exception e) {
			log.error("{}",e.getMessage());
        }
		if( Objects.nonNull(message) ){
			mav.append(message);
		}
	}

}
