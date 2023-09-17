package com.github.fank243.study.oauth2.api.domain.dto;

import com.github.fank243.study.core.base.BaseDTO;
import com.github.fank243.study.core.model.validation.ValidatorGroup;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 登录实体
 * 
 * @author FanWeiJie
 * @date 2023-01-13 09:28
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class OauthLoginDTO extends BaseDTO {

    /** 用户名 */
    @NotBlank(message = "请填写用户名", groups = {ValidatorGroup.Login.class})
    @NotBlank(message = "参数[username]不能为空", groups = {ValidatorGroup.Create.class})
    private String username;

    /** 登录密码 */
    @NotBlank(message = "请填写登录密码", groups = {ValidatorGroup.Login.class})
    @NotBlank(message = "参数[password]不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String password;

    @NotBlank(message = "请填写图形验证码", groups = {ValidatorGroup.Login.class})
    private String imgCode;

    @NotBlank(message = "请重新获取图形验证码", groups = {ValidatorGroup.Login.class})
    private String randomStr;
}
