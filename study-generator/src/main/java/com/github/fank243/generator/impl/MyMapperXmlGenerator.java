package com.github.fank243.generator.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.github.fank243.generator.utils.GeneratorUtils;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.MapperXmlConfig;
import com.mybatisflex.codegen.config.PackageConfig;
import com.mybatisflex.codegen.constant.TemplateConst;
import com.mybatisflex.codegen.entity.Table;
import com.mybatisflex.codegen.generator.impl.MapperXmlGenerator;

/**
 * 自定义MapperXML生成器
 * 
 * @author FanWeiJie
 * @date 2023-09-17 12:09
 */
public class MyMapperXmlGenerator extends MapperXmlGenerator {

    private String templatePath;

    public MyMapperXmlGenerator() {
        this(TemplateConst.MAPPER_XML);
    }

    public MyMapperXmlGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public void generate(Table table, GlobalConfig globalConfig) {
        if (!globalConfig.isMapperXmlGenerateEnable()) {
            return;
        }

        PackageConfig packageConfig = globalConfig.getPackageConfig();
        MapperXmlConfig mapperXmlConfig = globalConfig.getMapperXmlConfig();

        File mapperXmlFile = new File(packageConfig.getMapperXmlPath() + "/" + table.buildMapperXmlFileName() + ".xml");

        if (mapperXmlFile.exists() && !mapperXmlConfig.isOverwriteEnable()) {
            return;
        }

        // 字段转换
        Map<String, Object> columnMap = GeneratorUtils.convertColumn(table);

        Map<String, Object> params = new HashMap<>(columnMap);
        params.put("table", table);
        params.put("packageConfig", packageConfig);

        globalConfig.getTemplateConfig().getTemplate().generate(params, templatePath, mapperXmlFile);

        System.out.println("MapperXML ---> " + mapperXmlFile);
    }

    @Override
    public String getTemplatePath() {
        return templatePath;
    }

    @Override
    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }
}
