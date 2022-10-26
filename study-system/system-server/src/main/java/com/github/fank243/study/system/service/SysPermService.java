package com.github.fank243.study.system.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.fank243.study.core.constants.TimeConstant;
import com.github.fank243.study.core.domain.model.PageBean;
import com.github.fank243.study.core.exception.BizException;
import com.github.fank243.study.core.utils.BeanUtils;
import com.github.fank243.study.log.constants.LogRecordType;
import com.github.fank243.study.system.domain.dto.SysPermDTO;
import com.github.fank243.study.system.domain.entity.SysPermEntity;
import com.github.fank243.study.system.domain.vo.SysPermVO;
import com.github.fank243.study.system.mapper.ISysPermMapper;
import com.mzt.logapi.starter.annotation.LogRecord;

import cn.hutool.core.bean.BeanUtil;

/**
 * 系统权限表 服务类
 *
 * @author FanWeiJie
 * @since 2021-11-24
 */
@Service
public class SysPermService extends ServiceImpl<ISysPermMapper, SysPermEntity> {

    @Resource
    private ISysPermMapper sysPermDao;

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
    @LogRecord(type = LogRecordType.SYS_PERM, bizNo = "{{#sysPerm.permId}}", success = "新增角色【{{#sysPerm.permName}}】",
        successCondition = "{{#sysPerm.permId!=null}}")
    @Transactional(rollbackFor = Exception.class)
    public boolean add(SysPermDTO sysPerm) throws BizException {
        SysPermEntity sysPermEntity = sysPermDao
            .selectOne(Wrappers.<SysPermEntity>lambdaQuery().eq(SysPermEntity::getPermCode, sysPerm.getPermCode()));
        if (sysPermEntity != null) {
            throw new BizException("权限代码已存在");
        }

        sysPermEntity = BeanUtil.toBean(sysPerm, SysPermEntity.class);
        if (!save(sysPermEntity)) {
            return Boolean.FALSE;
        }

        sysPerm.setPermId(sysPermEntity.getPermId());
        return Boolean.TRUE;
    }

    /**
     * 系统权限表_修改
     *
     * @param sysPerm 请求参数
     * @return 操作结果
     */
    @LogRecord(type = LogRecordType.SYS_PERM, bizNo = "{{#sysPerm.permId}}", successCondition = "#_ret==true",
        success = "修改权限菜单：{_DIFF{#oldObject, #sysPerm}}")
    @Transactional(rollbackFor = Exception.class)
    public boolean modify(SysPermDTO sysPerm) throws BizException {
        SysPermEntity sysPermEntity = sysPermDao.selectById(sysPerm.getPermId());
        if (sysPermEntity == null) {
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

    public List<SysPermVO> findByPermIdIn(List<String> ids) {
        List<SysPermEntity> sysPermEntities =
            sysPermDao.selectList(new LambdaQueryWrapper<SysPermEntity>().in(SysPermEntity::getPermId, ids));
        return BeanUtil.copyToList(sysPermEntities, SysPermVO.class);
    }

    public SysPermVO findByPermId(String permId) {
        SysPermEntity sysPermEntity = getById(permId);
        return BeanUtil.copyProperties(sysPermEntity, SysPermVO.class);
    }
}
