package com.fank243.study.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fank243.study.system.domain.entity.SysUserEntity;

/**
 * 系统管理员表 数据访问层
 *
 * @author FanWeiJie
 * @since 2021-11-24
 */
@Mapper
public interface ISysUserMapper extends BaseMapper<SysUserEntity> {

    /**
     * 根据用户名查找
     *
     * @param username 用户名
     * @return 用户实体
     */
    @Select("select * from tb_sys_user where username = #{username}")
    SysUserEntity findByUsername(String username);

    /**
     * 根据OpenID查找
     *
     * @param openId OpenID
     * @return 用户实体
     */
    @Select("select * from tb_sys_user where open_id = #{openId}")
    SysUserEntity findByOpenId(String openId);
}
