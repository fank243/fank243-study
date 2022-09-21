package com.fank243.study.system.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fank243.study.api.system.api.IAreaApi;
import com.fank243.study.api.system.vo.Area;
import com.fank243.study.api.utils.AreaUtils;
import com.fank243.study.common.utils.ResultInfo;
import com.fank243.study.core.annotation.RepeatSubmit;
import com.fank243.study.core.base.BaseController;
import com.fank243.study.system.service.AreaService;

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
@RestController
public class AreaController extends BaseController implements IAreaApi {

    @Resource
    private AreaService areaService;

    @RepeatSubmit
    @Override
    public ResultInfo<?> importArea(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return ResultInfo.fail("请上传文件");
        }

        List<Area> areaList;
        try {
            String fileType = FileTypeUtil.getType(multipartFile.getInputStream(), multipartFile.getOriginalFilename());
            if (!"txt".equalsIgnoreCase(fileType)) {
                return ResultInfo.fail("请上传TXT文件");
            }
            List<String> list = new ArrayList<>(4000);
            IoUtil.readLines(multipartFile.getInputStream(), StandardCharsets.UTF_8, list);
            if (CollUtil.isEmpty(list)) {
                return ResultInfo.fail("文件内容不能为空");
            }
            areaList = AreaUtils.parse(list);
        } catch (IOException e) {
            log.error("导入异常：{}", e.getMessage(), e);
            return ResultInfo.error("导入失败", e.toString());
        }

        boolean isOk = areaService.importArea(areaList);

        return isOk ? ResultInfo.ok().message("导入成功") : ResultInfo.fail("导入失败");
    }

    @Override
    public ResultInfo<List<Area>> tree(Integer level) {
        level = Convert.toInt(level, 0);
        level = level == 1 ? 1 : 0;
        return ResultInfo.ok(areaService.generatorTree(level));
    }

    @Override
    public ResultInfo<List<Area>> getProvinces() {
        return ResultInfo.ok(areaService.findProvinces());
    }

    @Override
    public ResultInfo<List<Area>> getAreaByCode(String code) {
        return ResultInfo.ok(areaService.findAreaByCode(code));
    }
}
