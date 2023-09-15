package com.github.fank243.study.support.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.fank243.study.support.domain.entity.FileEntity;
import com.mybatisflex.core.BaseMapper;

/**
 * 文件表 数据访问层
 *
 * @author FanWeiJie
 * @since 2022-09-28 14:23:01
 */
@Mapper
public interface IFileMapper extends BaseMapper<FileEntity> {

    /**
     * 根据文件MD5查找
     *
     * @param fileMd5 文件MD5值
     * @return 文件
     */
    @Select("select * from tb_file where file_md5 = #{fileMd5} limit 1")
    FileEntity findByFileMd5(@Param("fileMd5") String fileMd5);

    /**
     * 条件查询
     *
     * @param fileName 文件名称
     * @param filePrefix 文件前缀
     * @param fileMd5 文件MD5值
     * @return 文件
     */
    @Select("select * from tb_file where file_name = #{fileName} and file_prefix = #{filePrefix} and file_md5 = #{fileMd5} limit 1")
    FileEntity findByFileNameAndFilePrefixAndFileMd5(@Param("fileName") String fileName,
        @Param("filePrefix") String filePrefix, @Param("fileMd5") String fileMd5);

}