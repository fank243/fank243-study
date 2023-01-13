package com.github.fank243.study.oauth2.api.domain.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 登录实体
 * 
 * @author FanWeiJie
 * @date 2023-01-13 09:28
 */
@Data
public class Oauth2LoginDTO implements Serializable {

    @NotBlank(message = "请填写登录账号")
    private String username;

    @NotBlank(message = "请填写登录密码")
    private String password;

    @NotBlank(message = "请填写图形验证码")
    private String imgCode;

    @NotBlank(message = "请重新获取图形验证码")
    private String randomStr;
}
