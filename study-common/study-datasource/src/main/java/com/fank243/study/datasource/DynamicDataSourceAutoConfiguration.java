//package com.fank243.study.datasource;
//
//import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
//import com.fank243.study.datasource.config.JdbcDynamicDataSourceProvider;
//import org.jasypt.encryption.StringEncryptor;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.baomidou.dynamic.datasource.processor.DsProcessor;
//import com.fank243.study.datasource.config.DataSourceProperties;
//import com.fank243.study.datasource.config.LastParamDsProcessor;
//
///**
// * 动态数据源统一配置
// *
// * @author FanWeiJie
// * @since 2022-09-30 21:36:25
// */
//@Configuration(proxyBeanMethods = false)
//@AutoConfigureAfter(DataSourceAutoConfiguration.class)
//@EnableConfigurationProperties(DataSourceProperties.class)
//public class DynamicDataSourceAutoConfiguration {
//
//    @Bean
//    public DynamicDataSourceProvider dynamicDataSourceProvider(StringEncryptor stringEncryptor,
//        DataSourceProperties properties) {
//        return new JdbcDynamicDataSourceProvider(stringEncryptor, properties);
//    }
//
//    @Bean
//    public DsProcessor dsProcessor() {
//        return new LastParamDsProcessor();
//    }
//
//}
