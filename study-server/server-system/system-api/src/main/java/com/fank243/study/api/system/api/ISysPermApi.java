package com.fank243.study.api.system.api;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fank243.study.api.system.constants.SystemApiConstants;
import com.fank243.study.api.system.domain.dto.SysPermDTO;
import com.fank243.study.api.system.domain.vo.SysPermVO;
import com.fank243.study.common.domain.model.PageBean;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.constants.ValidatorGroup;
import com.fank243.study.core.web.exception.BizException;

/**
 * 菜单管理
 *
 * @author FanWeiJie
 * @since 2022-05-13
 * @restApi
 */
public interface ISysPermApi {

    /**
     * 菜单管理_根据ID查找
     *
     * @param id 主键ID
     * @return 用户实体
     */
    @GetMapping(SystemApiConstants.BASE_URI_SYSTEM_PERM + "/get/{id}")
    ResultInfo<SysPermVO> getById(@PathVariable("id") String id);

    /**
     * 菜单管理_分页
     *
     * @param sysPerm 查询条件
     * @return 列表
     */
    @PostMapping(SystemApiConstants.BASE_URI_SYSTEM_PERM + "/page")
    ResultInfo<PageBean<SysPermVO>> page(@RequestBody SysPermDTO sysPerm);

    /**
     * 菜单管理_新增
     *
     * @param sysPerm 请求参数
     * @return 操作结果
     * @throws BizException 业务异常，见{@link BizException}
     */
    @PostMapping(SystemApiConstants.BASE_URI_SYSTEM_PERM + "/add")
    ResultInfo<?> add(@RequestBody @Validated({ValidatorGroup.Create.class}) SysPermDTO sysPerm) throws BizException;

    /**
     * 菜单管理_修改
     *
     * @param sysPerm 请求参数
     * @return 操作结果
     * @throws BizException 业务异常，见{@link BizException}
     */
    @PostMapping(SystemApiConstants.BASE_URI_SYSTEM_PERM + "/modify")
    ResultInfo<?> modify(@RequestBody @Validated({ValidatorGroup.Modify.class}) SysPermDTO sysPerm) throws BizException;

    /**
     * 菜单管理_删除
     *
     * @param ids 主键ID集合
     * @return 操作结果
     * @throws BizException 业务异常，见{@link BizException}
     */
    @DeleteMapping(SystemApiConstants.BASE_URI_SYSTEM_PERM + "/delete")
    ResultInfo<?> delete(@RequestBody String[] ids) throws BizException;

    /**
     * 根据权限类型获取所有权限
     * 
     * @param perms 权限类型
     * @return 权限列表
     */
    @PostMapping(SystemApiConstants.BASE_URI_SYSTEM_PERM + "/getByPermTypes")
    List<SysPermVO> getByPermTypes(@RequestParam("perms") List<Integer> perms);

    /**
     * 根据用户ID获取权限
     * 
     * @param userId 用户ID
     * @return 用户权限列表
     */
    @PostMapping(SystemApiConstants.BASE_URI_SYSTEM_PERM + "/getByUserId")
    List<SysPermVO> getByUserId(@RequestParam("userId") String userId);
}
