package com.fank243.study.system.domain.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.fank243.study.common.core.base.BaseDTO;
import com.fank243.study.common.core.constants.ValidatorGroup;

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
    @Length(min = 3, max = 20, message = "用户名长度在3-20位之间",
            groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    @NotBlank(message = "请填写用户名", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String username;

    /*** 昵称 */
    private String nickname;

    /** 密码 **/
    @Length(min = 3, max = 20, message = "密码长度在3-20位之间",
            groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    @NotBlank(message = "请填写密码", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String password;

}
