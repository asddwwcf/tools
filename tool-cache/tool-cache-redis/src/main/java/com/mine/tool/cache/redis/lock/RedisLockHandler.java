package com.mine.tool.cache.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import redis.clients.jedis.JedisCommands;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * 功能 :
 */
@Slf4j
public class RedisLockHandler implements LockHandler {

    private static final Long SUCCESS = 1L;
    private static final String OK = "OK";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean lock(String key, String value, long seconds) {
        Boolean result = redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            JedisCommands jedisCommands = (JedisCommands) connection.getNativeConnection();
            String ret = jedisCommands.set(key, value, "NX", "EX", seconds);
            return OK.equals(ret);
        });
        return null != result? result : false;
    }

    @Override
    public boolean tryLock(String key, String value, long seconds) {
        try{
            // 为了让redis操作原子性,这里使用redis的lua脚本,让get操作和set操作具有原子性
            String script = "if redis.call('setNx',KEYS[1],ARGV[1]) then if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end end";
            RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);
            Object result = redisTemplate.execute(redisScript, Collections.singletonList(key),value,seconds);
            return SUCCESS.equals(result);
        }catch(Exception e){
            log.error("{}",e.getMessage(),e);
        }
        return false;
    }

    @Override
    public boolean unlock(String key, String value) {
        // 为了让redis操作原子性,这里使用redis的lua脚本,让get操作和del操作具有原子性
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<String> redisScript = new DefaultRedisScript<>(script, String.class);
        Object result = redisTemplate.execute(redisScript, Collections.singletonList(key),value);
        return SUCCESS.equals(result);
    }
}
