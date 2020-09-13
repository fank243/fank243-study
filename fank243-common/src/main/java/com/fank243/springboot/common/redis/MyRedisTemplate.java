package com.fank243.springboot.common.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * RedisTemplate
 * 
 * @author FanWeiJie
 * @date 2020-09-12 22:35:32
 */
public class MyRedisTemplate extends RedisTemplate<String, Object> {
    public MyRedisTemplate() {
        this.setKeySerializer(RedisSerializer.json());
        this.setValueSerializer(RedisSerializer.json());
        this.setHashKeySerializer(RedisSerializer.json());
        this.setHashValueSerializer(RedisSerializer.json());
    }

    public MyRedisTemplate(RedisConnectionFactory connectionFactory) {
        this();
        this.setConnectionFactory(connectionFactory);
        this.afterPropertiesSet();
    }

}
