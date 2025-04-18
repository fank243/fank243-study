package com.github.fank243.study.system.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.Cached;
import com.github.fank243.study.core.constants.LogRecordType;
import com.github.fank243.study.core.constants.TimeConstants;
import com.github.fank243.study.core.domain.model.PageBean;
import com.github.fank243.study.core.exception.BizException;
import com.github.fank243.study.core.utils.BeanUtils;
import com.github.fank243.study.system.domain.SysPermEntity;
import com.github.fank243.study.system.domain.dto.SysPermDTO;
import com.github.fank243.study.system.domain.vo.SysPermVO;
import com.github.fank243.study.system.mapper.ISysPermMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.mzt.logapi.starter.annotation.LogRecord;

import cn.hutool.core.bean.BeanUtil;
import jakarta.annotation.Resource;

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
        QueryWrapper wrapper = new QueryWrapper();
        Page<SysPermEntity> sysPermEntityPage =
            sysPermDao.paginate(new Page<>(sysPermission.getCurrPage(), sysPermission.getPageSize()), wrapper);
        return BeanUtils.convert(sysPermEntityPage, SysPermVO.class);
    }

    /**
     * 系统权限表_新增
     *
     * @param sysPerm 请求参数
     * @return 操作结果
     */
    @LogRecord(type = LogRecordType.LOG_SYS_PERM, subType = "insert", bizNo = "{{#sysPerm.permId}}",
        success = "新增角色【{{#sysPerm.permName}}】", successCondition = "{{#sysPerm.permId!=null}}")
    @Transactional(rollbackFor = Exception.class)
    public boolean add(SysPermDTO sysPerm) throws BizException {
        SysPermEntity sysPermEntity = new SysPermEntity();
        sysPermEntity.setPermCode(sysPerm.getPermCode());
        QueryWrapper queryWrapper = QueryWrapper.create(sysPermEntity);
        sysPermEntity = sysPermDao.selectOneByQuery(queryWrapper);
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
    @LogRecord(type = LogRecordType.LOG_SYS_PERM, subType = "update", bizNo = "{{#sysPerm.permId}}",
        successCondition = "#_ret==true", success = "修改权限菜单：{_DIFF{#oldObject, #sysPerm}}")
    @Transactional(rollbackFor = Exception.class)
    public boolean modify(SysPermDTO sysPerm) throws BizException {
        SysPermEntity sysPermEntity = sysPermDao.selectOneById(sysPerm.getPermId());
        if (sysPermEntity == null) {
            throw new BizException("权限不存在");
        }

        sysPermEntity = BeanUtil.toBean(sysPerm, SysPermEntity.class);
        return saveOrUpdate(sysPermEntity);
    }

    @Cached(name = "system:perm:user:", key = "#userId", expire = TimeConstants.HOUR_1)
    @CacheRefresh(refresh = TimeConstants.MINUTE_5, stopRefreshAfterLastAccess = TimeConstants.HOUR_1)
    public List<SysPermVO> findByUserId(String userId) {
        return sysPermDao.findByUserId(userId);
    }

    @Cached(name = "system:perm:type:", expire = TimeConstants.DAY_1)
    @CacheRefresh(refresh = TimeConstants.MINUTE_5, stopRefreshAfterLastAccess = TimeConstants.HOUR_1)
    public List<SysPermVO> findByPermTypes(List<Integer> permTypes) {
        return sysPermDao.findByPermTypes(permTypes);
    }

    public SysPermVO findByPermId(String permId) {
        SysPermEntity sysPermEntity = getById(permId);
        return BeanUtil.copyProperties(sysPermEntity, SysPermVO.class);
    }
}
