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

package com.github.fank243.kong.system.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fank243.kong.core.annotation.RepeatSubmit;
import com.github.fank243.kong.core.base.BaseController;
import com.github.fank243.kong.core.constants.LogRecordType;
import com.github.fank243.kong.core.constants.ServerConstants;
import com.github.fank243.kong.core.domain.model.PageBean;
import com.github.fank243.kong.core.exception.BizException;
import com.github.fank243.kong.core.model.validation.ValidatorGroup;
import com.github.fank243.kong.system.domain.entity.SysRoleEntity;
import com.github.fank243.kong.system.domain.dto.SysRoleDTO;
import com.github.fank243.kong.system.domain.vo.SysRoleVO;
import com.github.fank243.kong.system.service.SysRoleService;
import com.github.fank243.kong.tool.result.ResultInfo;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;

/**
 * 系统角色表 控制器
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@RequestMapping(ServerConstants.BASE_URI_SYSTEM_ROLE)
@RestController
public class SysRoleController extends BaseController {

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 系统角色 > 根据权限ID获取
     *
     * @param roleId 角色ID
     * @return 角色信息
     */
    @GetMapping("/id/{roleId}")
    public ResultInfo<SysRoleVO> getById(@PathVariable String roleId) {
        SysRoleEntity sysRole = sysRoleService.findByRoleId(roleId);
        return ResultInfo.ok(BeanUtil.toBean(sysRole, SysRoleVO.class));
    }

    /**
     * 系统角色 > 分页
     *
     * @param sysRole 分页参数
     * @return 角色列表
     */
    @PostMapping("/page")
    public ResultInfo<PageBean<SysRoleVO>> page(@RequestBody SysRoleDTO sysRole) {
        return ResultInfo.ok(sysRoleService.page(sysRole));
    }

    /**
     * 系统角色 > 新增
     *
     * @param sysRole 请求参数
     * @return 操作结果
     */
    @RepeatSubmit
    @PostMapping("/role/add")
    public ResultInfo<?> add(@RequestBody @Validated({ValidatorGroup.Create.class}) SysRoleDTO sysRole)
        throws BizException {
        boolean isOk = sysRoleService.add(sysRole);
        return isOk ? ResultInfo.ok().message("添加成功") : ResultInfo.err500("添加失败");
    }

    /**
     * 系统角色 > 修改
     *
     * @param sysRole 请求参数
     * @return 操作结果
     */
    @RepeatSubmit
    @PostMapping("/modify")
    public ResultInfo<?> modify(@RequestBody @Validated({ValidatorGroup.Modify.class}) SysRoleDTO sysRole)
        throws BizException {
        boolean isOk = sysRoleService.modify(sysRole);
        return isOk ? ResultInfo.ok().message("修改成功") : ResultInfo.err500("修改失败");
    }

    /**
     * 系统角色 > 删除
     *
     * @param ids 角色ID数组
     * @return 操作结果
     */
    @LogRecord(type = LogRecordType.LOG_SYS_ROLE, subType = "delete", bizNo = "{{#roleId}}",
        success = "删除角色【{{#roleName}}】成功", successCondition = "{{#ret.success == true}}")
    @RepeatSubmit
    @DeleteMapping("/delete")
    public ResultInfo<?> delete(@RequestBody String[] ids) throws BizException {
        List<SysRoleVO> sysUserList = sysRoleService.findByRoleIn(List.of(ids));
        if (sysUserList.isEmpty()) {
            return ResultInfo.err404("角色不存在");
        }
        String roleName = sysUserList.stream().map(SysRoleVO::getRoleName).collect(Collectors.joining("、"));
        LogRecordContext.putVariable("roleId", String.join(",", ids));
        LogRecordContext.putVariable("roleName", roleName);
        LogRecordContext.putVariable("success", StrUtil.isNotBlank(roleName));

        sysRoleService.removeByIds(List.of(ids));
        return ResultInfo.ok();
    }

    /**
     * 系统角色 > 根据用户ID获取
     *
     * @param userId 用户ID
     * @return 用户角色列表
     */
    @GetMapping("/user/{userId}")
    public List<SysRoleVO> getByUserId(@PathVariable String userId) {
        return sysRoleService.findByUserId(userId);
    }
}
