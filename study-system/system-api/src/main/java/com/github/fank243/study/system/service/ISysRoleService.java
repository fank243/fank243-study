package com.github.fank243.study.system.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.github.fank243.study.core.constants.ServerConstants;
import com.github.fank243.study.system.domain.vo.SysRoleVO;

/**
 * 系统角色表 客户端
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@FeignClient(contextId = "iSysRoleService", value = ServerConstants.SERVER_SYSTEM,
    path = ServerConstants.BASE_URI_SYSTEM + ServerConstants.BASE_URI_SYSTEM_ROLE)
public interface ISysRoleService {

    /**
     * 根据用户ID获取用户所有角色
     *
     * @param userId 用户ID
     * @return 用户角色
     */
    @GetMapping("/user/{userId}")
    List<SysRoleVO> getByUserId(@PathVariable("userId") String userId);

}
