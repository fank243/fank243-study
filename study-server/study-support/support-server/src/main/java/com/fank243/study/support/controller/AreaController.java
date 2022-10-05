package com.fank243.study.support.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fank243.study.common.core.annotation.RepeatSubmit;
import com.fank243.study.common.core.base.BaseController;
import com.fank243.study.common.core.constants.ServerConstants;
import com.fank243.study.common.core.domain.model.Area;
import com.fank243.study.common.core.utils.AreaUtils;
import com.fank243.study.common.core.utils.ResultInfo;
import com.fank243.study.support.service.AreaService;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 国家行政区划表 控制器
 *
 * @author FanWeiJie
 * @since 2022-05-13
 */
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
    @RepeatSubmit
    @PostMapping("/import")
    public ResultInfo<?> importArea(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return ResultInfo.err400("请上传文件");
        }

        List<Area> areaList;
        try {
            String fileType = FileTypeUtil.getType(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
            if (!"txt".equalsIgnoreCase(fileType)) {
                return ResultInfo.err400("请上传TXT文件");
            }
            List<String> list = new ArrayList<>(4000);
            IoUtil.readLines(multipartFile.getInputStream(), StandardCharsets.UTF_8, list);
            if (CollUtil.isEmpty(list)) {
                return ResultInfo.err400("文件内容不能为空");
            }
            areaList = AreaUtils.parse(list);
        } catch (IOException e) {
            log.error("导入异常：{}", e.getMessage(), e);
            return ResultInfo.err500("导入失败").error(e.toString());
        }

        boolean isOk = areaService.importArea(areaList);

        return isOk ? ResultInfo.ok().message("导入成功") : ResultInfo.err500("导入失败");
    }

    /**
     * 行政区划 > 区划树
     * 
     * @param level 层级(0:省市，1：省市县)
     * @return 区域树
     */
    @GetMapping(value = {"/tree", "/tree/{level}"})
    public ResultInfo<List<Area>> tree(@PathVariable(value = "level", required = false) Integer level) {
        level = Convert.toInt(level, 0);
        level = level == 1 ? 1 : 0;
        return ResultInfo.ok(areaService.generatorTree(level));
    }

    /**
     * 行政区划 > 获取省级行政区划
     * 
     * @return 省级行政区划列表
     */
    @GetMapping("/getProvinces")
    public ResultInfo<List<Area>> getProvinces() {
        return ResultInfo.ok(areaService.findProvinces());
    }

    /**
     * 行政区划 > 根据行政区划代码获取
     * 
     * @param code 行政区划代码
     * @return 行政区划列表
     */
    @GetMapping("/getAreaByCode/{code}")
    public ResultInfo<List<Area>> getAreaByCode(@PathVariable String code) {
        return ResultInfo.ok(areaService.findAreaByCode(code));
    }
}
