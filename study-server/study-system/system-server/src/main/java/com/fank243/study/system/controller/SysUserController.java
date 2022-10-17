package com.fank243.study.system.controller;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fank243.study.common.core.annotation.RepeatSubmit;
import com.fank243.study.common.core.base.BaseController;
import com.fank243.study.common.core.constants.ServerConstants;
import com.fank243.study.common.core.constants.ValidatorGroup;
import com.fank243.study.common.core.domain.model.PageBean;
import com.fank243.study.common.core.exception.BizException;
import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.log.constants.LogRecordType;
import com.fank243.study.system.domain.dto.SysUserDTO;
import com.fank243.study.system.domain.entity.SysUserEntity;
import com.fank243.study.system.domain.vo.SysUserVO;
import com.fank243.study.system.service.SysUserService;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;

import cn.hutool.core.bean.BeanUtil;
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
    @GetMapping("/{userId}")
    public ResultInfo<SysUserVO> getById(@PathVariable String userId) {
        SysUserEntity sysUser = sysUserService.getById(userId);
        return ResultInfo.ok(BeanUtil.toBean(sysUser, SysUserVO.class));
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
     * @param userId 用户ID
     * @return 操作结果
     */
    @LogRecord(type = LogRecordType.SYS_USER, bizNo = "{{#userId}}", success = "删除管理员【{{#username}}】成功",
        successCondition = "{{#_rest.success == true}}")
    @RepeatSubmit
    @DeleteMapping("/{userId}")
    public ResultInfo<?> delete(@PathVariable("userId") String userId) {
        SysUserVO sysUserVO = sysUserService.findByUserId(userId);
        if (sysUserVO == null) {
            return ResultInfo.err404("用户不存在");
        }
        LogRecordContext.putVariable("username", sysUserVO.getUsername());

        boolean isOk = sysUserService.removeById(userId);
        return isOk ? ResultInfo.ok().message("删除成功") : ResultInfo.err500("删除失败");
    }
}
