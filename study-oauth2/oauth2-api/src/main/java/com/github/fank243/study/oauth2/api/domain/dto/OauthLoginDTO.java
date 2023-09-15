package com.github.fank243.study.oauth2.api.domain.dto;

import com.github.fank243.study.core.model.validation.ValidatorGroup;
import com.github.fank243.study.oauth2.api.domain.entity.OauthUserEntity;

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
public class OauthLoginDTO extends OauthUserEntity {

    @NotBlank(message = "请填写图形验证码", groups = {ValidatorGroup.Login.class})
    private String imgCode;

    @NotBlank(message = "请重新获取图形验证码", groups = {ValidatorGroup.Login.class})
    private String randomStr;
}
