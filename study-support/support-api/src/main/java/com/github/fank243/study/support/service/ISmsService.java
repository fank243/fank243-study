package com.github.fank243.study.support.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.constants.ServerConstants;
import com.github.fank243.study.support.domain.dto.OperLogDTO;

/**
 * 国家行政区划表 客户端
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@FeignClient(contextId = "iSmsService", value = "study-support", path = ServerConstants.BASE_URI_SUPPORT_LOG)
public interface ISmsService {
    /**
     * 发送短信验证码
     *
     * @param operLogDTO 请求响应信息
     * @return 添加结果
     */
    @PostMapping("/send")
    ResultInfo<?> send(@RequestBody OperLogDTO operLogDTO);
}
