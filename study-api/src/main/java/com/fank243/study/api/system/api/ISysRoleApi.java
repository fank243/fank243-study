package com.fank243.study.api.system.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fank243.study.api.constants.ApiConstants;
import com.fank243.study.api.constants.ValidatorGroup;
import com.fank243.study.api.system.dto.SysRoleDTO;
import com.fank243.study.api.system.vo.SysRoleVO;
import com.fank243.study.common.model.PageBean;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.exception.BizException;

/**
 * 角色管理
 *
 * @author FanWeiJie
 * @since 2022-05-13
 * @restApi
 */
public interface ISysRoleApi {

    /**
     * 角色管理_根据ID查找
     *
     * @param id 主键ID
     * @return 用户实体
     */
    @GetMapping(ApiConstants.BASE_URI_SYSTEM_ROLE + "/get/{id}")
    ResultInfo<SysRoleVO> getById(@PathVariable("id") String id);

    /**
     * 角色管理_分页
     *
     * @param sysRole 查询条件
     * @return 列表
     */
    @PostMapping(ApiConstants.BASE_URI_SYSTEM_ROLE + "/page")
    ResultInfo<PageBean<SysRoleVO>> page(@RequestBody SysRoleDTO sysRole);

    /**
     * 角色管理_新增
     *
     * @param sysRole 请求参数
     * @return 操作结果
     * @throws BizException 业务异常，见{@link BizException}
     */
    @PostMapping(ApiConstants.BASE_URI_SYSTEM_ROLE + "/add")
    ResultInfo<?> add(@RequestBody @Validated({ValidatorGroup.Create.class}) SysRoleDTO sysRole) throws BizException;

    /**
     * 角色管理_修改
     *
     * @param sysRole 请求参数
     * @return 操作结果
     * @throws BizException 业务异常，见{@link BizException}
     */
    @PostMapping(ApiConstants.BASE_URI_SYSTEM_ROLE + "/modify")
    ResultInfo<?> modify(@RequestBody @Validated({ValidatorGroup.Modify.class}) SysRoleDTO sysRole) throws BizException;

    /**
     * 角色管理_删除
     *
     * @param ids 主键ID集合
     * @return 操作结果
     * @throws BizException 业务异常，见{@link BizException}
     */
    @DeleteMapping(ApiConstants.BASE_URI_SYSTEM_ROLE + "/delete")
    ResultInfo<?> delete(@RequestBody String[] ids) throws BizException;
}
