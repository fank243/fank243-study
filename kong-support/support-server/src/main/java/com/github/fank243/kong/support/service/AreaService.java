/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.fank243.kong.support.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alicp.jetcache.anno.CachePenetrationProtect;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.Cached;
import com.github.fank243.kong.tool.area.Area;
import com.github.fank243.kong.tool.area.AreaConstants;
import com.github.fank243.kong.tool.area.AreaHelper;
import com.github.fank243.kong.core.constants.TimeConstants;
import com.github.fank243.kong.support.domain.AreaEntity;
import com.github.fank243.kong.support.repository.IAreaRepository;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * 国家行政区划表 服务类
 *
 * @author FanWeiJie
 * @since 2022-09-20 10:46:43
 */
@Slf4j
@Service
public class AreaService {

    @Resource
    private IAreaRepository areaRepository;

    /** 生成区域树 **/
    @Cached(name = "support:area:tree:", key = "#level", expire = TimeConstants.DAY_1)
    @CacheRefresh(refresh = TimeConstants.HOUR_1, stopRefreshAfterLastAccess = TimeConstants.HOUR_1)
    @CachePenetrationProtect
    public List<Area> generatorTree(int level) {
        // QueryWrapper queryWrapper = new QueryWrapper();
        // queryWrapper.where("(IF(area_code = '', 1, 0))");
        // List<AreaEntity> areaEntities = areaRepository.selectListByQuery(queryWrapper);
        List<AreaEntity> areaEntities = null;
        List<Area> areas = new ArrayList<>(34);
        // 省级
        Map<String, List<AreaEntity>> provinceMap =
            areaEntities.stream().collect(Collectors.groupingBy(AreaEntity::getProvinceCode));
        provinceMap.forEach((provinceCode, provinceList) -> {
            AreaEntity provinceEntity = provinceList.get(0);

            // 省级行政区
            Area provinceArea = new Area();
            provinceArea.setAreaCode(provinceCode);
            provinceArea.setAreaName(provinceEntity.getProvinceName());

            // 市级行政区
            List<Area> cityAreaList = new ArrayList<>(16);
            Map<String, List<AreaEntity>> cityMap =
                provinceList.stream().collect(Collectors.groupingBy(AreaEntity::getCityCode));
            cityMap.forEach((cityCode, cityList) -> {
                AreaEntity cityEntity = cityList.get(0);
                Area cityArea = new Area();
                cityArea.setAreaCode(cityCode);
                cityArea.setAreaName(cityEntity.getCityName());

                // 区县行政区
                List<Area> areaList = new ArrayList<>(16);
                if (level == 1) {
                    cityList.forEach(areaEntity -> {
                        if (StrUtil.isBlank(areaEntity.getAreaCode())) {
                            return;
                        }
                        Area area = new Area();
                        area.setAreaCode(areaEntity.getAreaCode());
                        area.setAreaName(areaEntity.getAreaName());
                        area.setChildren(new ArrayList<>(0));
                        areaList.add(area);
                    });
                    areaList.sort(Comparator.comparing(Area::getAreaCode));
                    cityArea.setChildren(areaList);

                    cityAreaList.add(cityArea);
                } else {
                    cityArea.setChildren(new ArrayList<>(0));
                    cityAreaList.add(cityArea);
                }
            });
            cityAreaList.sort(Comparator.comparing(Area::getAreaCode));
            provinceArea.setChildren(cityAreaList);

            areas.add(provinceArea);
        });

        areas.sort(Comparator.comparing(Area::getAreaCode));
        return areas;

    }

    /** 查询所有省级行政区划 **/
    @Cached(name = "support:area:", key = "'province'", expire = TimeConstants.DAY_1)
    @CachePenetrationProtect
    public List<Area> findProvinces() {
        // return areaRepository.findProvinces();
        return null;
    }

    /** 根据行政区划代码查询所辖行政区划 **/
    @Cached(name = "support:area:code:", key = "#code", expire = TimeConstants.DAY_1)
    @CacheRefresh(refresh = TimeConstants.HOUR_1, stopRefreshAfterLastAccess = TimeConstants.HOUR_1)
    public List<Area> findAreaByCode(String code) {
        AreaConstants.AreaCodeTypeEnum areaCodeType = AreaHelper.getAreaCodeType(code);
        if (AreaConstants.AreaCodeTypeEnum.PROVINCE.name().equalsIgnoreCase(areaCodeType.name())) {
            // return areaRepository.findCityByProvinceCode(code);
        } else if (AreaConstants.AreaCodeTypeEnum.CITY.name().equalsIgnoreCase(areaCodeType.name())) {
            // return areaRepository.findAreaByCityCode(code);
        }
        return new ArrayList<>(0);
    }

    /** 导入数据库 **/
    @Transactional(rollbackFor = Exception.class)
    public boolean importArea(List<Area> areaList) {
        long startTime = System.currentTimeMillis();

        // 清理历史记录
        // areaRepository.deleteByQuery(new QueryWrapper());

        List<AreaEntity> areaEntityList = new ArrayList<>();
        areaList.forEach(area -> {
            AreaConstants.AreaCodeTypeEnum areaCodeType = AreaHelper.getAreaCodeType(area.getAreaCode());
            AreaEntity areaEntity = new AreaEntity();
            if (AreaConstants.AreaCodeTypeEnum.PROVINCE.name().equalsIgnoreCase(areaCodeType.name())) {
                areaEntity.setProvinceCode(area.getAreaCode());
                areaEntity.setProvinceName(area.getAreaName());
            } else if (AreaConstants.AreaCodeTypeEnum.CITY.name().equalsIgnoreCase(areaCodeType.name())) {
                areaEntity.setCityCode(area.getAreaCode());
                areaEntity.setCityName(area.getAreaName());
            } else if (AreaConstants.AreaCodeTypeEnum.AREA.name().equalsIgnoreCase(areaCodeType.name())) {
                areaEntity.setAreaCode(area.getAreaCode());
                areaEntity.setAreaName(area.getAreaName());
            }
            areaEntityList.add(areaEntity);
        });

        // 填充省市级行政区
        areaEntityList.forEach(areaEntity -> {
            if (StrUtil.isNotBlank(areaEntity.getProvinceCode())) {
                return;
            }
            if (StrUtil.isNotBlank(areaEntity.getCityCode())) {
                String provinceCode = areaEntity.getCityCode().substring(0, 2) + AreaConstants.AREA_SUFFIX_PROVINCE;
                AreaEntity provinceArea =
                    areaEntityList.stream().filter(area -> area.getProvinceCode().equalsIgnoreCase(provinceCode))
                        .findFirst().orElse(new AreaEntity());
                areaEntity.setProvinceCode(provinceArea.getProvinceCode());
                areaEntity.setProvinceName(provinceArea.getProvinceName());
            }
            if (StrUtil.isNotBlank(areaEntity.getAreaCode())) {
                String provinceCode = areaEntity.getAreaCode().substring(0, 2) + AreaConstants.AREA_SUFFIX_PROVINCE;
                AreaEntity provinceArea =
                    areaEntityList.stream().filter(area -> area.getProvinceCode().equalsIgnoreCase(provinceCode))
                        .findFirst().orElse(new AreaEntity());
                areaEntity.setProvinceCode(provinceArea.getProvinceCode());
                areaEntity.setProvinceName(provinceArea.getProvinceName());

                String cityCode = areaEntity.getAreaCode().substring(0, 4) + AreaConstants.AREA_SUFFIX_CITY;
                AreaEntity cityArea = areaEntityList.stream()
                    .filter(
                        area -> StrUtil.isNotBlank(area.getCityCode()) && area.getCityCode().equalsIgnoreCase(cityCode))
                    .findFirst().orElse(new AreaEntity());
                areaEntity.setCityCode(cityArea.getCityCode());
                areaEntity.setCityName(cityArea.getCityName());
            }
        });

        // 省管县直接赋值为区县级行政区划
        areaEntityList.forEach(areaEntity -> {
            if (AreaConstants.AREA_SPECIAL.contains(areaEntity.getProvinceCode())) {
                areaEntity.setCityCode(areaEntity.getProvinceCode());
                areaEntity.setCityName(areaEntity.getProvinceName());
                return;
            }
            if (StrUtil.isBlank(areaEntity.getCityCode())) {
                areaEntity.setCityCode(areaEntity.getAreaCode());
                areaEntity.setCityName(areaEntity.getAreaName());
            }
        });

        List<AreaEntity> newAreaEntityList =
            areaEntityList.stream().filter(areaEntity -> StrUtil.isNotBlank(areaEntity.getAreaCode())
                || AreaConstants.AREA_TGA.contains(areaEntity.getProvinceCode())).collect(Collectors.toList());

        areaRepository.saveAll(newAreaEntityList);

        if (log.isDebugEnabled()) {
            log.debug("导入总耗时：{}秒", (System.currentTimeMillis() - startTime) / 1000);
        }

        return true;
    }
}
