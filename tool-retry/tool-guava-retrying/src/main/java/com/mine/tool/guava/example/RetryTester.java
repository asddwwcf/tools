package com.mine.tool.guava.example;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;

/**
 * 根据调用返回接口判断是否需要重试
 */

public class RetryTester {

	public static void main(String[] args) throws ExecutionException, RetryException {
		Retryer<Long> retryer = RetryerBuilder.<Long>newBuilder()
				// 返回false也需要重试
                .retryIfResult(Predicates.equalTo(1L))
                // 重调策略
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
                // 尝试次数
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();
		
		retryer.call(new Callable<Long>() {
	            @Override
	            public Long call() throws Exception {
	            	System.out.println("返回值是0L，看我能出现几次");
	            	return 1L;
	            }
	        }
		);
		
	}
}