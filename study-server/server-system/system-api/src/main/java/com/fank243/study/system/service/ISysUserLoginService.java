package com.fank243.study.system.service;

import com.fank243.study.system.constants.SystemApiConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fank243.study.system.domain.dto.SysUserLoginDTO;
import com.fank243.study.common.utils.ResultInfo;

/**
 * 管理员登录 客户端
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@FeignClient(value = "server-system", path = SystemApiConstants.BASE_URI_SYSTEM)
public interface ISysUserLoginService {

    /**
     * 管理员登录
     * 
     * @param loginReq 请求参数
     * @return 用户ID
     */
    @PostMapping("/login")
    ResultInfo<String> login(@RequestBody SysUserLoginDTO loginReq);
}
