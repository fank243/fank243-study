package com.fank243.study.generator;

import cn.hutool.setting.dialect.Props;
import cn.hutool.setting.dialect.PropsUtil;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 生成代码
 *
 * @author FanWeiJie
 * @since 2021-08-28 17:17:31
 */
public class GeneratorCode {

    private static final String jdbcUrl;

    private static final String username;

    private static final String password;

    static {
        Props props = PropsUtil.get("application");
        jdbcUrl = props.getProperty("jdbc.url");
        username = props.getProperty("jdbc.username");
        password = props.getProperty("jdbc.password");
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

    /**
     * 自定义生成模板
     * 
     * @param outDir 输出目录
     * @return 模板文件
     */
    private static List<FileOutConfig> buildTemplate(String outDir) {
        List<FileOutConfig> fileList = new ArrayList<>();

        fileList.add(new FileOutConfig("/ftl/api.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return outDir + "/api/" + "I" + tableInfo.getEntityName() + "Api" + StringPool.DOT_JAVA;
            }
        });
        fileList.add(new FileOutConfig("/ftl/client.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return outDir + "/client/" + "I" + tableInfo.getEntityName() + "Service" + StringPool.DOT_JAVA;
            }
        });
        fileList.add(new FileOutConfig("/ftl/controller.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return outDir + "/controller/" + tableInfo.getEntityName() + "Controller" + StringPool.DOT_JAVA;
            }
        });
        fileList.add(new FileOutConfig("/ftl/service.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return outDir + "/service/" + tableInfo.getEntityName() + "Service" + StringPool.DOT_JAVA;
            }
        });
        fileList.add(new FileOutConfig("/ftl/mapper.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return outDir + "/dao/" + "I" + tableInfo.getEntityName() + "Dao" + StringPool.DOT_JAVA;
            }
        });
        fileList.add(new FileOutConfig("/ftl/entity.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return outDir + "/entity/" + tableInfo.getEntityName() + "Entity" + StringPool.DOT_JAVA;
            }
        });
        fileList.add(new FileOutConfig("/ftl/dto.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return outDir + "/dto/" + tableInfo.getEntityName() + "DTO" + StringPool.DOT_JAVA;
            }
        });
        fileList.add(new FileOutConfig("/ftl/vo.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return outDir + "/vo/" + tableInfo.getEntityName() + "VO" + StringPool.DOT_JAVA;
            }
        });

        return fileList;
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String outDir = System.getProperty("user.home") + "/Desktop/code/";
        globalConfig.setOutputDir(outDir);
        globalConfig.setAuthor("FanWeiJie");
        globalConfig.setOpen(false);
        // 覆盖
        globalConfig.setFileOverride(true);
        globalConfig.setSwagger2(false);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setServiceName("%sService");
        globalConfig.setMapperName("I%sDao");
        autoGenerator.setGlobalConfig(globalConfig);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(jdbcUrl);
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername(username);
        dataSourceConfig.setPassword(password);
        dataSourceConfig.setSchemaName("");
        autoGenerator.setDataSource(dataSourceConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(scanner("模块名"));
        packageConfig.setParent("com.fank243.study");
        packageConfig.setMapper("dao");
        autoGenerator.setPackageInfo(packageConfig);

        // 自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {}
        };

        injectionConfig.setFileOutConfigList(buildTemplate(outDir));
        autoGenerator.setCfg(injectionConfig);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setController(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setMapper(null);
        templateConfig.setEntity(null);
        autoGenerator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setSuperEntityClass("com.fank243.study.core.base.BaseEntity");
        // 启用lombok
        strategyConfig.setEntityLombokModel(true);
        // @RestController
        strategyConfig.setRestControllerStyle(true);
        // 公共父类
        strategyConfig.setSuperControllerClass("com.fank243.study.core.base.BaseController");
        // 写于父类中的公共字段
        strategyConfig.setSuperEntityColumns("id", "created_by", "created_date", "last_modified_by",
            "last_modified_date");
        strategyConfig.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategyConfig.setControllerMappingHyphenStyle(true);
        // 表名前缀
        strategyConfig.setTablePrefix("tb_");
        strategyConfig.setLogicDeleteFieldName("is_deleted");
        strategyConfig.setEntitySerialVersionUID(false);

        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());

        autoGenerator.execute();
    }

}