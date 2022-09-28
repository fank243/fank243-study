package com.fank243.study.file.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fank243.study.file.domain.entity.FileEntity;

/**
 * 文件表 数据访问层
 *
 * @author FanWeiJie
 * @since 2022-09-28 14:23:01
 */
@Repository
public interface IFileDao extends BaseMapper<FileEntity> {

    @Select("select * from tb_file where file_md5 = #{fileMd5} limit 1")
    FileEntity findByFileMd5(@Param("fileMd5") String fileMd5);

}