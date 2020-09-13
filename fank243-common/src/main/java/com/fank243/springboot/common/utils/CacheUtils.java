package com.fank243.springboot.common.utils;

import com.fank243.springboot.common.redis.MyRedisTemplate;
import com.fank243.springboot.common.utils.spring.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * cache tool
 * 
 * @author FanWeiJie
 * @date 2020-09-12 21:45:13
 */
@Slf4j
public class CacheUtils {
    /** redis cache **/
    private static final MyRedisTemplate REDIS_TEMPLATE = SpringUtils.getBean(MyRedisTemplate.class);

    /** Set redis Key expire time */
    private static void setExpireTime(String key, long time) {
        REDIS_TEMPLATE.expire(key, time, TimeUnit.SECONDS);
    }

    /** Set Key **/
    public static boolean set(String key, String value) {
        return set(key, value, -1);
    }

    /** Set Key **/
    public static boolean set(String key, String value, long expireTime) {
        try {
            if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
                if (log.isWarnEnabled()) {
                    log.warn("the redis key or value must be not null or empty:{},{}", key, value);
                }
                return false;
            }
            // set key
            REDIS_TEMPLATE.opsForValue().set(key, value);
            // set expire time
            if (expireTime > 0) {
                setExpireTime(key, expireTime);
            }
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("redis set key error:{}", e.toString());
            }
            return false;
        }
        return true;
    }

    /** Get Key **/
    public static Object get(String key) {
        if (StringUtils.isBlank(key)) {
            return "";
        }
        return REDIS_TEMPLATE.opsForValue().get(key);
    }

    /** Put Hash Key **/
    public static boolean hashPut(String key, String hashKey, Object value) {
        return hashPut(key, hashKey, value, -1);
    }

    /** Put Hash Key **/
    public static boolean hashPut(String key, String hashKey, Object value, long expireTime) {
        try {
            if (StringUtils.isBlank(key) || value == null) {
                if (log.isWarnEnabled()) {
                    log.warn("the redis hash key or hashKey or value must be not null or empty:{},{},{}", key, hashKey,
                        value);
                }
                return false;
            }
            // put hash key
            REDIS_TEMPLATE.opsForHash().put(key, hashKey, value);
            // set expire time
            if (expireTime > 0) {
                setExpireTime(key, expireTime);
            }
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("redis set hash key error:{}", e.toString());
            }
            return false;
        }

        return true;
    }

    /** Get Hash Key **/
    public static Object hashGet(String key, String hashKey) {
        if (StringUtils.isBlank(key)) {
            return "";
        }
        return REDIS_TEMPLATE.opsForHash().get(key, hashKey);
    }

    /** delete single key **/
    public static boolean delete(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        Boolean isDelete = REDIS_TEMPLATE.delete(key);
        return isDelete != null && isDelete;
    }

    /** delete multiple keys **/
    public static boolean delete(Collection<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return false;
        }
        Long rows = REDIS_TEMPLATE.delete(keys);
        return rows != null && rows > 0;
    }

    /** remove single hash key **/
    public static boolean remove(String key, String hashKey) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(hashKey)) {
            return false;
        }
        return REDIS_TEMPLATE.opsForHash().delete(key, hashKey) > 0;
    }

    /** remove multiple hash key **/
    public static boolean remove(String key, Object... hashKeys) {
        if (StringUtils.isBlank(key) || hashKeys == null || hashKeys.length <= 0) {
            return false;
        }
        return REDIS_TEMPLATE.opsForHash().delete(key, hashKeys) > 0;
    }

    /** redis has this key **/
    public static boolean hasKey(String key) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        Boolean hasKey = REDIS_TEMPLATE.hasKey(key);
        return hasKey != null && hasKey;
    }

}
