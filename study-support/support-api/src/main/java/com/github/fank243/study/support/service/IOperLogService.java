package com.github.fank243.study.support.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.constants.ServerConstants;
import com.github.fank243.study.core.domain.dto.OperLogDTO;
import com.github.fank243.study.core.domain.model.PageBean;
import com.github.fank243.study.support.domain.vo.OperLogVO;

/**
 * 操作日志 客户端
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@FeignClient(contextId = "iLogService", value = ServerConstants.SERVER_SUPPORT,
    path = ServerConstants.BASE_URI_SUPPORT + ServerConstants.BASE_URI_SUPPORT_LOG)
public interface IOperLogService {

    /**
     * 操作日志 > 根据日志ID获取
     *
     * @param id 日志ID
     * @return 日志列表
     */
    @GetMapping("/{id}")
    ResultInfo<OperLogVO> getById(@PathVariable String id);

    /**
     * 操作日志 > 分页
     *
     * @param operLogDTO 分页参数
     * @return 日志列表
     */
    @PostMapping("/page")
    ResultInfo<PageBean<OperLogVO>> page(@RequestBody OperLogDTO operLogDTO);

}
