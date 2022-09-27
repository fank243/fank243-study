package com.fank243.study.support.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.support.constants.SupportApiConstants;
import com.fank243.study.support.domain.dto.ReqRespLogDTO;

/**
 * 请求响应日志表 客户端
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@FeignClient(value = "server-support", path = SupportApiConstants.BASE_URI_SUPPORT_LOG)
public interface IReqRespLogService {

    /**
     * 添加请求响应日志
     * 
     * @param reqRespLogDTO 请求响应信息
     * @return 添加结果
     */
    @PostMapping("/add")
    ResultInfo<?> saveLog(@RequestBody ReqRespLogDTO reqRespLogDTO);
}
