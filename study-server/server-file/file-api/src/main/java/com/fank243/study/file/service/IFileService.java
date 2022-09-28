package com.fank243.study.file.service;

import org.springframework.cloud.openfeign.FeignClient;

import com.fank243.study.file.constants.FileApiConstants;

/**
 * 文件表 客户端
 *
 * @author FanWeiJie
 * @since 2022-09-28 14:23:01
 */
@FeignClient(value = "server-system", path = FileApiConstants.BASE_URI_FILE)
public interface IFileService {

}
