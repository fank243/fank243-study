package com.fank243.study.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fank243.study.api.system.dto.SysUserDTO;
import com.fank243.study.api.system.vo.SysUserVO;
import com.fank243.study.core.base.BaseService;
import com.fank243.study.core.model.PageBean;
import com.fank243.study.server.system.domain.entity.SysUserEntity;

import java.util.List;

/**
 * 系统管理员
 * 
 * @author FanWeiJie
 * @since 2021-06-14 00:01:40
 */
public interface ISysUserService extends IService<SysUserEntity>, BaseService {

    /**
     * 保存对象
     * 
     * @param sysUser 对象实体
     * @return 结果
     */
    boolean save(SysUserDTO sysUser);

    /**
     * 修改对象
     *
     * @param sysUser 对象实体
     * @return 结果
     */
    boolean updateById(SysUserDTO sysUser);

    /**
     * 根据ID查找
     * 
     * @param id ID
     * @return 对象实体
     */
    SysUserVO findById(String id);

    /**
     * 条件查找
     *
     * @param sysUser 对象实体
     * @return 对象实体
     */
    SysUserVO findByCondition(SysUserDTO sysUser);

    /**
     * 条件查找
     *
     * @param sysUser 对象实体
     * @return 对象实体集合
     */
    List<SysUserVO> findListByCondition(SysUserDTO sysUser);

    /**
     * 分页查找用户实体
     * 
     * @param sysUser 参数实体
     * @return 用户对象
     */
    PageBean<SysUserVO> pageOfUser(SysUserDTO sysUser);
}
