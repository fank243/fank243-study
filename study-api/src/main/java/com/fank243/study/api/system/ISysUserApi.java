package com.fank243.study.api.system;

import com.fank243.study.api.constants.ApiConstants;
import com.fank243.study.api.constants.ValidatorGroup;
import com.fank243.study.api.system.dto.SysUserDTO;
import com.fank243.study.api.system.vo.SysUserVO;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.exception.BizException;
import com.fank243.study.core.model.PageBean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统管理员表 Api
 * </p>
 *
 * @author FanWeiJie
 * @since 2021-09-03
 */
public interface ISysUserApi {

    /**
     * 系统管理员表_根据ID查找
     *
     * @param id 主键ID
     * @return 用户实体
     */
    @GetMapping(ApiConstants.BASE_URI_SYSTEM_ADMIN + "/get/{id}")
    ResultInfo<SysUserVO> getById(@PathVariable("id") String id);

    /**
     * 系统管理员表_分页
     *
     * @param sysUser 查询条件
     * @return 列表
     */
    @PostMapping(ApiConstants.BASE_URI_SYSTEM_ADMIN + "/page")
    ResultInfo<PageBean<SysUserVO>> page(@RequestBody SysUserDTO sysUser);

    /**
     * 系统管理员表_新增
     *
     * @param sysUser 请求参数
     * @return 操作结果
     */
    @PostMapping(ApiConstants.BASE_URI_SYSTEM_ADMIN + "/add")
    ResultInfo<?> add(@RequestBody @Validated({ValidatorGroup.Create.class}) SysUserDTO sysUser) throws BizException;

    /**
     * 系统管理员表_修改
     *
     * @param sysUser 请求参数
     * @return 操作结果
     */
    @PostMapping(ApiConstants.BASE_URI_SYSTEM_ADMIN + "/modify")
    ResultInfo<?> modify(@RequestBody @Validated({ValidatorGroup.Modify.class}) SysUserDTO sysUser) throws BizException;

    /**
     * 系统管理员表_删除
     *
     * @param ids 主键ID集合
     * @return 操作结果
     */
    @DeleteMapping(ApiConstants.BASE_URI_SYSTEM_ADMIN + "/delete")
    ResultInfo<?> delete(@RequestBody String[] ids) throws BizException;
}
