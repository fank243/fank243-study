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

package com.github.fank243.kong.support.domain.dto;

import com.github.fank243.kong.core.base.BaseDTO;
import com.github.fank243.kong.core.constants.Constants;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 文件表
 *
 * @author FanWeiJie
 * @since 2022-09-28 14:23:01
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class FileDTO extends BaseDTO {

    /*** 文件ID */
    private String fileId;

    /*** 文件名称(原始文件名称) */
    private String fileName;

    /*** 文件类型 */
    private String fileType;

    /*** 文件前缀URI */
    private String filePrefix;

    /*** 文件相对路径 */
    private String filePath;

    /*** 文件大小(kb) */
    private Long fileSize;

    /*** 文件MD5值 */
    private String fileMd5;

    /*** 登录人ID */
    private String loginId;

    /*** 文件 MineType */
    private String mineType;

    public String getFilePrefix() {
        if (StrUtil.equals(this.filePrefix, Constants.FILE_PREFIX_USER)) {
            return filePrefix + "/" + loginId;
        }
        return filePrefix;
    }
}
