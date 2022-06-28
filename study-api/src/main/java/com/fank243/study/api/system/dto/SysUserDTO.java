package com.fank243.study.api.system.dto;

import javax.validation.constraints.NotBlank;

import com.fank243.study.api.constants.ValidatorGroup;
import com.fank243.study.core.base.BaseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统管理员表
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserDTO extends BaseDTO {

    /** 用户ID */
    @NotBlank(message = "用户ID必传", groups = {ValidatorGroup.Modify.class})
    private String userId;

    /** 用户名 */
    @NotBlank(message = "请填写用户名")
    private String username;

    /*** 昵称 */
    private String nickname;

    /** 密码 **/
    @NotBlank(message = "请填写密码")
    private String password;

}
