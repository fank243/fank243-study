package com.fank243.study.ds.config;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义填充事件，新增或者插入SQL更新相关日期字段
 * 
 * @author FanWeiJie
 * @since 2021-06-07 00:35:33
 */
@Component
public class MpMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        String userId = StpUtil.getLoginId("");
        this.strictInsertFill(metaObject, "createdDate", Date.class, new Date());
        this.strictInsertFill(metaObject, "createdBy", String.class, userId);
        this.strictInsertFill(metaObject, "lastModifiedDate", Date.class, new Date());
        this.strictInsertFill(metaObject, "lastModifiedBy", String.class, userId);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String userId = StpUtil.getLoginId("");
        this.strictUpdateFill(metaObject, "lastModifiedDate", Date.class, new Date());
        this.strictInsertFill(metaObject, "lastModifiedBy", String.class, userId);
    }
}