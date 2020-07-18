package com.fank243.springboot.admin.service;

import com.fank243.springboot.admin.repository.SysRoleRepository;
import com.fank243.springboot.admin.service.base.BaseService;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.core.entity.SysPermission;
import com.fank243.springboot.core.entity.SysRole;
import com.fank243.springboot.core.enums.SysUserEventType;
import com.fank243.springboot.core.model.PageBean;
import com.fank243.springboot.core.model.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 角色
 * 
 * @author FanWeiJie
 * @date 2020-03-08 12:21:56
 */
@Service
public class SysRoleService extends BaseService<SysRole> {
    @Resource
    private SysRoleRepository repository;
    @Resource
    private SysPermissionService sysPermissionService;
    @Resource
    private SysUserEventService sysUserEventService;

    /**
     * 根据ID查找
     * 
     * @param roleId 角色ID
     * @return Role
     */
    public Optional<SysRole> findById(Long roleId) {
        return repository.findById(roleId);
    }

    /**
     * 添加或修改角色
     * 
     * @param sysUserId 管理员ID
     * @param sysRole {@link SysRole}
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo addOrModify(Long sysUserId, SysRole sysRole) {
        if (StringUtils.isBlank(sysRole.getName())) {
            return ResultInfo.fail("请填写角色名称");
        }
        if (StringUtils.isBlank(sysRole.getDescription())) {
            return ResultInfo.fail("请填写角色描述");
        }

        if (sysRole.getId() == null || sysRole.getId() <= 0) {
            return add(sysUserId, sysRole);
        }

        return modify(sysUserId, sysRole);
    }

    /**
     * 添加角色
     *
     * @param sysUserId 管理员ID
     * @param sysRole {@link SysRole}
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo add(Long sysUserId, SysRole sysRole) {

        sysRole.setAvailable(Boolean.TRUE);
        sysRole = repository.save(sysRole);
        if (sysRole.getId() == null || sysRole.getId() <= 0) {
            return ResultInfo.fail("添加角色失败");
        }

        // 添加事件
        String remark = String.format("添加角色[%s:%s]", sysRole.getId(), sysRole.getName());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.ROLE_MNG, remark);

        return ResultInfo.ok().message("添加角色成功");
    }

    /**
     * 修改角色
     *
     * @param sysUserId 管理员ID
     * @param sysRole {@link SysRole}
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo modify(Long sysUserId, SysRole sysRole) {

        Optional<SysRole> optional = repository.findById(sysRole.getId());
        if (optional.isEmpty()) {
            return ResultInfo.fail("添加角色失败");
        }

        SysRole sysRole2 = optional.get();
        sysRole2.setName(sysRole.getName());
        sysRole2.setDescription(sysRole.getDescription());

        sysRole = repository.save(sysRole2);
        if (sysRole.getId() == null || sysRole.getId() <= 0) {
            return ResultInfo.fail("添加角色失败");
        }

        // 添加事件
        String remark = String.format("修改角色[%s:%s]", sysRole.getId(), sysRole.getName());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.ROLE_MNG, remark);

        return ResultInfo.ok().message("修改角色成功");
    }

    /**
     * 删除角色
     * 
     * @param sysUserId 管理员ID
     * @param roleId 角色ID
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo deleteRole(Long sysUserId, Long roleId) {
        Optional<SysRole> optionalRole = repository.findById(roleId);
        if (optionalRole.isEmpty()) {
            return ResultInfo.fail("角色不存在");
        }
        SysRole sysRole = optionalRole.get();

        // 物理删除
        repository.deleteById(roleId);

        // 添加事件
        String remark = String.format("删除角色【%s：%s】", roleId, sysRole.getName());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.ROLE_MNG, remark);

        return ResultInfo.ok().message("删除角色成功");
    }

    public PageBean<SysRole> pageOfRole(PageInfo pageInfo, String keyword) {
        StringBuilder countSql = new StringBuilder();
        StringBuilder querySql = new StringBuilder();

        countSql.append("select count(*) from tb_sys_role where 1=1");
        querySql.append("select * from tb_sys_role where 1=1");

        Map<String, Object> conditionArgs = new HashMap<>(1);
        if (StringUtils.isNotBlank(keyword)) {
            countSql.append(" and (name like :keyword or description like :keyword)");
            querySql.append(" and (name like :keyword or description like :keyword)");
            conditionArgs.put("keyword", "%" + keyword + "%");
        }
        querySql.append(" ORDER BY gmt_modified desc,id desc");

        return pageOfBySql(pageInfo, countSql.toString(), querySql.toString(), conditionArgs);
    }

    public List<SysRole> findByAvailableIsTrue() {
        return repository.findByAvailableIsTrue();
    }

    public List<SysRole> findByIds(String roleIds) {
        String[] split = roleIds.split(",");
        List<Long> ids = new ArrayList<>(split.length);
        for (String str : split) {
            ids.add(Long.valueOf(str));
        }
        return repository.findByIdIn(ids);
    }

    public ResultInfo authorize(Long sysUserId, Long roleId, String permIds) {
        roleId = roleId == null || roleId <= 0 ? 0 : roleId;
        if (StringUtils.isBlank(permIds)) {
            return ResultInfo.fail("请选择权限后操作");
        }
        Optional<SysRole> optional = repository.findById(roleId);
        if (optional.isEmpty()) {
            return ResultInfo.fail("角色不存在");
        }
        SysRole sysRole = optional.get();

        List<SysPermission> sysPermissionList = sysPermissionService.findByIds(permIds);
        Set<SysPermission> sysPermissionSet = new HashSet<>(sysPermissionList);
        sysRole.setPermissions(sysPermissionSet);

        sysRole = repository.save(sysRole);
        if (sysRole.getId() == null || sysRole.getId() <= 0) {
            return ResultInfo.fail("授权失败");
        }

        // 添加事件
        String remark = String.format("授权角色权限【%s:%s】", roleId, sysRole.getName());
        sysUserEventService.addEvent(sysUserId, SysUserEventType.ROLE_MNG, remark);

        return ResultInfo.ok().message("授权成功");
    }

    public String findByUserId(Long sysUserId) {
        return repository.findByUserId(sysUserId);
    }

    @Transactional(rollbackFor = Exception.class)
    public ResultInfo modifyStatus(Long sysUserId, Long roleId) {
        Optional<SysRole> optional = findById(roleId);
        if (optional.isEmpty()) {
            return ResultInfo.fail("角色不存在");
        }
        SysRole sysRole = optional.get();

        boolean available = !sysRole.getAvailable();
        int rows = repository.updateAvailableById(roleId, available);
        if (rows <= 0) {
            return ResultInfo.fail("修改角色状态失败");
        }

        // 添加管理员事件
        sysUserEventService.addEvent(sysUserId, SysUserEventType.ROLE_MNG,
            String.format("修改角色状态成功[%s：%s]", sysRole.getId(), sysRole.getName()));

        return ResultInfo.ok().message("修改角色状态成功");
    }
}
