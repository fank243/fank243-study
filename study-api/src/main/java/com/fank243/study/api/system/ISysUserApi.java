package com.fank243.study.api.system;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.fank243.study.api.constants.ApiConstants;
import com.fank243.study.api.constants.ValidatorGroup;
import com.fank243.study.api.system.dto.SysUserDTO;
import com.fank243.study.api.system.vo.SysUserVO;
import com.fank243.study.common.model.PageBean;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.exception.BizException;

/**
 * 系统管理员
 *
 * @author FanWeiJie
 * @since 2021-09-03
 * @restApi
 */
public interface ISysUserApi {

    /**
     * 系统管理员_根据ID查找
     *
     * @param userId 用户ID
     * @return 用户实体
     */
    @GetMapping(ApiConstants.BASE_URI_SYSTEM_ADMIN + "/get/{userId}")
    ResultInfo<SysUserVO> getById(@PathVariable("userId") String userId);

    /**
     * 系统管理员_分页
     *
     * @param sysUser 查询条件
     * @return 列表
     */
    @PostMapping(ApiConstants.BASE_URI_SYSTEM_ADMIN + "/page")
    ResultInfo<PageBean<SysUserVO>> page(@RequestBody SysUserDTO sysUser);

    /**
     * 系统管理员_新增
     *
     * @param sysUser 请求参数
     * @return 操作结果
     */
    @PostMapping(ApiConstants.BASE_URI_SYSTEM_ADMIN + "/add")
    ResultInfo<?> add(@RequestBody @Validated({ValidatorGroup.Create.class}) SysUserDTO sysUser) throws BizException;

    /**
     * 系统管理员_修改
     *
     * @param sysUser 请求参数
     * @return 操作结果
     */
    @PostMapping(ApiConstants.BASE_URI_SYSTEM_ADMIN + "/modify")
    ResultInfo<?> modify(@RequestBody @Validated({ValidatorGroup.Modify.class}) SysUserDTO sysUser) throws BizException;

    /**
     * 系统管理员_删除
     *
     * @param ids 主键ID集合
     * @return 操作结果
     */
    @DeleteMapping(ApiConstants.BASE_URI_SYSTEM_ADMIN + "/delete")
    ResultInfo<?> delete(@RequestBody String[] ids) throws BizException;
}
