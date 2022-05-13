package com.fank243.study.api.system.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.fank243.study.api.constants.ApiConstants;
import com.fank243.study.api.constants.ValidatorGroup;
import com.fank243.study.api.system.dto.SysPermDTO;
import com.fank243.study.api.system.vo.SysPermVO;
import com.fank243.study.common.model.PageBean;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.exception.BizException;

/**
 * 系统权限表 Api
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
public interface ISysPermApi {

    /**
     * 系统权限表_根据ID查找
     *
     * @param id 主键ID
     * @return 用户实体
     */
    @GetMapping(ApiConstants.BASE_URI_SYSTEM_PERM + "/get/{id}")
    ResultInfo<SysPermVO> getById(@PathVariable("id") String id);

    /**
     * 系统权限表_分页
     *
     * @param sysPerm 查询条件
     * @return 列表
     */
    @PostMapping(ApiConstants.BASE_URI_SYSTEM_PERM + "/page")
    ResultInfo<PageBean<SysPermVO>> page(@RequestBody SysPermDTO sysPerm);

    /**
     * 系统权限表_新增
     *
     * @param sysPerm 请求参数
     * @return 操作结果
     * @throws BizException 业务异常，见{@link BizException}
     */
    @PostMapping(ApiConstants.BASE_URI_SYSTEM_PERM + "/add")
    ResultInfo<?> add(@RequestBody @Validated({ValidatorGroup.Create.class}) SysPermDTO sysPerm) throws BizException;

    /**
     * 系统权限表_修改
     *
     * @param sysPerm 请求参数
     * @return 操作结果
     * @throws BizException 业务异常，见{@link BizException}
     */
    @PostMapping(ApiConstants.BASE_URI_SYSTEM_PERM + "/modify")
    ResultInfo<?> modify(@RequestBody @Validated({ValidatorGroup.Modify.class}) SysPermDTO sysPerm) throws BizException;

    /**
     * 系统权限表_删除
     *
     * @param ids 主键ID集合
     * @return 操作结果
     * @throws BizException 业务异常，见{@link BizException}
     */
    @DeleteMapping(ApiConstants.BASE_URI_SYSTEM_PERM + "/delete")
    ResultInfo<?> delete(@RequestBody String[] ids) throws BizException;
}
