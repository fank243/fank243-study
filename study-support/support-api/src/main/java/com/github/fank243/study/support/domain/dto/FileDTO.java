package com.github.fank243.study.support.domain.dto;

import com.github.fank243.study.core.constants.Constants;

import cn.hutool.core.util.StrUtil;
import lombok.Builder;
import lombok.Data;

/**
 * 文件表
 *
 * @author FanWeiJie
 * @since 2022-09-28 14:23:01
 */
@Data
@Builder
public class FileDTO {

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

    public String getFilePrefix() {
        if (StrUtil.equals(this.filePrefix, Constants.FILE_PREFIX_USER)) {
            return filePrefix + "/" + loginId;
        }
        return filePrefix;
    }
}
