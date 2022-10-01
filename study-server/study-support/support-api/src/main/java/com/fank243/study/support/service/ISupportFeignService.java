package com.fank243.study.support.service;

import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.support.domain.dto.ReqRespLogDTO;
import org.springframework.cloud.openfeign.FeignClient;

import com.fank243.study.support.constants.SupportApiConstants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 国家行政区划表 客户端
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@FeignClient(value = "support-server", path = SupportApiConstants.BASE_URI_SUPPORT)
public interface ISupportFeignService {
    /**
     * 添加请求响应日志
     *
     * @param reqRespLogDTO 请求响应信息
     * @return 添加结果
     */
    @PostMapping("/area/add")
    ResultInfo<?> saveLog(@RequestBody ReqRespLogDTO reqRespLogDTO);
}
