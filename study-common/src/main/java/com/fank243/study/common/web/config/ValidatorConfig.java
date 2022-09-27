//package com.fank243.study.common.web.config;
//
//import org.hibernate.validator.HibernateValidator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import jakarta.validation.Validation;
//import jakarta.validation.Validator;
//import jakarta.validation.ValidatorFactory;
//
///**
// * HibernateValidator 验证器配置
// *
// * @author FanWeiJie
// * @since 2022-09-27 09:48:05
// */
//@Configuration
//public class ValidatorConfig {
//
//    @Bean
//    public Validator validator() {
//        // failFast：true 快速失败返回模式 false 普通模式
//        ValidatorFactory validatorFactory =
//            Validation.byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory();
//        return validatorFactory.getValidator();
//    }
//}