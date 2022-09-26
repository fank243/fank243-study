package com.fank243.study.api.support.client;

import org.springframework.cloud.openfeign.FeignClient;

import com.fank243.study.api.support.api.IAreaApi;

/**
 * 国家行政区划表 客户端
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@FeignClient(value = "server-system")
public interface IAreaFeignClient extends IAreaApi {

}
