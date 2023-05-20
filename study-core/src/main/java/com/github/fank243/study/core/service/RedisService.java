package com.github.fank243.study.core.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * spring data redis 工具类
 * 
 * @author FanWeiJie
 * @since 2021-04-05 23:41:10
 **/
@SuppressWarnings(value = {"unchecked", "rawtypes", "UnusedReturnValue", "unused"})
@Component
public class RedisService {

    @Resource
    public RedisTemplate redisTemplate;

    /**
     * 设置缓存 Object
     * 
     * @param key Key
     * @param value Value
     */
    public <T> boolean setObj(final String key, final T value) {
        return setObj(key, value, -1);
    }

    /**
     * 设置缓存 Object
     * 
     * @param key Key
     * @param value Value
     * @param timeOut 过期时间
     */
    public <T> boolean setObj(final String key, final T value, long timeOut) {
        return setObj(key, value, timeOut, TimeUnit.SECONDS);
    }

    /**
     * 获取缓存 Object
     * 
     * @param key Key
     * @param value Value
     * @param timeout 过期时间
     * @param timeUnit 过期时间单位
     */
    public <T> boolean setObj(final String key, final T value, final Long timeout, TimeUnit timeUnit) {
        if (key == null || "".equals(key)) {
            return Boolean.FALSE;
        }
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
        return Boolean.TRUE;
    }

    /**
     * 获取缓存 Object
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public <T> T getObj(final String key) {
        if (key == null || "".equals(key)) {
            return null;
        }
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 设置缓存 List
     *
     * @param key Key
     * @param value List
     */
    public <T> boolean setList(final String key, final List<T> value) {
        return setList(key, value, -1);
    }

    /**
     * 设置缓存 List
     *
     * @param key Key
     * @param value List
     * @param timeout 过期时间
     */
    public <T> boolean setList(final String key, final List<T> value, long timeout) {
        return setList(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存 List
     *
     * @param key Key
     * @param value List
     * @param timeout 过期时间
     * @param timeUnit 过期时间单位
     */
    public <T> boolean setList(final String key, final List<T> value, long timeout, TimeUnit timeUnit) {
        if (key == null || "".equals(key) || value == null) {
            return Boolean.FALSE;
        }
        Long count = redisTemplate.opsForList().rightPushAll(key, value);
        if (count != null && count > 0) {
            return expire(key, timeout, timeUnit);
        }
        return Boolean.FALSE;
    }

    /**
     * 获取缓存 List
     *
     * @param key 缓存的键值
     */
    public <T> List<T> getList(final String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 设置缓存 Set集合
     *
     * @param key Key
     * @param value Set
     */
    public <T> boolean setSet(final String key, final Set<T> value) {
        return setSet(key, value, -1);
    }

    /**
     * 设置缓存 Set集合
     *
     * @param key Key
     * @param value Set
     * @param timeout 过期时间
     */
    public <T> boolean setSet(final String key, final Set<T> value, long timeout) {
        return setSet(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存 Set集合
     *
     * @param key Key
     * @param value Set
     * @param timeout 过期时间
     * @param timeUnit 过期时间单位
     */
    public <T> boolean setSet(final String key, final Set<T> value, long timeout, TimeUnit timeUnit) {
        if (key == null || "".equals(key) || value == null) {
            return Boolean.FALSE;
        }
        BoundSetOperations<String, T> operations = redisTemplate.boundSetOps(key);
        for (T t : value) {
            operations.add(t);
        }
        Long operationsSize = operations.size();
        if (operationsSize != null && operationsSize > 0) {
            return expire(key, timeout, timeUnit);
        }
        return Boolean.FALSE;
    }

    /**
     * 获取缓存 Set集合
     *
     * @param key Key
     * @return Set集合
     */
    public <T> Set<T> getSet(final String key) {
        if (key == null || "".equalsIgnoreCase(key)) {
            return null;
        }
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 设置缓存 Hash
     *
     * @param key Key
     * @param value Hash
     */
    public <T> boolean setHash(final String key, final Map<String, T> value) {
        return setHash(key, value, -1);
    }

    /**
     * 设置缓存 Hash
     *
     * @param key Key
     * @param value Hash
     * @param timeout 过期时间
     */
    public <T> boolean setHash(final String key, final Map<String, T> value, long timeout) {
        return setHash(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存 Hash
     * 
     * @param key Key
     * @param value Hash
     * @param timeout 过期时间
     * @param timeUnit 过期时间单位
     */
    public <T> boolean setHash(final String key, final Map<String, T> value, long timeout, TimeUnit timeUnit) {
        if (key == null || "".equalsIgnoreCase(key) || value == null) {
            return Boolean.FALSE;
        }
        redisTemplate.opsForHash().putAll(key, value);
        expire(key, timeout, timeUnit);
        return Boolean.TRUE;
    }

    /**
     * 获取缓存 Hash
     *
     * @param key Key
     * @return Hash
     */
    public <T> Map<String, T> getHash(final String key) {
        if (key == null || "".equalsIgnoreCase(key)) {
            return null;
        }
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 设置缓存 Hash
     *
     * @param key Key
     * @param hashKey Hash Key
     * @param value Hash
     */
    public <T> boolean setHashValue(final String key, final String hashKey, final T value) {
        return setHashValue(key, hashKey, value, -1);
    }

    /**
     * 设置缓存Hash，单位：秒
     *
     * @param key Key
     * @param hashKey Hash Key
     * @param value Hash
     * @param timeout 过期时间
     */
    public <T> boolean setHashValue(final String key, final String hashKey, final T value, long timeout) {
        return setHashValue(key, hashKey, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存 Hash
     * 
     * @param key Key
     * @param hashKey Hash Key
     * @param value Hash
     * @param timeout 过期时间
     * @param timeUnit 过期时间单位
     */
    public <T> boolean setHashValue(final String key, final String hashKey, final T value, long timeout,
        TimeUnit timeUnit) {
        if (key == null || "".equalsIgnoreCase(key) || hashKey == null || "".equalsIgnoreCase(hashKey)
            || value == null) {
            return Boolean.FALSE;
        }
        redisTemplate.opsForHash().put(key, hashKey, value);
        expire(key, timeout, timeUnit);
        return Boolean.TRUE;
    }

    /**
     * 获取Hash中的数据
     *
     * @param key Key
     * @param hashKey Hash Key
     * @return Hash
     */
    public <T> T getHashValue(final String key, final String hashKey) {
        if (key == null || "".equalsIgnoreCase(key) || hashKey == null || "".equalsIgnoreCase(hashKey)) {
            return null;
        }
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hashKey);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key Key
     * @param hashKeys Hash Key 集合
     * @return Hash 集合
     */
    public <T> List<T> getMultiHashValue(final String key, final Collection<String> hashKeys) {
        if (key == null || "".equalsIgnoreCase(key) || hashKeys == null || hashKeys.isEmpty()) {
            return null;
        }
        return redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    /**
     * 设置有效时间
     *
     * @param key Redis键
     * @param timeout 超时时间
     * @param timeUnit 时间单位
     */
    private boolean expire(final String key, final long timeout, final TimeUnit timeUnit) {
        Boolean isSuccess = redisTemplate.expire(key, timeout, timeUnit);
        return isSuccess != null && isSuccess ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * 获取 Key 过期时间
     *
     * @param key Key
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(final String key) {
        Long seconds = redisTemplate.getExpire(key, TimeUnit.SECONDS);
        return seconds == null ? 0 : seconds;
    }

    /**
     * 判断某个 Key 在缓存中是否存在
     * 
     * @param key Key
     * @return 对象列表
     */
    public boolean hasKey(final String key) {
        Boolean isSuccess = redisTemplate.hasKey(key);
        return isSuccess != null && isSuccess ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * 根据表达式获取缓存中的 Key 列表
     * 
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public Collection<String> getKeys(final String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 删除缓存
     *
     * @param key Key
     */
    public boolean delete(final String key) {
        Boolean isSuccess = redisTemplate.delete(key);
        return isSuccess != null && isSuccess ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * 删除缓存
     *
     * @param keys Key 集合
     * @return 删除成功行数
     */
    public long delete(final Collection<String> keys) {
        Long rows = redisTemplate.delete(keys);
        return rows != null && rows > 0 ? rows : 0;
    }
}
