package com.fank243.study.api.system.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.fank243.study.api.system.api.ISysUserLoginApi;

/**
 * 管理员登录 客户端
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@FeignClient(value = "server-system")
public interface ISysUserLoginFeignClient extends ISysUserLoginApi {

}
