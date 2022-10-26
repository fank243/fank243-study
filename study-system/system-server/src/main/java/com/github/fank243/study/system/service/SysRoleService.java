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
import com.github.fank243.study.system.domain.dto.SysRoleDTO;
import com.github.fank243.study.system.domain.entity.SysRoleEntity;
import com.github.fank243.study.system.domain.vo.SysRoleVO;
import com.github.fank243.study.system.mapper.ISysRoleMapper;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;

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
public class SysRoleService extends ServiceImpl<ISysRoleMapper, SysRoleEntity> {

    @Resource
    private ISysRoleMapper sysRoleDao;

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
    @LogRecord(type = LogRecordType.SYS_ROLE, bizNo = "{{#sysRole.roleId}}", success = "新增角色【{{#sysRole.roleName}}】",
            successCondition = "{{#sysRole.roleId!=null}}")
    @Transactional(rollbackFor = Exception.class)
    public boolean add(SysRoleDTO sysRole) throws BizException {
        SysRoleEntity sysRoleEntity = sysRoleDao
            .selectOne(Wrappers.<SysRoleEntity>lambdaQuery().eq(SysRoleEntity::getRoleCode, sysRole.getRoleCode()));
        if (sysRoleEntity != null) {
            throw new BizException("角色代码已存在");
        }
        sysRoleEntity = BeanUtil.toBean(sysRole, SysRoleEntity.class);

        if (!save(sysRoleEntity)) {
            return Boolean.FALSE;
        }

        sysRole.setRoleId(sysRoleEntity.getRoleId());
        return Boolean.TRUE;
    }

    /**
     * 系统角色表_修改
     *
     * @param sysRole 请求参数
     * @return 操作结果
     */
    @LogRecord(type = LogRecordType.SYS_PERM, bizNo = "{{#sysRole.roleId}}", successCondition = "#_ret==true",
            success = "修改角色信息：{_DIFF{#oldObject, #sysRole}}")
    @Transactional(rollbackFor = Exception.class)
    public boolean modify(SysRoleDTO sysRole) throws BizException {
        SysRoleEntity sysRoleEntity = sysRoleDao.selectById(sysRole.getRoleId());
        if (sysRoleEntity == null) {
            throw new BizException("角色不存在");
        }
        LogRecordContext.putVariable("oldObject", BeanUtil.copyProperties(sysRoleEntity, SysRoleDTO.class));

        sysRoleEntity = BeanUtil.toBean(sysRole, SysRoleEntity.class);
        return sysRoleDao.updateById(sysRoleEntity) > 0;
    }

    @Cached(name = "system:role:user:", key = "#userId", expire = TimeConstant.HOUR_1)
    @CacheRefresh(refresh = TimeConstant.MINUTE_5, stopRefreshAfterLastAccess = TimeConstant.HOUR_1)
    public List<SysRoleVO> findByUserId(String userId) {
        return sysRoleDao.findByUserId(userId);
    }

    public List<SysRoleVO> findByRoleIn(List<String> ids) {
        List<SysRoleEntity> sysRoleEntities =
            sysRoleDao.selectList(new LambdaQueryWrapper<SysRoleEntity>().in(SysRoleEntity::getRoleId, ids));
        return BeanUtil.copyToList(sysRoleEntities, SysRoleVO.class);
    }
}
