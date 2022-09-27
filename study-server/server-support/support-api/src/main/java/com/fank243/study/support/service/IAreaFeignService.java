package com.fank243.study.support.service;

import org.springframework.cloud.openfeign.FeignClient;

import com.fank243.study.support.constants.SupportApiConstants;

/**
 * 国家行政区划表 客户端
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@FeignClient(value = "server-system", path = SupportApiConstants.BASE_URI_SUPPORT_AREA)
public interface IAreaFeignService {

}
