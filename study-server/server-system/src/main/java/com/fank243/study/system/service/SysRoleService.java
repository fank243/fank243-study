package com.fank243.study.system.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.api.system.dto.SysRoleDTO;
import com.fank243.study.api.system.vo.SysRoleVO;
import com.fank243.study.common.model.PageBean;
import com.fank243.study.core.exception.BizException;
import com.fank243.study.ds.utils.BeanUtils;
import com.fank243.study.system.dao.ISysRoleDao;
import com.fank243.study.system.entity.SysRoleEntity;

import cn.hutool.core.bean.BeanUtil;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author FanWeiJie
 * @since 2021-11-24
 */
@Service
public class SysRoleService extends ServiceImpl<ISysRoleDao, SysRoleEntity> {

    @Resource
    private ISysRoleDao sysRoleDao;

    /**
     * 系统角色表_分页
     *
     * @param sysRole 查询条件
     * @return 列表
     */
    public PageBean<SysRoleVO> page(SysRoleDTO sysRole) {
        // TODO FanWeiJie 添加查询条件
        QueryWrapper<SysRoleEntity> wrapper = new QueryWrapper<>();
        IPage<SysRoleEntity> page =
            sysRoleDao.selectPage(new Page<>(sysRole.getCurrPage(), sysRole.getPageSize()), wrapper);
        return BeanUtils.convert(page, SysRoleVO.class);
    }

    /**
     * 系统角色表_新增
     *
     * @param sysRole 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(SysRoleDTO sysRole) throws BizException {
        // TODO FanWeiJie 业务逻辑
        SysRoleEntity sysRoleEntity = BeanUtil.toBean(sysRole, SysRoleEntity.class);
        return save(sysRoleEntity);
    }

    /**
     * 系统角色表_修改
     *
     * @param sysRole 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean modify(SysRoleDTO sysRole) throws BizException {
        // TODO FanWeiJie 业务逻辑
        SysRoleEntity sysRoleEntity = BeanUtil.toBean(sysRole, SysRoleEntity.class);
        return sysRoleDao.updateById(sysRoleEntity) > 0;
    }
}
