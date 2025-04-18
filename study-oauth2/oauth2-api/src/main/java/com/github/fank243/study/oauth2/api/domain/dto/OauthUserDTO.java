package com.github.fank243.study.oauth2.api.domain.dto;

import java.util.Date;

import com.alibaba.fastjson2.annotation.JSONField;
import com.github.fank243.study.core.base.BaseDTO;
import com.github.fank243.study.core.constants.DateConstants;
import com.github.fank243.study.core.model.validation.ValidatorGroup;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 系统管理员表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OauthUserDTO extends BaseDTO {

    /** 用户ID */
    private String userId;

    /** 用户名 */
    @NotBlank(message = "请填写用户名", groups = {ValidatorGroup.Login.class})
    @NotBlank(message = "参数[username]不能为空", groups = {ValidatorGroup.Create.class})
    private String username;

    /** 昵称 */
    private String nickname;

    /** 登录密码 */
    @NotBlank(message = "请填写登录密码", groups = {ValidatorGroup.Login.class})
    @NotBlank(message = "参数[password]不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String password;

    /** 状态(0：正常，1：禁用，2：登录锁定) */
    private Integer status;

    /** 登录累计错误次数 */
    private Integer loginErrCount;

    /** 登录锁定时间 */
    @JSONField(format = DateConstants.YYYY_MM_DD_HH_MM_SS)
    private Date loginLockTime;

    private String openId;
}
