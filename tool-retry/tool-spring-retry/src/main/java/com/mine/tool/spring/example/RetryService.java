package com.mine.tool.spring.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

@Slf4j
public class RetryService {

    /**
     * value：指定处理的异常类
     * maxAttempts：最大重试次数。默认3次
     * backoff： 重试等待策略。默认使用@Backoff注解
     * include：指定处理的异常类和value一样，默认为空，当exclude也为空时，默认所有异常
     * exclude：指定异常不处理，默认空，当include也为空时，默认所有异常
     * delay：延迟ms
     * multiplier：延迟倍数
     */
    @Retryable(value = RuntimeException.class,maxAttempts = 5,backoff = @Backoff(delay = 1000,multiplier = 1))
    public boolean retryMethod(String requestId) {
        log.info("Processing request: {}", requestId);
        throw new RuntimeException("Failed Request");
    }

    /**
     * @Recover 用于@Retryable重试失败后处理方法
     */
    @Recover
    public boolean retryMethod(RuntimeException ex, String requestId) {
        log.error("Recovering request {} - {}", requestId,ex.getMessage());
        return false;
    }
}
