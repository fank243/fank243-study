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

package com.github.fank243.kong.system.domain.dto;

import org.hibernate.validator.constraints.Length;

import com.github.fank243.kong.core.base.BaseDTO;
import com.github.fank243.kong.core.model.validation.ValidatorGroup;
import com.mzt.logapi.starter.annotation.DiffLogAllFields;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 系统角色表
 *
 * @author FanWeiJie
 * @since 2022-06-27
 */
@Getter
@Setter
@SuperBuilder
@DiffLogAllFields
@NoArgsConstructor
public class SysRoleDTO extends BaseDTO {

    /** 角色ID */
	@NotNull(message = "角色ID必传", groups = {ValidatorGroup.Modify.class})
    private Long roleId;

    /*** 角色代码 */
    @Length(min = 2, max = 10, message = "角色代码长度在3-20位之间",
        groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    @NotBlank(message = "请填写角色代码", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String roleCode;

    /*** 角色名称 */
    @Length(min = 3, max = 20, message = "角色名称长度在3-20位之间",
        groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    @NotBlank(message = "请填写角色名称", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private String roleName;

    /*** 角色描述 */
    private String roleDesc;

    /*** 状态(0：正常，1：禁用) */
    @NotNull(message = "请选择角色状态", groups = {ValidatorGroup.Create.class, ValidatorGroup.Modify.class})
    private Integer status;
}
