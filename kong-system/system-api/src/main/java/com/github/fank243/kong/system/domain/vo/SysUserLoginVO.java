/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fank243.kong.system.domain.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 系统管理员表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Data
public class SysUserLoginVO implements Serializable {

    /** token剩余有效期 (单位: 秒) */
    public long tokenTimeout;
    /** User-Session剩余有效时间 (单位: 秒) */
    public long sessionTimeout;
    /** Token-Session剩余有效时间 (单位: 秒) */
    public long tokenSessionTimeout;
	/**
	 * token 距离被冻结还剩多少时间（单位: 秒） (单位: 秒)
	 */
	public long tokenActiveTimeout;
    /*** 用户ID */
    private Long id;
    /*** 用户名 */
    private String username;
    /*** 昵称 */
    private String nickname;
    /*** 最近登录时间 */
    private Date lastLoginTime;
    /*** 最近登录IP */
    private String lastLoginIp;
    /** 令牌名称 */
    private String tokenName;
    /** 登陆令牌 */
    private String tokenValue;

}