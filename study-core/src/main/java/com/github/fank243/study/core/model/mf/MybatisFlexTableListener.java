package com.github.fank243.study.core.model.mf;

import java.util.Date;

import com.mybatisflex.annotation.InsertListener;
import com.mybatisflex.annotation.UpdateListener;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ReflectUtil;

/**
 * 数据表审计
 * 
 * @author FanWeiJie
 * @date 2023-09-16 15:03
 */
public class MybatisFlexTableListener implements InsertListener, UpdateListener {

    @Override
    public void onInsert(Object entity) {
        fillSqlAudit(true, entity);
    }

    @Override
    public void onUpdate(Object entity) {
        fillSqlAudit(false, entity);
    }

    private void fillSqlAudit(boolean isInsert, Object entity) {
        String userId = "";
        if (StpUtil.isLogin()) {
            userId = StpUtil.getLoginIdAsString();
        }
        Class<?> clazz = entity.getClass();
        Date now = new Date();
        if (isInsert) {
            if (ReflectUtil.hasField(clazz, "createdBy")) {
                ReflectUtil.setFieldValue(entity, "createdBy", userId);
            }
            if (ReflectUtil.hasField(clazz, "createdDate")) {
                ReflectUtil.setFieldValue(entity, "createdDate", now);
            }
        }
        if (ReflectUtil.hasField(clazz, "lastModifiedBy")) {
            ReflectUtil.setFieldValue(entity, "lastModifiedBy", userId);
        }
        if (ReflectUtil.hasField(clazz, "lastModifiedDate")) {
            ReflectUtil.setFieldValue(entity, "lastModifiedDate", now);
        }
    }

}
