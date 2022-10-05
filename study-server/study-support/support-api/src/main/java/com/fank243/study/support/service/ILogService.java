package com.fank243.study.support.service;

import com.fank243.study.common.core.constants.ServerConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.support.domain.dto.LogDTO;

/**
 * 国家行政区划表 客户端
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@FeignClient(contextId = "iLogService", value = "study-support", path = ServerConstants.BASE_URI_SUPPORT_LOG)
public interface ILogService {
    /**
     * 添加请求响应日志
     *
     * @param logDTO 请求响应信息
     * @return 添加结果
     */
    @PostMapping("/add")
    ResultInfo<?> add(@RequestBody LogDTO logDTO);
}
