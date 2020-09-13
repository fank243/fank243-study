//package com.fank243.springboot.admin.listener;
//
//import com.fank243.springboot.admin.service.SysConfigService;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//
///**
// * 系统启动数据初始化
// *
// * @author FanWeiJie
// * @date 2019-11-04 11:37:16
// */
//@WebListener
//public class BootstrapListener implements ServletContextListener {
//
//    @Resource
//    private SysConfigService sysConfigService;
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        // 刷新系统配置缓存
//        sysConfigService.refreshCache();
//    }
//}
