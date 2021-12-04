package com.fank243.study.system.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.api.system.dto.SysPermissionDTO;
import com.fank243.study.api.system.vo.SysPermissionVO;
import com.fank243.study.common.model.PageBean;
import com.fank243.study.core.exception.BizException;
import com.fank243.study.ds.utils.BeanUtils;
import com.fank243.study.system.dao.ISysPermissionDao;
import com.fank243.study.system.entity.SysPermissionEntity;

import cn.hutool.core.bean.BeanUtil;

/**
 * 系统权限表 服务类
 *
 * @author FanWeiJie
 * @since 2021-11-24
 */
@Service
public class SysPermissionService extends ServiceImpl<ISysPermissionDao, SysPermissionEntity> {

    @Resource
    private ISysPermissionDao sysPermissionDao;

    /**
     * 系统权限表_分页
     *
     * @param sysPermission 查询条件
     * @return 列表
     */
    public PageBean<SysPermissionVO> page(SysPermissionDTO sysPermission) {
        // TODO FanWeiJie 添加查询条件
        QueryWrapper<SysPermissionEntity> wrapper = new QueryWrapper<>();
        IPage<SysPermissionEntity> page =
            sysPermissionDao.selectPage(new Page<>(sysPermission.getCurrPage(), sysPermission.getPageSize()), wrapper);
        return BeanUtils.convert(page, SysPermissionVO.class);
    }

    /**
     * 系统权限表_新增
     *
     * @param sysPermission 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(SysPermissionDTO sysPermission) throws BizException {
        // TODO FanWeiJie 业务逻辑
        SysPermissionEntity sysPermissionEntity = BeanUtil.toBean(sysPermission, SysPermissionEntity.class);
        return save(sysPermissionEntity);
    }

    /**
     * 系统权限表_修改
     *
     * @param sysPermission 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean modify(SysPermissionDTO sysPermission) throws BizException {
        // TODO FanWeiJie 业务逻辑
        SysPermissionEntity sysPermissionEntity = BeanUtil.toBean(sysPermission, SysPermissionEntity.class);
        return sysPermissionDao.updateById(sysPermissionEntity) > 0;
    }
}
