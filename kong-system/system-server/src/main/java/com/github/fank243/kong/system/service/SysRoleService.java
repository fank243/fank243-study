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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.Cached;
import com.github.fank243.kong.core.constants.LogRecordType;
import com.github.fank243.kong.core.constants.TimeConstants;
import com.github.fank243.kong.core.domain.model.PageBean;
import com.github.fank243.kong.core.exception.BizException;
import com.github.fank243.kong.system.domain.entity.SysRoleEntity;
import com.github.fank243.kong.system.domain.dto.SysRoleDTO;
import com.github.fank243.kong.system.domain.vo.SysRoleVO;
import com.github.fank243.kong.system.domain.vo.SysUserVO;
import com.github.fank243.kong.system.repository.ISysRoleRepository;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;

import cn.hutool.core.bean.BeanUtil;
import jakarta.annotation.Resource;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author FanWeiJie
 * @since 2021-11-24
 */
@Service
public class SysRoleService {

    @Resource
    private ISysRoleRepository roleRepository;
    @Autowired
    private SysUserService sysUserService;

    public SysRoleEntity findByRoleId(String roleId) {
        return roleRepository.findById(Long.valueOf(roleId)).orElse(null);
    }

    /**
     * 系统角色表_分页
     *
     * @param sysRole 查询条件
     * @return 列表
     */
    public PageBean<SysRoleVO> page(SysRoleDTO sysRole) {
        // TODO FanWeiJie 添加查询条件
        // Page<SysRoleEntity> sysRoleEntityPage =
        // roleRepository.paginate(new Page<>(sysRole.getCurrPage(), sysRole.getPageSize()), wrapper);
        // return BeanUtils.convert(sysRoleEntityPage, SysRoleVO.class);
        return null;
    }

    /**
     * 系统角色表_新增
     *
     * @param sysRole 请求参数
     * @return 操作结果
     */
    @LogRecord(type = LogRecordType.LOG_SYS_ROLE, subType = "insert", bizNo = "{{#sysRole.roleId}}",
        success = "新增角色【{{#sysRole.roleName}}】", successCondition = "{{#sysRole.roleId!=null}}")
    @Transactional(rollbackFor = Exception.class)
    public boolean add(SysRoleDTO sysRole) throws BizException {
        SysRoleEntity sysRoleEntity = roleRepository.findByRoleCode(sysRole.getRoleCode());
        if (sysRoleEntity != null) {
            throw new BizException("角色代码已存在");
        }
        sysRoleEntity = BeanUtil.toBean(sysRole, SysRoleEntity.class);

        roleRepository.save(sysRoleEntity);

        sysRole.setRoleId(sysRoleEntity.getId());
        return Boolean.TRUE;
    }

    /**
     * 系统角色表_修改
     *
     * @param sysRole 请求参数
     * @return 操作结果
     */
    @LogRecord(type = LogRecordType.LOG_SYS_PERM, subType = "update", bizNo = "{{#sysRole.roleId}}",
        successCondition = "#_ret==true", success = "修改角色信息：{_DIFF{#oldObject, #sysRole}}")
    @Transactional(rollbackFor = Exception.class)
    public boolean modify(SysRoleDTO sysRole) throws BizException {
        SysRoleEntity sysRoleEntity = roleRepository.findById(sysRole.getRoleId()).orElse(null);
        if (sysRoleEntity == null) {
            throw new BizException("角色不存在");
        }
        LogRecordContext.putVariable("oldObject", BeanUtil.copyProperties(sysRoleEntity, SysRoleDTO.class));

        sysRoleEntity = BeanUtil.toBean(sysRole, SysRoleEntity.class);

        roleRepository.save(sysRoleEntity);
        return true;
    }

    @Cached(name = "system:role:user:", key = "#userId", expire = TimeConstants.HOUR_1)
    @CacheRefresh(refresh = TimeConstants.MINUTE_5, stopRefreshAfterLastAccess = TimeConstants.HOUR_1)
    public List<SysRoleVO> findByUserId(String userId) {
        SysUserVO sysUserVO = sysUserService.findByUserId(userId);
        return sysUserVO.getRoles();
    }

    public List<SysRoleVO> findByRoleIn(List<String> roleIdList) {
        List<SysRoleEntity> sysRoleEntities =
            roleRepository.findAllByIdIn(roleIdList.stream().map(Long::valueOf).toList());
        return BeanUtil.copyToList(sysRoleEntities, SysRoleVO.class);
    }

    public void removeByIds(List<String> ids) {
        roleRepository.deleteAllByIdInBatch(ids.stream().map(Long::valueOf).toList());
    }
}
