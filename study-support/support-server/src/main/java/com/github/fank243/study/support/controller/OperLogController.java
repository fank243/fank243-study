package com.github.fank243.study.support.controller;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.base.BaseController;
import com.github.fank243.study.core.constants.ServerConstants;
import com.github.fank243.study.core.constants.ValidatorGroup;
import com.github.fank243.study.core.domain.model.PageBean;
import com.github.fank243.study.core.exception.BizException;
import com.github.fank243.study.support.domain.dto.OperLogDTO;
import com.github.fank243.study.support.domain.entity.OperLogEntity;
import com.github.fank243.study.support.domain.vo.OperLogVO;
import com.github.fank243.study.support.service.OperLogService;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 请求响应日志表 控制器
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@Tag(name = "OperLogController", description = "操作日志接口")
@RequestMapping(ServerConstants.BASE_URI_SUPPORT_LOG)
@RestController
public class OperLogController extends BaseController {

    @Resource
    private OperLogService operLogService;

    /**
     * 操作日志 > 根据日志ID获取
     * 
     * @param id 日志ID
     * @return 日志列表
     */
    @Operation(summary = "操作日志-根据ID获取", description = "获取操作记录")
    @Parameter(description = "操作日志记录ID")
    @GetMapping("/{id}")
    public ResultInfo<OperLogVO> getById(@PathVariable String id) {
        OperLogEntity operLogEntity = operLogService.getById(id);
        return ResultInfo.ok(BeanUtil.toBean(operLogEntity, OperLogVO.class));
    }

    /**
     * 操作日志 > 分页
     *
     * @param reqRespLog 分页参数
     * @return 日志列表
     */
    @Operation(summary = "操作日志-分页")
    @PostMapping("/page")
    public ResultInfo<PageBean<OperLogVO>> page(@RequestBody OperLogDTO reqRespLog) {
        return ResultInfo.ok(operLogService.page(reqRespLog));
    }

    /**
     * 操作日志 > 新增
     *
     * @param reqRespLog 请求参数
     * @return 操作结果
     */
    @Operation(summary = "操作日志-新增")
    @PostMapping("/add")
    public ResultInfo<?> add(@RequestBody @Validated({ValidatorGroup.Create.class}) OperLogDTO reqRespLog)
        throws BizException {
        boolean isOk = operLogService.add(reqRespLog);
        return isOk ? ResultInfo.ok().message("添加成功") : ResultInfo.err500("添加失败");
    }
}
