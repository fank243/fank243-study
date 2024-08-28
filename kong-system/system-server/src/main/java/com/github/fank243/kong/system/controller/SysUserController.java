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
import com.github.fank243.kong.system.domain.dto.SysUserDTO;
import com.github.fank243.kong.system.domain.vo.SysUserVO;
import com.github.fank243.kong.system.service.SysUserService;
import com.github.fank243.kong.tool.result.ResultInfo;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统管理员表 控制器
 * 
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Slf4j
@RequestMapping(ServerConstants.BASE_URI_SYSTEM_ADMIN)
@RestController
public class SysUserController extends BaseController {
    @Resource
    private SysUserService sysUserService;

    /**
     * 系统用户 > 根据用户ID获取
     *
     * @param userId 用户ID
     * @return 用户信息
     */
    @GetMapping("/id/{userId}")
    public ResultInfo<SysUserVO> getByUserId(@PathVariable String userId) {
        SysUserVO sysUser = sysUserService.findByUserId(userId);
        return ResultInfo.ok(sysUser);
    }

    /**
     * 系统用户 > 分页
     *
     * @param sysUser 分页参数
     * @return 用户列表
     */
    @PostMapping("/page")
    public ResultInfo<PageBean<SysUserVO>> page(@RequestBody SysUserDTO sysUser) {
        return ResultInfo.ok(sysUserService.page(sysUser));
    }

    /**
     * 系统用户 > 新增
     *
     * @param sysUser 请求参数
     * @return 操作结果
     */
    @RepeatSubmit
    @PostMapping("/add")
    public ResultInfo<?> add(@RequestBody @Validated({ValidatorGroup.Create.class}) SysUserDTO sysUser)
        throws BizException {
        boolean isOk = sysUserService.add(sysUser);
        return isOk ? ResultInfo.ok().message("添加成功") : ResultInfo.err500("添加失败");
    }

    /**
     * 系统用户 > 修改
     *
     * @param sysUser 请求参数
     * @return 操作结果
     */
    @RepeatSubmit
    @PostMapping(value = "/modify")
    public ResultInfo<?> modify(@RequestBody @Validated({ValidatorGroup.Modify.class}) SysUserDTO sysUser)
        throws BizException {
        boolean isOk = sysUserService.modify(sysUser);
        return isOk ? ResultInfo.ok().message("修改成功") : ResultInfo.err500("修改失败");
    }

    /**
     * 系统用户 > 删除
     *
     * @param ids 用户ID
     * @return 操作结果
     */
    @LogRecord(type = LogRecordType.LOG_SYS_USER, subType = "delete", bizNo = "{{#userId}}",
        success = "删除管理员【{{#username}}】成功", successCondition = "{{#_ret.success == true}}")
    @RepeatSubmit
    @DeleteMapping("/delete")
    public ResultInfo<?> delete(@RequestBody String[] ids) {
        List<SysUserVO> sysUserList = sysUserService.findAllByUserIdIn(List.of(ids));
        if (CollUtil.isEmpty(sysUserList)) {
            return ResultInfo.err404("用户不存在");
        }
        String username = sysUserList.stream().map(SysUserVO::getUsername).collect(Collectors.joining("、"));
        LogRecordContext.putVariable("roleId", String.join(",", ids));
        LogRecordContext.putVariable("success", StrUtil.isNotBlank(username));
        LogRecordContext.putVariable("username", username);

        sysUserService.removeByIds(List.of(ids));
        return ResultInfo.ok();
    }
}
