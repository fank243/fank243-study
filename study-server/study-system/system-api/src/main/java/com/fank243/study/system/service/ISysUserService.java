package com.fank243.study.system.service;

import org.springframework.cloud.openfeign.FeignClient;

import com.fank243.study.common.core.constants.ServerConstants;

/**
 * 系统管理员表 客户端
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@FeignClient(contextId = "iSysUserService", value = ServerConstants.SERVER_SYSTEM,
    path = ServerConstants.BASE_URI_SYSTEM_ADMIN)
public interface ISysUserService {

}
