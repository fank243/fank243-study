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

package com.github.fank243.kong.system.domain.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 管理登录响应参数
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Data
public class SysUserLoginResp implements Serializable {

    /*** 用户ID */
    private Long id;

    /*** 用户名 */
    private String username;

    /*** 昵称 */
    private String nickname;

    /*** 状态(0：正常，1：禁用，2：登录锁定) */
    private Integer status;

    /*** 登录累计错误次数 */
    private Integer loginErrCount;

    /*** 登录锁定时间 */
    private Date loginLockTime;

    /*** 最近登录时间 */
    private Date lastLoginTime;

    /*** 最近登录IP */
    private String lastLoginIp;

}
