package com.fank243.study.common.core.config;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.fank243.study.common.core.constants.Constants;
import org.springframework.boot.autoconfigure.AutoConfiguration;

/**
 * JetCache 自动配置
 * 
 * @author FanWeiJie
 * @since 2022-09-30 23:26:02
 */
@EnableMethodCache(basePackages = {Constants.BASE_PACKAGE})
@AutoConfiguration
public class JetCacheConfiguration {}
