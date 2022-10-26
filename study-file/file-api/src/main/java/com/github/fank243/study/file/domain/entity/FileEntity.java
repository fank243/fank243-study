package com.github.fank243.study.file.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.fank243.study.core.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件表
 *
 * @author FanWeiJie
 * @since 2022-09-28 14:23:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_file")
public class FileEntity extends BaseEntity {

    /** 文件ID */
    @TableId
    private String fileId;

    /** 文件名称(原始文件名称) */
    private String fileName;

    /** 文件类型 */
    private String fileType;

    /** 文件后缀 */
    private String fileSuffix;

    /** 文件相对路径 */
    private String filePath;

    /** 文件大小(kb) */
    private Long fileSize;

    /** 文件MD5值 */
    private String fileMd5;

}
