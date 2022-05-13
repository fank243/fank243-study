package com.fank243.study.system.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.api.system.dto.SysPermDTO;
import com.fank243.study.api.system.vo.SysPermVO;
import com.fank243.study.common.model.PageBean;
import com.fank243.study.core.exception.BizException;
import com.fank243.study.ds.utils.BeanUtils;
import com.fank243.study.system.dao.ISysPermDao;
import com.fank243.study.system.entity.SysPermEntity;

import cn.hutool.core.bean.BeanUtil;

/**
 * 系统权限表 服务类
 *
 * @author FanWeiJie
 * @since 2021-11-24
 */
@Service
public class SysPermService extends ServiceImpl<ISysPermDao, SysPermEntity> {

    @Resource
    private ISysPermDao sysPermDao;

    /**
     * 系统权限表_分页
     *
     * @param sysPermission 查询条件
     * @return 列表
     */
    public PageBean<SysPermVO> page(SysPermDTO sysPermission) {
        // TODO FanWeiJie 添加查询条件
        QueryWrapper<SysPermEntity> wrapper = new QueryWrapper<>();
        IPage<SysPermEntity> page =
            sysPermDao.selectPage(new Page<>(sysPermission.getCurrPage(), sysPermission.getPageSize()), wrapper);
        return BeanUtils.convert(page, SysPermVO.class);
    }

    /**
     * 系统权限表_新增
     *
     * @param sysPermission 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(SysPermDTO sysPerm) throws BizException {
        QueryWrapper<SysPermEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("permCode", sysPerm.getPermCode());
        SysPermEntity sysPermEntity = sysPermDao.selectOne(wrapper);
        if (sysPermEntity != null) {
            throw new BizException("权限代码已存在");
        }

        sysPermEntity = BeanUtil.toBean(sysPerm, SysPermEntity.class);
        return save(sysPermEntity);
    }

    /**
     * 系统权限表_修改
     *
     * @param sysPermission 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean modify(SysPermDTO sysPerm) throws BizException {
        SysPermEntity sysPermEntity = sysPermDao.selectById(sysPerm.getPermId());
        if (sysPermEntity != null) {
            throw new BizException("权限不存在");
        }

        sysPermEntity = BeanUtil.toBean(sysPerm, SysPermEntity.class);
        return sysPermDao.updateById(sysPermEntity) > 0;
    }
}
