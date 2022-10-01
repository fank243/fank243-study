package com.fank243.study.system.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fank243.study.system.constants.SystemApiConstants;
import com.fank243.study.system.domain.vo.SysPermVO;

/**
 * 系统权限表 客户端
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@FeignClient(contextId = "iSysPermService", value = "server-system", path = SystemApiConstants.BASE_URI_SYSTEM_PERM)
public interface ISysPermService {

    /**
     * 根据用户ID获取用户所有权限
     *
     * @param userId 用户ID
     * @return 用户权限
     */
    @GetMapping("/getByUserId/{userId}")
    List<SysPermVO> getByUserId(@PathVariable("userId") String userId);

    /**
     * 根据权限类型获取所有权限
     *
     * @param perms 权限类型列表
     * @return 权限列表
     */
    @PostMapping("/getByPermTypes")
    List<SysPermVO> getByPermTypes(@RequestBody List<Integer> perms);
}
