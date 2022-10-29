package com.github.fank243.study.oauth2.api.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.github.fank243.study.core.constants.ValidatorGroup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统管理员表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OauthUserDTO implements Serializable {

    /** 用户名 */
    @NotBlank(message = "参数[username]不能为空", groups = {ValidatorGroup.Create.class})
    private String username;

    /** 昵称 */
    private String nickname;

    /** 登录密码 */
    @NotBlank(message = "参数[password]不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String password;

    @NotBlank(message = "参数[accessToken]不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String accessToken;

    @NotBlank(message = "参数[openId]不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String openId;
}
