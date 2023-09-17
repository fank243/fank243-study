package com.github.fank243.study.support.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.study.support.entity.FileEntity;

/**
 * 上传文件表 映射层
 *
 * @author FanWeiJie
 * @since 2023-09-17 17:33:20
 */
public interface IFileMapper extends BaseMapper<FileEntity> {

     /**
     * 根据主键ID查询
     *
     * @param fileId 主键
     * @return 上传文件表
     */
     FileEntity findByFileId(Long fileId);

     /**
     * 多条件查询
     *
     * @param file 条件参数
     * @return 上传文件表
     */
     FileEntity findByCondition(@Param("file") FileEntity file);

     /**
     * 多条件查询
     *
     * @param file 条件参数
     * @return 上传文件表
     */
     List<FileEntity> findListByCondition(@Param("file") FileEntity file);
}
