//package com.fank243.study.generator;
//
//import java.util.Scanner;
//
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
//
//import cn.hutool.setting.dialect.Props;
//import cn.hutool.setting.dialect.PropsUtil;
//
///**
// * 生成代码
// *
// * @author FanWeiJie
// * @since 2021-08-28 17:17:31
// */
//public class GeneratorCodeV35 {
//
//    private static final String JDBC_URL;
//
//    private static final String USERNAME;
//
//    private static final String PASSWORD;
//
//    static {
//        Props props = PropsUtil.get("application");
//        JDBC_URL = props.getProperty("jdbc.url");
//        USERNAME = props.getProperty("jdbc.username");
//        PASSWORD = props.getProperty("jdbc.password");
//    }
//
//    /**
//     * <p>
//     * 读取控制台内容
//     * </p>
//     */
//    public static String scanner(String tip) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("请输入" + tip + "：");
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (ipt != null && !"".equalsIgnoreCase(ipt)) {
//                return ipt;
//            }
//        }
//        System.out.println("请输入正确的" + tip + "！");
//        return "";
//    }
//
//    public static void main(String[] args) {
//        // 全局配置
//        // @formatter:off
//        GlobalConfig globalConfig = new GlobalConfig.Builder()
//            .outputDir(System.getProperty("user.home") + "/Desktop/study")
//            .author("FanWeiJie")
//            .dateType(DateType.ONLY_DATE)
//            .disableOpenDir()
//            .commentDate("yyyy-MM-dd HH:mm:ss")
//            .build();
//        // @formatter:on
//
//        // 数据源
//        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(JDBC_URL, USERNAME, PASSWORD).build();
//
//        // 包配置
//        // @formatter:off
//        PackageConfig packageConfig = new PackageConfig.Builder("com.fank243.study", scanner("模块名"))
//                .controller("controller")
//                .service("service")
//                .mapper("dao")
//                .entity("entity")
//                .build();
//        // @formatter:on
//
//        // 模板配置
//        // @formatter:off
//        TemplateConfig templateConfig = new TemplateConfig.Builder()
//                .controller("ftl35/controller.java")
//                .service("ftl35/service.java")
//                .mapper("ftl35/mapper.java")
//                .entity("ftl35/entity.java")
//                .disable(TemplateType.SERVICEIMPL)
//                .disable(TemplateType.XML)
//                .build();
//        // @formatter:on
//
//        // 策略配置
//        // @formatter:off
//        StrategyConfig strategyConfig = new StrategyConfig.Builder()
//                .enableCapitalMode()
//                .addInclude(scanner("表名，多个使用英文逗号分割"))
//                .addTablePrefix("tb_")
//                .controllerBuilder().enableRestStyle().superClass("com.fank243.study.core.base.BaseController")
//                .serviceBuilder().formatServiceFileName("%sService")
//                .mapperBuilder().formatMapperFileName("%sDao")
//                .entityBuilder().enableLombok().formatFileName("%sEntity").naming(NamingStrategy.underline_to_camel)
//                .superClass("com.fank243.study.core.base.BaseEntity")
//                .addSuperEntityColumns("id","created_by","created_date","last_modified_by","last_modified_date")
//                .build();
//        // @formatter:on
//
//        // ConfigBuilder
//        ConfigBuilder configBuilder =
//            new ConfigBuilder(packageConfig, dataSourceConfig, strategyConfig, templateConfig, globalConfig, null);
//
//        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig).config(configBuilder);
//        autoGenerator.execute(new FreemarkerTemplateEngine());
//    }
//
//}
