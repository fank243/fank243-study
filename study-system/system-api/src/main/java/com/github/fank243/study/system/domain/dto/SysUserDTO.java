package com.github.fank243.study.system.domain.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.github.fank243.study.core.base.BaseDTO;
import com.github.fank243.study.core.constants.ValidatorGroup;
import com.mzt.logapi.starter.annotation.DIffLogIgnore;
import com.mzt.logapi.starter.annotation.DiffLogAllFields;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统管理员表
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
@DiffLogAllFields
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
    @DIffLogIgnore
    private String password;

    public String getUsername() {
        return StrUtil.trimToEmpty(this.username);
    }

    public String getNickname() {
        return StrUtil.trimToEmpty(this.nickname);
    }
}
