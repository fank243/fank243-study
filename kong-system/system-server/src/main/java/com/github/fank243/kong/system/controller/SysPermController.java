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
import com.github.fank243.kong.system.domain.entity.SysPermEntity;
import com.github.fank243.kong.system.domain.dto.SysPermDTO;
import com.github.fank243.kong.system.domain.enums.PermTypeEnum;
import com.github.fank243.kong.system.domain.vo.SysPermVO;
import com.github.fank243.kong.system.service.SysPermService;
import com.github.fank243.kong.tool.result.ResultInfo;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;

/**
 * 系统权限表 控制器
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@RequestMapping(ServerConstants.BASE_URI_SYSTEM_PERM)
@RestController
public class SysPermController extends BaseController {

    @Resource
    private SysPermService sysPermService;

    /**
     * 菜单权限 > 根据权限ID获取
     *
     * @param permId 权限ID
     * @return 权限信息
     */
    @GetMapping("/id/{permId}")
    public ResultInfo<SysPermVO> getById(@PathVariable("permId") String permId) {
        SysPermVO sysPerm = sysPermService.findByPermId(permId);
        return ResultInfo.ok(sysPerm);
    }

    /**
     * 菜单权限 > 分页
     *
     * @param sysPerm 分页参数
     * @return 权限列表
     */
    @PostMapping("/page")
    public ResultInfo<PageBean<SysPermVO>> page(@RequestBody SysPermDTO sysPerm) {
        return ResultInfo.ok(sysPermService.page(sysPerm));
    }

    /**
     * 菜单权限 > 新增
     *
     * @param sysPerm 请求参数
     * @return 操作结果
     */
    @RepeatSubmit
    @PostMapping("/add")
    public ResultInfo<?> add(@RequestBody @Validated({ValidatorGroup.Create.class}) SysPermDTO sysPerm)
        throws BizException {
        boolean isOk = sysPermService.add(sysPerm);
        return isOk ? ResultInfo.ok().message("添加成功") : ResultInfo.err500("添加失败");
    }

    /**
     * 菜单权限 > 修改
     *
     * @param sysPerm 请求参数
     * @return 操作结果
     */
    @RepeatSubmit
    @PostMapping("/modify")
    public ResultInfo<?> modify(@RequestBody @Validated({ValidatorGroup.Modify.class}) SysPermDTO sysPerm)
        throws BizException {
        boolean isOk = sysPermService.modify(sysPerm);
        return isOk ? ResultInfo.ok().message("修改成功") : ResultInfo.err500("修改失败");
    }

    /**
     * 菜单权限 > 删除
     *
     * @param ids 权限ID
     * @return 操作结果
     */
    @LogRecord(type = LogRecordType.LOG_SYS_PERM, subType = "delete", bizNo = "{{#permId}}",
        success = "删除管理员【{{#permName}}】成功", successCondition = "{{#_ret.success == true}}")
    @RepeatSubmit
    @DeleteMapping("/delete")
    public ResultInfo<?> delete(@RequestBody String[] ids) throws BizException {
        List<SysPermEntity> sysPermList = sysPermService.findAllByPermIdIn(List.of(ids));
        if (CollUtil.isEmpty(sysPermList)) {
            return ResultInfo.err404("权限菜单不存在");
        }
        String permName = sysPermList.stream().map(SysPermEntity::getPermName).collect(Collectors.joining("、"));
        LogRecordContext.putVariable("roleId", String.join(",", ids));
        LogRecordContext.putVariable("success", StrUtil.isNotBlank(permName));
        LogRecordContext.putVariable("permName", permName);

        sysPermService.removeByIds(List.of(ids));
        return ResultInfo.ok();
    }

    /**
     * 菜单权限 > 根据权限类型获取
     *
     * @param perms 权限类型列表
     * @return 操作结果
     */
    @PostMapping("/types")
    public List<SysPermVO> getByPermTypes(@RequestBody List<PermTypeEnum> perms) {
        return sysPermService.findByPermTypes(perms);
    }

    /**
     * 菜单权限 > 根据用户ID获取
     *
     * @param userId 用户ID
     * @return 用户权限列表
     */
    @GetMapping("/user/{userId}")
    public List<SysPermVO> getByUserId(@PathVariable String userId) {
        return sysPermService.findByUserId(userId);
    }
}
