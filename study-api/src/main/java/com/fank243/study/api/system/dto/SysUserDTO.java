package com.fank243.study.api.system.dto;

import com.fank243.study.api.constants.ValidatorGroup;
import com.fank243.study.core.base.BaseDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 系统管理员表
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserDTO extends BaseDTO {

    /** 用户名 */
    @NotBlank(message = "请填写用户名", groups = {ValidatorGroup.Create.class})
    private String username;

    /** 密码 **/
    @NotBlank(message = "请填写密码", groups = {ValidatorGroup.Create.class})
    private String password;

}
