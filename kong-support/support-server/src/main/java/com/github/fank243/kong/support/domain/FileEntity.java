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

package com.github.fank243.kong.support.domain;

import com.github.fank243.kong.core.base.BaseEntity;

import com.github.fank243.kong.core.configuration.YitIdGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

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
@Entity
@Table(name = "tb_file")
public class FileEntity extends BaseEntity {

    /** 文件名称(原始文件名称) */
    private String fileName;

    /*** 文件 MineType */
    private String mineType;

    /** 文件类型 */
    private String fileType;

    /*** 文件前缀URI */
    private String filePrefix;

    /** 文件相对路径 */
    private String filePath;

    /** 文件大小(kb) */
    private Long fileSize;

    /** 文件MD5值 */
    private String fileMd5;

}
