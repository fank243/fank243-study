/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.github.fank243.kong.system.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.Cached;
import com.github.fank243.kong.core.constants.LogRecordType;
import com.github.fank243.kong.core.constants.TimeConstants;
import com.github.fank243.kong.core.domain.model.PageBean;
import com.github.fank243.kong.core.exception.BizException;
import com.github.fank243.kong.system.domain.entity.SysPermEntity;
import com.github.fank243.kong.system.domain.dto.SysPermDTO;
import com.github.fank243.kong.system.domain.enums.PermTypeEnum;
import com.github.fank243.kong.system.domain.enums.StatusEnum;
import com.github.fank243.kong.system.domain.vo.SysPermVO;
import com.github.fank243.kong.system.repository.ISysPermRepository;
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
public class SysPermService {

    @Resource
    private ISysPermRepository sysPermRepository;

    /**
     * 系统权限表_分页
     *
     * @param sysPermission 查询条件
     * @return 列表
     */
    public PageBean<SysPermVO> page(SysPermDTO sysPermission) {
        // TODO FanWeiJie 添加查询条件
        // Page<SysPermEntity> sysPermEntityPage =
        // sysPermRepository.paginate(new Page<>(sysPermission.getCurrPage(), sysPermission.getPageSize()), wrapper);
        // return BeanUtils.convert(sysPermEntityPage, SysPermVO.class);
        return null;
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
        SysPermEntity sysPermEntity = sysPermRepository.findByPermCode(sysPerm.getPermCode());
        if (sysPermEntity != null) {
            throw new BizException("权限代码已存在");
        }

        sysPermEntity = BeanUtil.toBean(sysPerm, SysPermEntity.class);
        sysPermRepository.save(sysPermEntity);

        sysPerm.setPermId(sysPermEntity.getId());
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
        SysPermEntity sysPermEntity = sysPermRepository.findById(sysPerm.getPermId()).orElse(null);
        if (sysPermEntity == null) {
            throw new BizException("权限不存在");
        }

        sysPermEntity = BeanUtil.toBean(sysPerm, SysPermEntity.class);
        sysPermRepository.save(sysPermEntity);
        return true;
    }

    @Cached(name = "system:perm:user:", key = "#userId", expire = TimeConstants.HOUR_1)
    @CacheRefresh(refresh = TimeConstants.MINUTE_5, stopRefreshAfterLastAccess = TimeConstants.HOUR_1)
    public List<SysPermVO> findByUserId(String userId) {
        List<SysPermEntity> sysPermList = sysPermRepository.findByUserId(userId);
        return BeanUtil.copyToList(sysPermList, SysPermVO.class);
    }

    @Cached(name = "system:perm:type:", expire = TimeConstants.DAY_1)
    @CacheRefresh(refresh = TimeConstants.MINUTE_5, stopRefreshAfterLastAccess = TimeConstants.HOUR_1)
    public List<SysPermVO> findByPermTypes(List<PermTypeEnum> permTypes) {
        List<SysPermEntity> sysPersmList =
            sysPermRepository.findAllByPermTypeInAndStatusEquals(permTypes, StatusEnum.NORMAL);
        return BeanUtil.copyToList(sysPersmList, SysPermVO.class);
    }

    public SysPermVO findByPermId(String permId) {
        SysPermEntity sysPermEntity = sysPermRepository.findById(Long.valueOf(permId)).orElse(null);
        return BeanUtil.copyProperties(sysPermEntity, SysPermVO.class);
    }

    public List<SysPermEntity> findAllByPermIdIn(List<String> ids) {
        return sysPermRepository.findAllByIdIn(ids.stream().map(Long::valueOf).toList());
    }

    public void removeByIds(List<String> ids) {
        sysPermRepository.deleteAllByIdInBatch(ids.stream().map(Long::valueOf).toList());
    }
}
