package com.fank243.study.system.controller;

import java.util.List;

import javax.annotation.Resource;

import com.fank243.study.api.domain.PageBean;
import com.fank243.study.core.annotation.RepeatSubmit;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fank243.study.api.constants.ValidatorGroup;
import com.fank243.study.api.system.api.ISysRoleApi;
import com.fank243.study.api.system.dto.SysRoleDTO;
import com.fank243.study.api.system.vo.SysRoleVO;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.base.BaseController;
import com.fank243.study.core.exception.BizException;
import com.fank243.study.system.entity.SysRoleEntity;
import com.fank243.study.system.service.SysRoleService;

import cn.hutool.core.bean.BeanUtil;

/**
 * 系统角色表 控制器
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@RestController
public class SysRoleController extends BaseController implements ISysRoleApi {

    @Resource
    private SysRoleService sysRoleService;

    @Override
    public ResultInfo<SysRoleVO> getById(String id) {
        SysRoleEntity sysRole = sysRoleService.getById(id);
        return ResultInfo.ok(BeanUtil.toBean(sysRole, SysRoleVO.class));
    }

    @Override
    public ResultInfo<PageBean<SysRoleVO>> page(@RequestBody SysRoleDTO sysRole) {
        return ResultInfo.ok(sysRoleService.page(sysRole));
    }

    @RepeatSubmit
    @Override
    public ResultInfo<?> add(@RequestBody @Validated({ValidatorGroup.Create.class}) SysRoleDTO sysRole)
        throws BizException {
        boolean isOk = sysRoleService.add(sysRole);
        return isOk ? ResultInfo.ok().message("添加成功") : ResultInfo.fail("添加失败");
    }

    @RepeatSubmit
    @Override
    public ResultInfo<?> modify(@RequestBody @Validated({ValidatorGroup.Modify.class}) SysRoleDTO sysRole)
        throws BizException {
        boolean isOk = sysRoleService.add(sysRole);
        return isOk ? ResultInfo.ok().message("修改成功") : ResultInfo.fail("修改失败");
    }

    @RepeatSubmit
    @Override
    public ResultInfo<?> delete(@RequestBody String[] ids) throws BizException {
        boolean isOk = sysRoleService.removeByIds(List.of(ids));
        return isOk ? ResultInfo.ok().message("删除成功") : ResultInfo.fail("删除失败");
    }
}
