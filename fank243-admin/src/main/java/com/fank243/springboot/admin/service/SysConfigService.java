package com.fank243.springboot.admin.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fank243.springboot.admin.repository.SysConfigRepository;
import com.fank243.springboot.common.utils.ResultInfo;
import com.fank243.springboot.common.utils.StrUtils;
import com.fank243.springboot.core.consts.RedisKey;
import com.fank243.springboot.core.entity.SysConfig;
import com.fank243.springboot.core.enums.SysUserEventType;
import com.fank243.springboot.admin.service.component.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 系统配置
 * 
 * @author FanWeiJie
 * @date 2020-03-08 17:14:25
 */
@Service
public class SysConfigService {

    @Resource
    private SysConfigRepository repository;
    @Resource
    private SysUserEventService sysUserEventService;
    @Resource
    private RedisService redisService;

    /** 刷新 Redis 缓存 **/
    public void refreshCache() {
        List<SysConfig> sysConfigList = repository.findAll();
        if (sysConfigList.isEmpty()) {
            return;
        }

        // 清理缓存
        redisService.del(RedisKey.SYS_CONFIG);

        // 添加缓存
        for (SysConfig sysConfig : sysConfigList) {
            redisService.hset(RedisKey.SYS_CONFIG, sysConfig.getSysKey(), sysConfig.getSysValue());
        }
    }

    /**
     * 修改配置
     * 
     * @param adminId 管理员ID
     * @param body 键值
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultInfo modify(Long adminId, String body) {
        if (StringUtils.isBlank(body)) {
            return ResultInfo.fail("请填写配置项");
        }

        JSONObject json = JSON.parseObject(body);
        int type = json.getInteger("type");
        json.remove("type");

        List<SysConfig> sysConfigList = new ArrayList<>();

        for (Map.Entry<String, Object> entry : json.entrySet()) {
            String key = entry.getKey();
            String value = StrUtils.strToStr(String.valueOf(entry.getValue()), "");

            SysConfig sysConfig = repository.findBySysKey(key);
            if (sysConfig == null) {
                continue;
            }
            sysConfig.setSysKey(key);
            sysConfig.setSysValue(value);
            sysConfigList.add(sysConfig);
        }

        List<SysConfig> resultList = repository.saveAll(sysConfigList);
        if (resultList.isEmpty()) {
            return ResultInfo.fail("修改站点配置失败");
        }

        // 刷新缓存
        if (type == 1) {
            refreshCache();
        }

        // 添加管理员事件
        sysUserEventService.addEvent(adminId, SysUserEventType.SITE_MNG, "修改站点配置");

        if (resultList.size() != sysConfigList.size()) {
            return ResultInfo.ok().message("部分配置修改成功");
        }

        return ResultInfo.ok().message("修改配置成功");
    }

    public List<SysConfig> findAll() {
        return repository.findAll();
    }
}
