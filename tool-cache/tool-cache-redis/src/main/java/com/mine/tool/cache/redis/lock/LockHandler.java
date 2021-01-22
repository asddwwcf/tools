package com.mine.tool.cache.redis.lock;

/**
 * 功能 :
 */
public interface LockHandler {
    /**锁定:获取锁,同一把锁,不可以重复获取**/
    boolean lock(String key, String value, long seconds);
    /**锁定:获取锁,同一把锁,可以重复获取**/
    boolean tryLock(String key, String value, long seconds);
    /**解锁:释放锁,同一把锁,只能释放一次**/
    boolean unlock(String key, String value);
}
