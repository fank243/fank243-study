package com.fank243.study.client.system;

import com.fank243.study.api.system.ISysUserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 系统管理员
 * 
 * @author FanWeiJie
 * @since 2021-06-16 21:41:16
 */
@FeignClient("server-system")
public interface ISysUserClient extends ISysUserApi {}
