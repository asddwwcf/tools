package com.mine.tool.guava.example;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
/**
 * 根据调用发生异常判断是否需要重试
 */

public class RetryTester2 {

	public static void main(String[] args) throws ExecutionException, RetryException {
		Retryer<Long> retryer = RetryerBuilder.<Long>newBuilder()
				.retryIfExceptionOfType(NullPointerException.class)
				//.retryIfException() 抛出runtime异常、checked异常时都会重试，但是抛出error不会重试
                //.retryIfRuntimeException() RuntimeException会重试
				// 重试2次后停止
                .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                .build();
		retryer.call(new Callable<Long>() {
	            @Override
	            public Long call() throws Exception {
	            	System.out.println("异常打印，看我能出现几次");
	            	throw new RuntimeException();
	            }
	        }
		);
		
	}
}