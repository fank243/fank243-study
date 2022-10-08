package com.fank243.study.system.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cn.hutool.core.util.StrUtil;
import com.fank243.study.log.constants.LogRecordType;
import com.fank243.study.system.domain.vo.SysUserVO;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
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
import com.fank243.study.system.domain.dto.SysRoleDTO;
import com.fank243.study.system.domain.entity.SysRoleEntity;
import com.fank243.study.system.domain.vo.SysRoleVO;
import com.fank243.study.system.service.SysRoleService;

import cn.hutool.core.bean.BeanUtil;

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
     * @param id 角色ID
     * @return 角色信息
     */
    @GetMapping("/get/{id}")
    public ResultInfo<SysRoleVO> getById(@PathVariable String id) {
        SysRoleEntity sysRole = sysRoleService.getById(id);
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
    @PostMapping("/add")
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
        boolean isOk = sysRoleService.add(sysRole);
        return isOk ? ResultInfo.ok().message("修改成功") : ResultInfo.err500("修改失败");
    }

    /**
     * 系统角色 > 删除
     *
     * @param ids 角色ID数组
     * @return 操作结果
     */
    @LogRecord(type = LogRecordType.SYS_ROLE, bizNo = "{{#roleId}}",
            success = "删除角色【{{#roleName}}】成功", successCondition = "{{#success == true}}")
    @RepeatSubmit
    @DeleteMapping("/delete")
    public ResultInfo<?> delete(@RequestBody String[] ids) throws BizException {
        List<SysRoleVO> sysUserList = sysRoleService.findByRoleIn(List.of(ids));
        String roleName = sysUserList.stream().map(SysRoleVO::getRoleName).collect(Collectors.joining("、"));
        LogRecordContext.putVariable("roleId", String.join(",", ids));
        LogRecordContext.putVariable("roleName", roleName);
        LogRecordContext.putVariable("success", StrUtil.isNotBlank(roleName));

        boolean isOk = sysRoleService.removeByIds(List.of(ids));
        return isOk ? ResultInfo.ok().message("删除成功") : ResultInfo.err500("删除失败");
    }

    /**
     * 系统角色 > 根据用户ID获取
     *
     * @param userId 用户ID
     * @return 用户角色列表
     */
    @GetMapping("/getByUserId/{userId}")
    public List<SysRoleVO> getByUserId(@PathVariable String userId) {
        return sysRoleService.findByUserId(userId);
    }
}
