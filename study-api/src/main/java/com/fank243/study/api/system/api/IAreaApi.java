package com.fank243.study.api.system.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fank243.study.api.constants.ApiConstants;
import com.fank243.study.api.system.vo.Area;
import com.fank243.study.common.utils.ResultInfo;

/**
 * 国家行政区划管理
 *
 * @author FanWeiJie
 * @since 2022-05-13
 * @restApi
 */
public interface IAreaApi {

    /**
     * 行政区划 > 导入
     *
     * @param multipartFile 国家行政区划TXT文档
     * @return 导入结果
     */
    @PostMapping(ApiConstants.BASE_URI_SYSTEM_AREA + "/import")
    ResultInfo<?> importArea(@RequestParam("file") MultipartFile multipartFile);

    /**
     * 行政区划 > 区划树
     *
     * @param level 层级(0：市级(默认)，1：区县级)
     * @return 区划树
     */
    @GetMapping(
        value = {ApiConstants.BASE_URI_SYSTEM_AREA + "/tree", ApiConstants.BASE_URI_SYSTEM_AREA + "/tree/{level}"})
    ResultInfo<List<Area>> tree(@PathVariable(required = false) Integer level);

    /**
     * 行政区划 > 省级行政区
     *
     * @return 省级行政区
     */
    @GetMapping(ApiConstants.BASE_URI_SYSTEM_AREA + "/getProvinces")
    ResultInfo<List<Area>> getProvinces();

    /**
     * 行政区划 > 根据行政区划代码获取城市、区县行政区划列表
     *
     * @param code 行政区代码
     * @return 省级行政区
     */
    @GetMapping(ApiConstants.BASE_URI_SYSTEM_AREA + "/getAreaByCode/{code}")
    ResultInfo<List<Area>> getAreaByCode(@PathVariable String code);
}
