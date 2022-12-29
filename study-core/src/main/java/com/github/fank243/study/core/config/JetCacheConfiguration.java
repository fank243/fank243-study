package com.github.fank243.study.core.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.github.fank243.study.core.constants.Constants;

/**
 * JetCache 自动配置
 *
 * @author FanWeiJie
 * @since 2022-09-30 23:26:02
 */
@EnableMethodCache(basePackages = {Constants.BASE_PACKAGE_SERVICE})
@AutoConfiguration
public class JetCacheConfiguration {}
