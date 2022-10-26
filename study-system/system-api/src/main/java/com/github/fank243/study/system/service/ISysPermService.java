package com.github.fank243.study.system.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.fank243.study.core.constants.ServerConstants;
import com.github.fank243.study.system.domain.vo.SysPermVO;

/**
 * 系统权限表 客户端
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@FeignClient(contextId = "iSysPermService", value = ServerConstants.SERVER_SYSTEM,
    path = ServerConstants.BASE_URI_SYSTEM_PERM)
public interface ISysPermService {

    /**
     * 根据用户ID获取用户所有权限
     *
     * @param userId 用户ID
     * @return 用户权限
     */
    @GetMapping("/user/{userId}")
    List<SysPermVO> getByUserId(@PathVariable("userId") String userId);

    /**
     * 根据权限类型获取所有权限
     *
     * @param perms 权限类型列表
     * @return 权限列表
     */
    @PostMapping("/types")
    List<SysPermVO> getByPermTypes(@RequestBody List<Integer> perms);

}
