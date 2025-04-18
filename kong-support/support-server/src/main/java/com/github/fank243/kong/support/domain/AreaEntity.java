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

package com.github.fank243.kong.support.domain;

import com.github.fank243.kong.core.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 国家行政区划表
 *
 * @author FanWeiJie
 * @since 2022-09-20 10:46:43
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = "tb_support_area")
public class AreaEntity extends BaseEntity {

    /** 省级行政区划代码 */
    private String provinceCode;

    /** 省级行政区划名称 */
    private String provinceName;

    /** 市级行政区划代码 */
    private String cityCode;

    /** 市级行政区划名称 */
    private String cityName;

    /** 区县级行政区划代码 */
    private String areaCode;

    /** 区县级行政区划名称 */
    private String areaName;
}
