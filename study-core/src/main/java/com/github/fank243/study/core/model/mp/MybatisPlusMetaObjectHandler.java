// package com.github.fank243.study.core.model.mp;
//
// import java.nio.charset.Charset;
// import java.util.Date;
//
// import org.apache.ibatis.reflection.MetaObject;
// import org.springframework.util.ClassUtils;
//
// import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//
// import cn.dev33.satoken.stp.StpUtil;
// import cn.hutool.core.util.StrUtil;
// import lombok.extern.slf4j.Slf4j;
//
/// **
// * 自定义填充事件，新增或者插入SQL更新相关日期字段
// *
// * @author FanWeiJie
// * @since 2021-06-07 00:35:33
// */
// @Slf4j
// public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {
//
// /**
// * 填充值，先判断是否有手动设置，优先手动设置的值，例如：job必须手动设置
// *
// * @param fieldName 属性名
// * @param fieldVal 属性值
// * @param metaObject MetaObject
// * @param isCover 是否覆盖原有值,避免更新操作手动入参
// */
// private static void fillValIfNullByName(String fieldName, Object fieldVal, MetaObject metaObject, boolean isCover) {
// // 1. 没有 set 方法
// if (!metaObject.hasSetter(fieldName)) {
// return;
// }
// // 2. 如果用户有手动设置的值
// Object userSetValue = metaObject.getValue(fieldName);
// String setValueStr = StrUtil.str(userSetValue, Charset.defaultCharset());
// if (StrUtil.isNotBlank(setValueStr) && !isCover) {
// return;
// }
// // 3. field 类型相同时设置
// Class<?> getterType = metaObject.getGetterType(fieldName);
// if (ClassUtils.isAssignableValue(getterType, fieldVal)) {
// metaObject.setValue(fieldName, fieldVal);
// }
// }
//
// @Override
// public void insertFill(MetaObject metaObject) {
// String userId = StpUtil.isLogin() ? StpUtil.getLoginIdAsString() : "";
// log.debug("mybatis plus start insert fill，当前登录用户ID：{}", userId);
//
// Date now = new Date();
//
// fillValIfNullByName("createdDate", now, metaObject, false);
// fillValIfNullByName("lastModifiedDate", now, metaObject, false);
// fillValIfNullByName("createdBy", userId, metaObject, false);
// fillValIfNullByName("lastModifiedBy", userId, metaObject, false);
// }
//
// @Override
// public void updateFill(MetaObject metaObject) {
// String userId = StpUtil.isLogin() ? StpUtil.getLoginIdAsString() : "";
// log.debug("mybatis plus start update fill，当前登录用户ID：{}", userId);
//
// fillValIfNullByName("lastModifiedDate", new Date(), metaObject, true);
// fillValIfNullByName("lastModifiedBy", userId, metaObject, true);
// }
//
// }