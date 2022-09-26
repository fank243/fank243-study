package com.fank243.study.api.system.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fank243.study.api.system.constants.SystemApiConstants;
import com.fank243.study.api.system.domain.dto.SysUserDTO;
import com.fank243.study.api.system.domain.vo.SysUserVO;
import com.fank243.study.common.domain.model.PageBean;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.constants.ValidatorGroup;
import com.fank243.study.core.web.exception.BizException;

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
    @GetMapping(SystemApiConstants.BASE_URI_SYSTEM_ADMIN + "/get/{userId}")
    ResultInfo<SysUserVO> getById(@PathVariable("userId") String userId);

    /**
     * 系统管理员_分页
     *
     * @param sysUser 查询条件
     * @return 列表
     */
    @PostMapping(SystemApiConstants.BASE_URI_SYSTEM_ADMIN + "/page")
    ResultInfo<PageBean<SysUserVO>> page(@RequestBody SysUserDTO sysUser);

    /**
     * 系统管理员_新增
     *
     * @param sysUser 请求参数
     * @return 操作结果
     * @throws BizException 业务异常，见{@link BizException}
     */
    @PostMapping(SystemApiConstants.BASE_URI_SYSTEM_ADMIN + "/add")
    ResultInfo<?> add(@RequestBody @Validated({ValidatorGroup.Create.class}) SysUserDTO sysUser) throws BizException;

    /**
     * 系统管理员_修改
     *
     * @param sysUser 请求参数
     * @return 操作结果
     * @throws BizException 业务异常，见{@link BizException}
     */
    @PostMapping(SystemApiConstants.BASE_URI_SYSTEM_ADMIN + "/modify")
    ResultInfo<?> modify(@RequestBody @Validated({ValidatorGroup.Modify.class}) SysUserDTO sysUser) throws BizException;

    /**
     * 系统管理员_删除
     *
     * @param ids 主键ID集合
     * @return 操作结果
     * @throws BizException 业务异常，见{@link BizException}
     */
    @DeleteMapping(SystemApiConstants.BASE_URI_SYSTEM_ADMIN + "/delete")
    ResultInfo<?> delete(@RequestBody String[] ids) throws BizException;
}
