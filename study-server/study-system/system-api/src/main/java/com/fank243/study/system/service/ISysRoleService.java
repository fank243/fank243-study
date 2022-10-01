package com.fank243.study.system.service;

import java.util.List;

import com.fank243.study.system.domain.vo.SysRoleVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fank243.study.system.constants.SystemApiConstants;

/**
 * 系统角色表 客户端
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@FeignClient(contextId = "iSysRoleService", value = "server-system", path = SystemApiConstants.BASE_URI_SYSTEM_ROLE)
public interface ISysRoleService {

    /**
     * 根据用户ID获取用户所有角色
     *
     * @param userId 用户ID
     * @return 用户角色
     */
    @GetMapping("/getByUserId/{userId}")
    List<SysRoleVO> getByUserId(@PathVariable("userId") String userId);
}
