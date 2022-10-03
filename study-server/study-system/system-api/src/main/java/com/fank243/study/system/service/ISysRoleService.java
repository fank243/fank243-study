package com.fank243.study.system.service;

import org.springframework.cloud.openfeign.FeignClient;

import com.fank243.study.common.core.constants.ServerConstants;

/**
 * 系统角色表 客户端
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@FeignClient(contextId = "iSysRoleService", value = ServerConstants.SERVER_SYSTEM,
    path = ServerConstants.BASE_URI_SYSTEM_ROLE)
public interface ISysRoleService {

}
