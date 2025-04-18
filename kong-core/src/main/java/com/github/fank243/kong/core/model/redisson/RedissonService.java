/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fank243.kong.core.model.redisson;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

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
	 * 获取锁并锁定，如果KEY已被锁定，会一直等待直到获得锁 redis中生成一条hash结构数据体
     *
     * @param lockKey 锁KEY
     */
    public void lock(String lockKey) {
        getLock(lockKey).lock();
    }

    /**
	 * 获取锁并锁定(秒)，如果KEY已被锁定，会一直等待直到获得锁 redis中生成一条hash结构数据体
     *
     * @param lockKey 锁KEY
     * @param leaseTime 锁定时长(秒)
     */
    public void lock(String lockKey, long leaseTime) {
        getLock(lockKey).lock(leaseTime, TimeUnit.SECONDS);
    }

    /**
	 * 锁定并设定锁定时长(自定义时间单位) 如果KEY已被锁定，会一直等待直到获得锁 redis中生成一条hash结构数据体
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