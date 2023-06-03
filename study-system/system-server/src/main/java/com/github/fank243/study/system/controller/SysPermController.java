package com.github.fank243.study.system.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.annotation.RepeatSubmit;
import com.github.fank243.study.core.base.BaseController;
import com.github.fank243.study.core.constants.LogRecordType;
import com.github.fank243.study.core.constants.ServerConstants;
import com.github.fank243.study.core.domain.model.PageBean;
import com.github.fank243.study.core.exception.BizException;
import com.github.fank243.study.core.model.validation.ValidatorGroup;
import com.github.fank243.study.system.domain.dto.SysPermDTO;
import com.github.fank243.study.system.domain.entity.SysPermEntity;
import com.github.fank243.study.system.domain.vo.SysPermVO;
import com.github.fank243.study.system.service.SysPermService;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;

import cn.hutool.core.bean.BeanUtil;
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
        SysPermEntity sysPerm = sysPermService.getById(permId);
        return ResultInfo.ok(BeanUtil.toBean(sysPerm, SysPermVO.class));
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
     * @param permId 权限ID
     * @return 操作结果
     */
    @LogRecord(type = LogRecordType.LOG_SYS_PERM, subType = "delete", bizNo = "{{#permId}}",
        success = "删除管理员【{{#permName}}】成功", successCondition = "{{#_ret.success == true}}")
    @RepeatSubmit
    @DeleteMapping("/id/{permId}")
    public ResultInfo<?> delete(@PathVariable("permId") String permId) throws BizException {
        SysPermVO sysPermVO = sysPermService.findByPermId(permId);
        if (sysPermVO == null) {
            return ResultInfo.err404("权限菜单不存在");
        }
        LogRecordContext.putVariable("permName", sysPermVO.getPermName());

        boolean isOk = sysPermService.removeById(permId);
        return isOk ? ResultInfo.ok().message("删除成功") : ResultInfo.err500("删除失败");
    }

    /**
     * 菜单权限 > 根据权限类型获取
     *
     * @param perms 权限类型列表
     * @return 操作结果
     */
    @PostMapping("/types")
    public List<SysPermVO> getByPermTypes(@RequestBody List<Integer> perms) {
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
