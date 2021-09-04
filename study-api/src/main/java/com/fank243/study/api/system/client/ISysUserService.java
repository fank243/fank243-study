package com.fank243.study.api.system.client;

import com.fank243.study.api.system.ISysUserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 * 系统管理员表 客户端
 * </p>
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@FeignClient(value = "server-system")
public interface ISysUserService extends ISysUserApi {

}

