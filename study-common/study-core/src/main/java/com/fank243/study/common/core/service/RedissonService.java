package com.fank243.study.common.core.service;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

/**
 * Redisson 分布式锁工具类
 * 
 * @author FanWeiJie
 * @since 2022-09-30 09:40:33
 */
@Component
public class RedissonService {

    @Resource
    private RedissonClient redissonClient;

    /**
     * 获取锁
     *
     * @param lockKey 锁KEY
     * @return RLock
     */
    public RLock getLock(String lockKey) {
        return redissonClient.getLock(lockKey);
    }

    /**
     * 获取锁并锁定
     * 
     * 如果KEY已被锁定，会一直等待直到获得锁
     * 
     * redis中生成一条hash结构数据体
     *
     * @param lockKey 锁KEY
     */
    public void lock(String lockKey) {
        getLock(lockKey).lock();
    }

    /**
     * 获取锁并锁定(秒)
     *
     * 如果KEY已被锁定，会一直等待直到获得锁
     * 
     * redis中生成一条hash结构数据体
     * 
     * @param lockKey 锁KEY
     * @param leaseTime 锁定时长(秒)
     */
    public void lock(String lockKey, long leaseTime) {
        getLock(lockKey).lock(leaseTime, TimeUnit.SECONDS);
    }

    /**
     * 锁定并设定锁定时长(自定义时间单位)
     *
     * 如果KEY已被锁定，会一直等待直到获得锁
     * 
     * redis中生成一条hash结构数据体
     * 
     * @param lockKey 锁KEY
     * @param timeout 锁定时长
     * @param unit 时间单位
     */
    public void lock(String lockKey, long timeout, TimeUnit unit) {
        getLock(lockKey).lock(timeout, unit);
    }

    /**
     * 尝试获取锁
     *
     * @param lockKey 锁KEY
     * @param waitTime 等待时长
     * @param timeout 锁定时长
     * @param unit 时间单位
     * @return 获取锁结果
     */
    public boolean tryLock(String lockKey, long waitTime, long timeout, TimeUnit unit) {
        try {
            return getLock(lockKey).tryLock(waitTime, timeout, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    /**
     * 尝试获取锁，不设定锁定时长
     *
     * @param lockKey 锁KEY
     * @param waitTime 等待时长
     * @param unit 时间单位
     * @return boolean
     */
    public boolean tryLock(String lockKey, long waitTime, TimeUnit unit) {
        try {
            return getLock(lockKey).tryLock(waitTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    /**
     * 释放锁
     * 
     * @param lockKey 锁KEY
     */
    public void unlock(String lockKey) {
        getLock(lockKey).unlock();
    }

    /**
     * 释放锁
     * 
     * @param lock RLock
     */
    public void unlock(RLock lock) {
        lock.unlock();
    }

}