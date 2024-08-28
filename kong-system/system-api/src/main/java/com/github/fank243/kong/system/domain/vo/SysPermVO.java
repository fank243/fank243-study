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

import lombok.Data;

/**
 * 系统权限表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Data
public class SysPermVO implements Serializable {

    /*** 权限ID */
    private Long permId;

    /*** 权限代码 */
    private String permCode;

    /*** 权限地址 */
    private String permUri;

    /*** 权限名称 */
    private String permName;

    /*** 权限描述 */
    private String permDesc;

    /*** 状态(0：正常，1：禁用) */
    private Integer status;

}
