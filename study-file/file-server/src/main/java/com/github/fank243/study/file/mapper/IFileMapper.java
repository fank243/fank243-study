package com.github.fank243.study.file.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.fank243.study.file.domain.entity.FileEntity;

/**
 * 文件表 数据访问层
 *
 * @author FanWeiJie
 * @since 2022-09-28 14:23:01
 */
@Mapper
public interface IFileMapper extends BaseMapper<FileEntity> {

    /**
     * 根据文件MD5值查询
     * 
     * @param fileMd5 文件MD5值
     * @return 文件
     */

    @Select("select * from tb_file where file_md5 = #{fileMd5} limit 1")
    FileEntity findByFileMd5(@Param("fileMd5") String fileMd5);

}