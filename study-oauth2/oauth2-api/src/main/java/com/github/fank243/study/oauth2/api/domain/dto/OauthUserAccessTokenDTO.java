package com.github.fank243.study.oauth2.api.domain.dto;

import com.github.fank243.study.core.model.validation.ValidatorGroup;
import com.github.fank243.study.oauth2.api.domain.entity.OauthUserEntity;

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
public class OauthUserAccessTokenDTO extends OauthUserEntity {

    @NotBlank(message = "参数[accessToken]不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String accessToken;

    @NotBlank(message = "参数[openId]不能为空", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String openId;
}
