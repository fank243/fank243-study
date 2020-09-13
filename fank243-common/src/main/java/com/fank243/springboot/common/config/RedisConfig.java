package com.fank243.springboot.common.config;

import com.fank243.springboot.common.redis.MyRedisTemplate;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis Configuration
 *
 * @author FanWeiJie
 * @date 2019-05-30 13:35:22
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Bean
    public MyRedisTemplate redisTemplate(RedisConnectionFactory factory) {
        return new MyRedisTemplate(factory);
    }
}