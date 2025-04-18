package com.github.fank243.study.oauth2.api.domain.dto;

import com.github.fank243.study.core.base.BaseDTO;
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
public class OauthUserAccessTokenDTO extends BaseDTO {

    @NotBlank(message = "参数[accessToken]不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String accessToken;

    @NotBlank(message = "参数[openId]不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String openId;

    /** 用户ID */
    private String userId;

    /** 用户名 */
    @NotBlank(message = "请填写用户名", groups = {ValidatorGroup.Login.class})
    @NotBlank(message = "参数[username]不能为空", groups = {ValidatorGroup.Create.class})
    private String username;

    /** 登录密码 */
    private String password;

    /** 昵称 */
    private String nickname;
}
