package com.github.fank243.study.support.domain;

import com.github.fank243.study.core.base.BaseEntity;
import com.github.fank243.study.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;

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
@Table(value = "tb_file", onInsert = MybatisFlexTableListener.class, onUpdate = MybatisFlexTableListener.class)
public class FileEntity extends BaseEntity {

    /** 文件ID */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private String fileId;

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
