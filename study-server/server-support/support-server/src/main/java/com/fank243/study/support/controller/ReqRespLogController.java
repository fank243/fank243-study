package com.fank243.study.support.controller;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fank243.study.common.domain.base.BaseController;
import com.fank243.study.common.domain.model.PageBean;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.constants.ValidatorGroup;
import com.fank243.study.core.web.exception.BizException;
import com.fank243.study.support.constants.SupportApiConstants;
import com.fank243.study.support.domain.dto.ReqRespLogDTO;
import com.fank243.study.support.domain.entity.ReqRespLogEntity;
import com.fank243.study.support.domain.vo.ReqRespLogVO;
import com.fank243.study.support.service.ReqRespLogService;

import cn.hutool.core.bean.BeanUtil;

/**
 * 请求响应日志表 控制器
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@RequestMapping(SupportApiConstants.BASE_URI_SUPPORT_LOG)
@RestController
public class ReqRespLogController extends BaseController {

    @Resource
    private ReqRespLogService reqRespLogService;

    /**
     * 请求响应日志 > 根据日志ID获取
     * 
     * @param id 日志ID
     * @return 日志列表
     */
    @GetMapping("/get/{id}")
    public ResultInfo<ReqRespLogVO> getById(@PathVariable String id) {
        ReqRespLogEntity reqRespLog = reqRespLogService.getById(id);
        return ResultInfo.ok(BeanUtil.toBean(reqRespLog, ReqRespLogVO.class));
    }

    /**
     * 请求响应日志 > 分页
     *
     * @param reqRespLog 分页参数
     * @return 日志列表
     */
    @PostMapping("/page")
    public ResultInfo<PageBean<ReqRespLogVO>> page(@RequestBody ReqRespLogDTO reqRespLog) {
        return ResultInfo.ok(reqRespLogService.page(reqRespLog));
    }

    /**
     * 请求响应日志 > 新增
     *
     * @param reqRespLog 请求参数
     * @return 操作结果
     */
    @PostMapping("/add")
    public ResultInfo<?> add(@RequestBody @Validated({ValidatorGroup.Create.class}) ReqRespLogDTO reqRespLog)
        throws BizException {
        boolean isOk = reqRespLogService.add(reqRespLog);
        return isOk ? ResultInfo.ok().message("添加成功") : ResultInfo.fail("添加失败");
    }
}
