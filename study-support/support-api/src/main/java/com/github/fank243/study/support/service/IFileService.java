package com.github.fank243.study.support.service;

import org.springframework.cloud.openfeign.FeignClient;

import com.github.fank243.study.core.constants.ServerConstants;

/**
 * 文件表 客户端
 *
 * @author FanWeiJie
 * @since 2022-09-28 14:23:01
 */
@FeignClient(contextId = "iFileService", value = ServerConstants.SERVER_SUPPORT, path = ServerConstants.BASE_URI_FILE)
public interface IFileService {

}
