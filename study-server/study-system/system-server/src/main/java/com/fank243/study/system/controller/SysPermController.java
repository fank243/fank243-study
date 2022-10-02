package com.fank243.study.system.controller;

import java.util.List;

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
import com.fank243.study.system.domain.dto.SysPermDTO;
import com.fank243.study.system.domain.entity.SysPermEntity;
import com.fank243.study.system.domain.vo.SysPermVO;
import com.fank243.study.system.service.SysPermService;

import cn.hutool.core.bean.BeanUtil;

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
     * @param id 权限ID
     * @return 权限信息
     */
    @GetMapping("/get/{id}")
    public ResultInfo<SysPermVO> getById(@PathVariable("id") String id) {
        SysPermEntity sysPerm = sysPermService.getById(id);
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
        return isOk ? ResultInfo.ok().message("添加成功") : ResultInfo.fail("添加失败");
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
        boolean isOk = sysPermService.add(sysPerm);
        return isOk ? ResultInfo.ok().message("修改成功") : ResultInfo.fail("修改失败");
    }

    /**
     * 菜单权限 > 删除
     *
     * @param ids 权限ID数组
     * @return 操作结果
     */
    @RepeatSubmit
    @DeleteMapping("/delete")
    public ResultInfo<?> delete(@RequestBody String[] ids) throws BizException {
        boolean isOk = sysPermService.removeByIds(List.of(ids));
        return isOk ? ResultInfo.ok().message("删除成功") : ResultInfo.fail("删除失败");
    }

    /**
     * 菜单权限 > 根据权限类型获取
     *
     * @param perms 权限类型列表
     * @return 操作结果
     */
    @PostMapping("/getByPermTypes")
    public List<SysPermVO> getByPermTypes(@RequestBody List<Integer> perms) {
        return sysPermService.findByPermTypes(perms);
    }

    /**
     * 菜单权限 > 根据用户ID获取
     *
     * @param userId 用户ID
     * @return 用户权限列表
     */
    @GetMapping("/getByUserId/{userId}")
    public List<SysPermVO> getByUserId(@PathVariable String userId) {
        return sysPermService.findByUserId(userId);
    }
}
