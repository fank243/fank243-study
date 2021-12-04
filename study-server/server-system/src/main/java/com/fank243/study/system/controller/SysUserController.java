package com.fank243.study.system.controller;

import java.util.List;

import javax.annotation.Resource;

import com.fank243.study.common.model.PageBean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fank243.study.api.constants.ValidatorGroup;
import com.fank243.study.api.system.ISysUserApi;
import com.fank243.study.api.system.dto.SysUserDTO;
import com.fank243.study.api.system.vo.SysUserVO;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.base.BaseController;
import com.fank243.study.core.exception.BizException;
import com.fank243.study.system.entity.SysUserEntity;
import com.fank243.study.system.service.SysUserService;

import cn.hutool.core.bean.BeanUtil;

/**
 * 系统管理员表 控制器
 * 
 * @author FanWeiJie
 * @since 2021-09-03
 */
@RestController
public class SysUserController extends BaseController implements ISysUserApi {

    @Resource
    private SysUserService sysUserService;

    @Override
    public ResultInfo<SysUserVO> getById(String id) {
        SysUserEntity sysUser = sysUserService.getById(id);
        return ResultInfo.ok(BeanUtil.toBean(sysUser, SysUserVO.class));
    }

    @Override
    public ResultInfo<PageBean<SysUserVO>> page(@RequestBody SysUserDTO sysUser) {
        return ResultInfo.ok(sysUserService.page(sysUser));
    }

    @Override
    public ResultInfo<?> add(@RequestBody @Validated({ValidatorGroup.Create.class}) SysUserDTO sysUser)
        throws BizException {
        boolean isOk = sysUserService.add(sysUser);
        return isOk ? ResultInfo.ok().message("添加成功") : ResultInfo.fail("添加失败");
    }

    @Override
    public ResultInfo<?> modify(@RequestBody @Validated({ValidatorGroup.Modify.class}) SysUserDTO sysUser)
        throws BizException {
        boolean isOk = sysUserService.add(sysUser);
        return isOk ? ResultInfo.ok().message("修改成功") : ResultInfo.fail("修改失败");
    }

    @Override
    public ResultInfo<?> delete(@RequestBody String[] ids) throws BizException {
        boolean isOk = sysUserService.removeByIds(List.of(ids));
        return isOk ? ResultInfo.ok().message("删除成功") : ResultInfo.fail("删除失败");
    }
}
