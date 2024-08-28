package com.github.fank243.generator.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.github.fank243.generator.utils.GeneratorUtils;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.MapperConfig;
import com.mybatisflex.codegen.config.PackageConfig;
import com.mybatisflex.codegen.constant.TemplateConst;
import com.mybatisflex.codegen.entity.Table;
import com.mybatisflex.codegen.generator.impl.MapperGenerator;

/**
 * 自定义MapperXML生成器
 * 
 * @author FanWeiJie
 * @date 2023-09-17 12:09
 */
public class MyMapperGenerator extends MapperGenerator {

    private String templatePath;

    public MyMapperGenerator() {
        this(TemplateConst.MAPPER);
    }

    public MyMapperGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public void generate(Table table, GlobalConfig globalConfig) {
        if (!globalConfig.isMapperGenerateEnable()) {
            return;
        }

        PackageConfig packageConfig = globalConfig.getPackageConfig();
        MapperConfig mapperConfig = globalConfig.getMapperConfig();

        String mapperPackagePath = packageConfig.getMapperPackage().replace(".", "/");
        File mapperJavaFile =
            new File(packageConfig.getSourceDir(), mapperPackagePath + "/" + table.buildMapperClassName() + ".java");

        if (mapperJavaFile.exists() && !mapperConfig.isOverwriteEnable()) {
            return;
        }
		packageConfig.setEntityPackage(packageConfig.getEntityPackage().replace("domain", "dto"));

        // 字段转换
        Map<String, Object> columnMap = GeneratorUtils.convertColumn(table);

        Map<String, Object> params = new HashMap<>(columnMap);
        params.put("table", table);
		params.put("dtoName", table.buildEntityClassName().replace("Entity", "DTO"));
        params.put("mapperConfig", mapperConfig);
        params.put("packageConfig", packageConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());

        globalConfig.getTemplateConfig().getTemplate().generate(params, templatePath, mapperJavaFile);

        System.out.println("Mapper ---> " + mapperJavaFile);
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
