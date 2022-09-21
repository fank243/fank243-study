package com.fank243.study.system.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fank243.study.api.constants.AreaConstants;
import com.fank243.study.api.system.vo.Area;
import com.fank243.study.api.utils.AreaUtils;
import com.fank243.study.common.constants.TimeConstant;
import com.fank243.study.system.dao.IAreaDao;
import com.fank243.study.system.entity.AreaEntity;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 国家行政区划表 服务类
 *
 * @author FanWeiJie
 * @since 2022-09-20 10:46:43
 */
@Slf4j
@Service
public class AreaService extends ServiceImpl<IAreaDao, AreaEntity> {

    @Resource
    private IAreaDao areaDao;

    /** 生成区域树 **/
    @Cached(name = "sys.area.tree:", key = "'areaTree'", expire = TimeConstant.DAY_1)
    @CacheRefresh(refresh = TimeConstant.HOUR_1, stopRefreshAfterLastAccess = TimeConstant.DAY_1)
    public List<Area> generatorTree(int level) {
        QueryWrapper<AreaEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("province_code");
        List<AreaEntity> areaEntities = areaDao.selectList(queryWrapper);

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

            // 台港澳地区
            if (AreaConstants.AREA_TGA.contains(provinceCode)) {
                areas.add(provinceArea);
                return;
            }
            // 直辖市
            if ((level == 0 && AreaConstants.AREA_DIRECTLY.contains(provinceCode))) {
                areas.add(provinceArea);
                return;
            }

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
                        Area area = new Area();
                        area.setAreaCode(areaEntity.getAreaCode());
                        area.setAreaName(areaEntity.getAreaName());
                        areaList.add(area);
                    });
                    areaList.sort(Comparator.comparing(Area::getAreaCode));
                    cityArea.setChildren(areaList);

                    if (AreaConstants.AREA_DIRECTLY.contains(provinceCode)) {
                        cityAreaList.clear();
                        cityAreaList.addAll(areaList);
                    } else {
                        cityAreaList.add(cityArea);
                    }
                } else {
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
    @Cached(name = "sys.area.provinces:", key = "'areaProvinces'", expire = TimeConstant.DAY_1)
    public List<Area> findProvinces() {
        return areaDao.findProvinces();
    }

    /** 根据行政区划代码查询所辖行政区划 **/
    @Cached(name = "sys.area.areas:", key = "#code", expire = TimeConstant.DAY_1)
    @CacheRefresh(refresh = TimeConstant.HOUR_1, stopRefreshAfterLastAccess = TimeConstant.DAY_1)
    public List<Area> findAreaByCode(String code) {
        AreaConstants.AreaCodeTypeEnum areaCodeType = AreaUtils.getAreaCodeType(code);
        if (AreaConstants.AreaCodeTypeEnum.PROVINCE.name().equalsIgnoreCase(areaCodeType.name())) {
            return areaDao.findCityByProvinceCode(code);
        } else if (AreaConstants.AreaCodeTypeEnum.CITY.name().equalsIgnoreCase(areaCodeType.name())) {
            return areaDao.findAreaByCityCode(code);
        }
        return new ArrayList<>(0);
    }

    /** 导入数据库 **/
    @Transactional(rollbackFor = Exception.class)
    public boolean importArea(List<Area> areaList) {
        long startTime = System.currentTimeMillis();

        // 清理历史记录
        areaDao.delete(new QueryWrapper<>());

        List<AreaEntity> areaEntityList = new ArrayList<>();
        areaList.forEach(area -> {
            AreaConstants.AreaCodeTypeEnum areaCodeType = AreaUtils.getAreaCodeType(area.getAreaCode());
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

        // 填充省市级行政区，直辖市、省管县无法添加市级行政区划
        areaEntityList.forEach(areaEntity -> {
            if (StrUtil.isNotBlank(areaEntity.getProvinceCode())) {
                return;
            }
            if (StrUtil.isNotBlank(areaEntity.getCityCode())) {
                String provinceCode = areaEntity.getCityCode().substring(0, 2) + "0000";
                AreaEntity provinceArea =
                    areaEntityList.stream().filter(area -> area.getProvinceCode().equalsIgnoreCase(provinceCode))
                        .findFirst().orElse(new AreaEntity());
                areaEntity.setProvinceCode(provinceArea.getProvinceCode());
                areaEntity.setProvinceName(provinceArea.getProvinceName());
            }
            if (StrUtil.isNotBlank(areaEntity.getAreaCode())) {
                String provinceCode = areaEntity.getAreaCode().substring(0, 2) + "0000";
                AreaEntity provinceArea =
                    areaEntityList.stream().filter(area -> area.getProvinceCode().equalsIgnoreCase(provinceCode))
                        .findFirst().orElse(new AreaEntity());
                areaEntity.setProvinceCode(provinceArea.getProvinceCode());
                areaEntity.setProvinceName(provinceArea.getProvinceName());

                String cityCode = areaEntity.getAreaCode().substring(0, 4) + "00";
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
            if (AreaConstants.AREA_TGA.contains(areaEntity.getProvinceCode())
                || AreaConstants.AREA_DIRECTLY.contains(areaEntity.getProvinceCode())) {
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

        boolean isOk = saveBatch(newAreaEntityList);

        if (log.isDebugEnabled()) {
            log.debug("导入总耗时：{}秒", (System.currentTimeMillis() - startTime) / 1000);
        }

        return isOk;
    }
}
