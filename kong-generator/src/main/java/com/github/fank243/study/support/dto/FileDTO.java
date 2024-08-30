package com.github.fank243.kong.support.dto;

import com.github.fank243.kong.core.base.BaseDTO;
import com.github.fank243.kong.core.base.BaseEntity;
import com.github.fank243.kong.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 上传文件表 实体
 *
 * @author FanWeiJie
 * @since 2023-09-23 10:53:10
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class FileEntity extends BaseDTO  {

    /** 文件ID */
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
