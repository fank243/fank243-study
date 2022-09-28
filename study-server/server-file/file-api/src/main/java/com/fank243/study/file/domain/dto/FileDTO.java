package com.fank243.study.file.domain.dto;

import com.fank243.study.common.domain.base.BaseDTO;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件表
 *
 * @author FanWeiJie
 * @since 2022-09-28 14:23:01
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class FileDTO extends BaseDTO {

    /*** 文件ID */
    private String fileId;

    /*** 文件名称 */
    private String fileName;

    /** 文件名称(原始文件名称) */
    private String fileOriginalName;

    /*** 文件类型 */
    private String fileType;

    /*** 文件后缀 */
    private String fileSuffix;

    /*** 文件相对路径 */
    private String filePath;

    /*** 文件大小(kb) */
    private Long fileSize;

    /*** 文件MD5值 */
    private String fileMd5;

}
