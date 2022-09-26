package com.fank243.study.api.support.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.fank243.study.api.support.api.IReqRespLogApi;

/**
 * 请求响应日志表 客户端
 *
 * @author FanWeiJie
 * @since 2022-09-26 15:14:51
 */
@FeignClient(value = "server-support")
public interface IReqRespLogFeignClient extends IReqRespLogApi {

}

