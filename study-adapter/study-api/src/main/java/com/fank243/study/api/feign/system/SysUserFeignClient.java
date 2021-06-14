package com.fank243.study.api.feign.system;

import com.fank243.study.client.api.system.SysUserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 系统管理员
 * 
 * @author FanWeiJie
 * @date 2021-06-09 00:11:49
 */
@FeignClient(value = "study-system")
public interface SysUserFeignClient extends SysUserApi {

}
