package com.fank243.study.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;

import com.fank243.study.api.system.api.ISysUserApi;
import com.fank243.study.api.system.domain.dto.SysUserDTO;
import com.fank243.study.api.system.domain.vo.SysUserVO;
import com.fank243.study.common.annotation.RepeatSubmit;
import com.fank243.study.common.domain.model.PageBean;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.web.exception.BizException;
import com.fank243.study.system.domain.entity.SysUserEntity;
import com.fank243.study.system.service.SysUserService;

import cn.hutool.core.bean.BeanUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统管理员表 控制器
 * 
 * @author FanWeiJie
 * @since 2021-09-03
 */
@Slf4j
@RestController
public class SysUserController implements ISysUserApi {

    @Resource
    private SysUserService sysUserService;

    @Override
    public ResultInfo<SysUserVO> getById(String userId) {
        SysUserEntity sysUser = sysUserService.getById(userId);
        return ResultInfo.ok(BeanUtil.toBean(sysUser, SysUserVO.class));
    }

    @Override
    public ResultInfo<PageBean<SysUserVO>> page(SysUserDTO sysUser) {
        return ResultInfo.ok(sysUserService.page(sysUser));
    }

    @RepeatSubmit
    @Override
    public ResultInfo<?> add(SysUserDTO sysUser) throws BizException {
        boolean isOk = sysUserService.add(sysUser);
        return isOk ? ResultInfo.ok().message("添加成功") : ResultInfo.fail("添加失败");
    }

    @RepeatSubmit
    @Override
    public ResultInfo<?> modify(SysUserDTO sysUser) throws BizException {
        boolean isOk = sysUserService.modify(sysUser);
        return isOk ? ResultInfo.ok().message("修改成功") : ResultInfo.fail("修改失败");
    }

    @RepeatSubmit
    @Override
    public ResultInfo<?> delete(String[] ids) {
        boolean isOk = sysUserService.removeByIds(List.of(ids));
        return isOk ? ResultInfo.ok().message("删除成功") : ResultInfo.fail("删除失败");
    }
}
