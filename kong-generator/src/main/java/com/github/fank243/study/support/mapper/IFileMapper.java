package com.github.fank243.kong.support.mapper;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.lang.Long;
import com.mybatisflex.core.BaseMapper;
import com.github.fank243.kong.support.dto.FileDTO;

/**
* 上传文件表 映射层
*
* @author FanWeiJie
* @since 2023-09-23 10:53:10
*/
public interface IFileMapper extends BaseMapper<FileEntity> {

    /**
    * 根据主键ID查询
    *
    * @param fileId 主键
    * @return 上传文件表
    */
    FileDTO findByFileId(Long fileId);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 上传文件表
    */
    FileDTO findByCondition(@Param("params") FileDTO params);

    /**
    * 多条件查询
    *
    * @param params 条件参数
    * @return 上传文件表
    */
    List<FileDTO> findListByCondition(@Param("params") FileDTO params);
}
