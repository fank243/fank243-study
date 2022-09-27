package com.fank243.study.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fank243.study.api.system.api.ISysPermApi;
import com.fank243.study.api.system.domain.dto.SysPermDTO;
import com.fank243.study.api.system.domain.vo.SysPermVO;
import com.fank243.study.common.annotation.RepeatSubmit;
import com.fank243.study.common.domain.base.BaseController;
import com.fank243.study.common.domain.model.PageBean;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.web.exception.BizException;
import com.fank243.study.system.domain.entity.SysPermEntity;
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
    public ResultInfo<PageBean<SysPermVO>> page( SysPermDTO sysPerm) {
        return ResultInfo.ok(sysPermService.page(sysPerm));
    }

    @RepeatSubmit
    @Override
    public ResultInfo<?> add(SysPermDTO sysPerm) throws BizException {
        boolean isOk = sysPermService.add(sysPerm);
        return isOk ? ResultInfo.ok().message("添加成功") : ResultInfo.fail("添加失败");
    }

    @RepeatSubmit
    @Override
    public ResultInfo<?> modify(SysPermDTO sysPerm) throws BizException {
        boolean isOk = sysPermService.add(sysPerm);
        return isOk ? ResultInfo.ok().message("修改成功") : ResultInfo.fail("修改失败");
    }

    @RepeatSubmit
    @Override
    public ResultInfo<?> delete( String[] ids) throws BizException {
        boolean isOk = sysPermService.removeByIds(List.of(ids));
        return isOk ? ResultInfo.ok().message("删除成功") : ResultInfo.fail("删除失败");
    }

    @Override
    public List<SysPermVO> getByPermTypes(List<Integer> perms) {
        return sysPermService.findByPermTypes(perms);
    }

    @Override
    public List<SysPermVO> getByUserId(String userId) {
        return sysPermService.findByUserId(userId);
    }
}
