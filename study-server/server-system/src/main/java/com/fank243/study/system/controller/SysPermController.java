package com.fank243.study.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fank243.study.api.constants.ValidatorGroup;
import com.fank243.study.api.system.api.ISysPermApi;
import com.fank243.study.api.system.dto.SysPermDTO;
import com.fank243.study.api.system.vo.SysPermVO;
import com.fank243.study.common.model.PageBean;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.annotation.RepeatSubmit;
import com.fank243.study.core.base.BaseController;
import com.fank243.study.core.exception.BizException;
import com.fank243.study.system.entity.SysPermEntity;
import com.fank243.study.system.service.SysPermService;

import cn.hutool.core.bean.BeanUtil;

/**
 * 系统权限表 控制器
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@RestController
public class SysPermController extends BaseController implements ISysPermApi {

    @Resource
    private SysPermService sysPermService;

    @Override
    public ResultInfo<SysPermVO> getById(String id) {
        SysPermEntity sysPerm = sysPermService.getById(id);
        return ResultInfo.ok(BeanUtil.toBean(sysPerm, SysPermVO.class));
    }

    @Override
    public ResultInfo<PageBean<SysPermVO>> page(@RequestBody SysPermDTO sysPerm) {
        return ResultInfo.ok(sysPermService.page(sysPerm));
    }

    @RepeatSubmit
    @Override
    public ResultInfo<?> add(@RequestBody @Validated({ValidatorGroup.Create.class}) SysPermDTO sysPerm)
        throws BizException {
        boolean isOk = sysPermService.add(sysPerm);
        return isOk ? ResultInfo.ok().message("添加成功") : ResultInfo.fail("添加失败");
    }

    @RepeatSubmit
    @Override
    public ResultInfo<?> modify(@RequestBody @Validated({ValidatorGroup.Modify.class}) SysPermDTO sysPerm)
        throws BizException {
        boolean isOk = sysPermService.add(sysPerm);
        return isOk ? ResultInfo.ok().message("修改成功") : ResultInfo.fail("修改失败");
    }

    @RepeatSubmit
    @Override
    public ResultInfo<?> delete(@RequestBody String[] ids) throws BizException {
        boolean isOk = sysPermService.removeByIds(List.of(ids));
        return isOk ? ResultInfo.ok().message("删除成功") : ResultInfo.fail("删除失败");
    }
}
