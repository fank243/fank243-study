package com.fank243.study.api.system.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.fank243.study.api.system.api.ISysPermApi;

/**
 * 系统权限表 客户端
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@FeignClient(value = "server-system")
public interface ISysPermFeignClient extends ISysPermApi {

}
