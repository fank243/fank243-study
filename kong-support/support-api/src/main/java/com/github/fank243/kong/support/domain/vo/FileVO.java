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

package com.github.fank243.kong.support.domain.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 文件表
 *
 * @author FanWeiJie
 * @since 2022-09-28 14:23:01
 */
@Data
public class FileVO implements Serializable {

    /*** 文件ID */
    private String fileId;

    /*** 文件名称 */
    private String fileName;

    /*** 文件类型 */
    private String fileType;

    /*** 文件后缀 */
    private String fileSuffix;

    /*** 文件相对路径 */
    private String filePath;

    /*** 文件大小(kb) */
    private Integer fileSize;

    /*** 文件MD5值 */
    private String md5;

}
