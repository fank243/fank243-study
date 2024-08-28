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

package com.github.fank243.kong.system.domain.enums;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

/**
 * 权限类型
 * 
 * @author FanWeiJie
 * @since 2022-06-27 11:11:49
 */
@Getter
public enum PermTypeEnum {

    /** 目录 **/
    DIR(0, "目录"),

    MENU(1, "菜单"),

    BUTTON(2, "接口");

    /** 访问鉴权 **/
    public static final List<Integer> PERMS = Arrays.asList(MENU.code, BUTTON.code);

    private final int code;

    private final String desc;

    PermTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PermTypeEnum getEnum(int code) {
        PermTypeEnum[] values = PermTypeEnum.values();
        for (PermTypeEnum value : values) {
            if (code == value.getCode()) {
                return value;
            }
        }
        return null;
    }

    @SuppressWarnings("unused")
    public static boolean valid(int code) {
        return getEnum(code) != null;
    }
}
