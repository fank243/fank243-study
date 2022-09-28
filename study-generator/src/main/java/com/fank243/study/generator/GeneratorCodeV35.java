package com.fank243.study.generator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import cn.hutool.setting.dialect.Props;
import cn.hutool.setting.dialect.PropsUtil;

/**
 * 生成代码
 *
 * @author FanWeiJie
 * @since 2021-08-28 17:17:31
 */
public class GeneratorCodeV35 {

    private static final String JDBC_URL;

    private static final String USERNAME;

    private static final String PASSWORD;

    static {
        Props props = PropsUtil.get("application");
        JDBC_URL = props.getProperty("jdbc.url");
        USERNAME = props.getProperty("jdbc.username");
        PASSWORD = props.getProperty("jdbc.password");
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (ipt != null && !"".equalsIgnoreCase(ipt)) {
                return ipt;
            }
        }
        System.out.println("请输入正确的" + tip + "！");
        return "";
    }

    public static void main(String[] args) {
        // 全局配置
        // @formatter:off
        GlobalConfig globalConfig = new GlobalConfig.Builder()
            .outputDir(System.getProperty("user.home") + "/Desktop/study")
            .author("FanWeiJie")
            .dateType(DateType.ONLY_DATE)
            .disableOpenDir()
            .commentDate("yyyy-MM-dd HH:mm:ss")
            .build();
        // @formatter:on

        // 数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(JDBC_URL, USERNAME, PASSWORD).build();

        // 包配置
        // @formatter:off
        PackageConfig packageConfig = new PackageConfig.Builder("com.fank243.study", scanner("模块名"))
                .controller("controller")
                .service("service")
                .mapper("dao")
                .entity("entity")
                .build();
        // @formatter:on

        // 模板配置
        // @formatter:off
        TemplateConfig templateConfig = new TemplateConfig.Builder()
                .disable(TemplateType.CONTROLLER)
                .disable(TemplateType.SERVICE)
                .disable(TemplateType.SERVICEIMPL)
                .disable(TemplateType.MAPPER)
                .disable(TemplateType.ENTITY)
                .disable(TemplateType.XML)
                .build();
        // @formatter:on

        // 策略配置
        // @formatter:off
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .enableCapitalMode()
                .addInclude(scanner("表名，多个使用英文逗号分割"))
                .addTablePrefix("tb_")
                .entityBuilder().disableSerialVersionUID()
                .controllerBuilder().enableRestStyle().superClass("com.fank243.study.common.domain.base.BaseController")
                .serviceBuilder().formatServiceFileName("%sService")
                .mapperBuilder().formatMapperFileName("%sDao")
                .entityBuilder().enableLombok().naming(NamingStrategy.underline_to_camel)
                .superClass("com.fank243.study.ds.domain.base.BaseEntity")
                .addSuperEntityColumns("id","created_by","created_date","last_modified_by","last_modified_date")
                .build();
        // @formatter:on

        // 自定义配置
        Map<String, String> customFile = new HashMap<>(3);
        InjectionConfig injectionConfig = new InjectionConfig.Builder().beforeOutputFile((tableInfo, objectMap) -> {
            String name = tableInfo.getEntityName().replaceAll("Entity", "");
            customFile.put(name + "Controller.java", "ftl35/controller.java.ftl");
            customFile.put(name + "Service.java", "ftl35/service.java.ftl");
            customFile.put("I" + name + "Dao.java", "ftl35/mapper.java.ftl");
            customFile.put(name + "Entity.java", "ftl35/entity.java.ftl");
            customFile.put("I" + name + "Service.java", "ftl35/client.java.ftl");
            customFile.put(name + "DTO.java", "ftl35/dto.java.ftl");
            customFile.put(name + "VO.java", "ftl35/vo.java.ftl");
        }).customFile(customFile).fileOverride().build();

        // ConfigBuilder
        ConfigBuilder configBuilder = new ConfigBuilder(packageConfig, dataSourceConfig, strategyConfig, templateConfig,
            globalConfig, injectionConfig);

        AutoGenerator autoGenerator = new AutoGenerator(dataSourceConfig).config(configBuilder);
        autoGenerator.execute(new FreemarkerTemplateEngine());

    }

}
