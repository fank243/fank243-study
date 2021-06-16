package com.fank243.study.ds.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义填充事件，新增或者插入SQL更新相关日期字段
 * 
 * @author FanWeiJie
 * @date 2021-06-07 00:35:33
 */
@Component
public class MpMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createdDate", Date.class, new Date());
        this.strictInsertFill(metaObject, "lastModifiedDate", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "lastModifiedDate", Date.class, new Date());
    }
}