package com.github.fank243.study.support.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.fank243.common.area.Area;
import com.github.fank243.common.area.AreaHelper;
import com.github.fank243.common.result.ResultInfo;
import com.github.fank243.study.core.annotation.RepeatSubmit;
import com.github.fank243.study.core.base.BaseController;
import com.github.fank243.study.core.constants.ServerConstants;
import com.github.fank243.study.support.constants.SupportConstants;
import com.github.fank243.study.support.service.AreaService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.IoUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * 国家行政区划表 控制器
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
@Tag(name = "AreaController", description = "行政区划接口")
@Slf4j
@RequestMapping(ServerConstants.BASE_URI_SUPPORT_AREA)
@RestController
public class AreaController extends BaseController {

    @Resource
    private AreaService areaService;

    /**
     * 行政区划 > 导入
     * 
     * @param multipartFile 国家行政区划TXT文件
     * @return 操作结果
     */
    @Operation(summary = "行政区划-导入")
    @Parameter(name = "file", description = "待上传的行政区划TXT文件", required = true)
    @RepeatSubmit
    @PostMapping("/import")
    public ResultInfo<?> importArea(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return ResultInfo.err400("请上传文件");
        }

        List<Area> areaList;
        try {
            String fileType = FileTypeUtil.getType(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
            if (!SupportConstants.TXT.equalsIgnoreCase(fileType)) {
                return ResultInfo.err400("请上传TXT文件");
            }
            List<String> list = new ArrayList<>(4000);
            IoUtil.readLines(multipartFile.getInputStream(), StandardCharsets.UTF_8, list);
            if (CollUtil.isEmpty(list)) {
                return ResultInfo.err400("文件内容不能为空");
            }
            areaList = AreaHelper.parseArea(list);
        } catch (IOException e) {
            log.error("导入异常：{}", e.getMessage(), e);
            return ResultInfo.err500("导入失败").error(e.toString());
        }

        boolean isOk = areaService.importArea(areaList);

        return isOk ? ResultInfo.ok().message("导入成功") : ResultInfo.err500("导入失败");
    }

    /**
     * 行政区划 > 根据行政区划代码获取子节点(树结构)
     * 
     * @param areaCode 行政区划代码
     * @return 行政区划列表
     */
    @Operation(summary = "行政区划-根据行政区划代码获取")
    @Parameter(name = "areaCode", description = "六位行政区划代码", allowEmptyValue = true)
    @Parameter(name = "isLoadArea", description = "是否加载区县子节点", allowEmptyValue = true)
    @GetMapping("/list")
    public ResultInfo<List<Area>> getAreaList(@RequestParam(required = false, defaultValue = "") String areaCode,
        @RequestParam(required = false, defaultValue = "true") Boolean isLoadArea) {
        List<Area> areaList = AreaHelper.getAreaList(areaCode, isLoadArea);
        return ResultInfo.ok(areaList);
    }
}
