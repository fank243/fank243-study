/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.github.fank243.kong.oauth2.domain;

import java.io.Serializable;

import com.github.fank243.kong.core.annotation.CustomerIdGenerator;
import com.github.fank243.kong.core.configuration.YitIdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 授权客户端表
 *
 * @author FanWeiJie
 * @since 2021-11-26
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "tb_oauth_client")
public class OauthClientEntity implements Serializable {

    /** AppID */
    @Id
    @CustomerIdGenerator
    private String clientId;

    /** AppSecret */
    private String clientSecret;

    /** 资源ID集合(英文逗号分割) */
    private String resourceIds;

    /** 授权范围 */
    private String scope;

    /** 授权类型 */
    private String grantTypes;

    /** 重定向URL */
    private String redirectUrl;

    /** 其他信息 */
    private String additionalInformation;

}
