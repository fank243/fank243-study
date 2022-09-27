package com.fank243.study.api.system.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fank243.study.api.system.constants.SystemApiConstants;
import com.fank243.study.api.system.domain.dto.SysUserLoginDTO;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.common.web.exception.AuthException;
import com.fank243.study.core.constants.ValidatorGroup;

/**
 * 系统管理员
 *
 * @author FanWeiJie
 * @since 2021-09-03
 * @restApi
 */
public interface ISysUserLoginApi {

    /**
     * 系统管理员 登录
     *
     * @param loginReq 登录参数
     * @return 用户实体
     * @throws AuthException AuthException
     */
    @PostMapping(SystemApiConstants.BASE_URI_SYSTEM + "/login")
    ResultInfo<String> login(@RequestBody @Validated({ValidatorGroup.Login.class}) SysUserLoginDTO loginReq)
        throws AuthException;

}
