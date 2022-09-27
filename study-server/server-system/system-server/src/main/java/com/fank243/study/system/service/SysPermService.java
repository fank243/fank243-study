package com.fank243.study.system.service;

import javax.annotation.Resource;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.Cached;
import com.fank243.study.common.constants.TimeConstant;
import com.fank243.study.system.dao.ISysPermDao;
import com.fank243.study.system.domain.entity.SysPermEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.api.system.domain.dto.SysPermDTO;
import com.fank243.study.api.system.domain.vo.SysPermVO;
import com.fank243.study.common.domain.model.PageBean;
import com.fank243.study.common.utils.BeanUtils;
import com.fank243.study.core.web.exception.BizException;

import cn.hutool.core.bean.BeanUtil;

import java.util.List;

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
     * @param sysPerm 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean add(SysPermDTO sysPerm) throws BizException {
        SysPermEntity sysPermEntity = sysPermDao
            .selectOne(Wrappers.<SysPermEntity>lambdaQuery().eq(SysPermEntity::getPermCode, sysPerm.getPermCode()));
        if (sysPermEntity != null) {
            throw new BizException("权限代码已存在");
        }

        sysPermEntity = BeanUtil.toBean(sysPerm, SysPermEntity.class);
        return save(sysPermEntity);
    }

    /**
     * 系统权限表_修改
     *
     * @param sysPerm 请求参数
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


    @Cached(name = "system:perm:user:", key = "#userId", expire = TimeConstant.HOUR_1)
    @CacheRefresh(refresh = TimeConstant.MINUTE_5, stopRefreshAfterLastAccess = TimeConstant.HOUR_1)
    public List<SysPermVO> findByUserId(String userId) {
        return sysPermDao.findByUserId(userId);
    }

    @Cached(name = "system:perm:type:", expire = TimeConstant.DAY_1)
    @CacheRefresh(refresh = TimeConstant.HOUR_1, stopRefreshAfterLastAccess = TimeConstant.DAY_1)
    public List<SysPermVO> findByPermTypes(List<Integer> permTypes) {
        return sysPermDao.findByPermTypes(permTypes);
    }
}