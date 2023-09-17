package com.github.fank243.study.support.entity;

import com.github.fank243.study.core.base.BaseEntity;
import com.github.fank243.study.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 上传文件表 实体类
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:20
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Table(value = "tb_file", onInsert = MybatisFlexTableListener.class, onUpdate = MybatisFlexTableListener.class)
public class FileEntity extends BaseEntity implements Serializable {

    /** 文件ID */
    @Id(keyType = KeyType.Generator, value = "snowFlakeId")
    private Long fileId;

    /** 文件名称 */
    private String fileName;

    /** MineType */
    private String mineType;

    /** 文件类型 */
    private String fileType;

    /** 文件前缀 */
    private String filePrefix;

    /** 文件相对路径 */
    private String filePath;

    /** 文件大小(byte) */
    private Long fileSize;

    /** 文件MD5签名 */
    private String fileMd5;

}
