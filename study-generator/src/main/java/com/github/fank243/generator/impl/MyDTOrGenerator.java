package com.github.fank243.generator.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.mybatisflex.codegen.config.EntityConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.PackageConfig;
import com.mybatisflex.codegen.entity.Table;
import com.mybatisflex.codegen.generator.IGenerator;

/**
 * 自定义MapperXML生成器
 *
 * @author FanWeiJie
 * @date 2023-09-17 12:09
 */
public class MyDTOrGenerator implements IGenerator {

    private String templatePath;

    public MyDTOrGenerator() {
        this("/templates/enjoy/dto.tpl");
    }

    public MyDTOrGenerator(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public void generate(Table table, GlobalConfig globalConfig) {

        if (!globalConfig.isEntityGenerateEnable()) {
            return;
        }

        PackageConfig packageConfig = globalConfig.getPackageConfig();
        EntityConfig entityConfig = globalConfig.getEntityConfig();

        String entityPackagePath = packageConfig.getEntityPackage().replace(".", "/");

        entityPackagePath = entityPackagePath.replaceAll("domain", "dto");
        String dtoName = table.getEntityJavaFileName() + "DTO";

        File entityJavaFile = new File(packageConfig.getSourceDir(), entityPackagePath + "/" + dtoName + ".java");

        if (entityJavaFile.exists() && !entityConfig.isOverwriteEnable()) {
            return;
        }
        // 排除忽略列
        if (globalConfig.getStrategyConfig().getIgnoreColumns() != null) {
            table.getColumns().removeIf(
                    column -> globalConfig.getStrategyConfig().getIgnoreColumns().contains(column.getName().toLowerCase()));
        }

        Map<String, Object> params = new HashMap<>(4);
        params.put("table", table);
        params.put("entityConfig", entityConfig);
        params.put("packageConfig", packageConfig);
        params.put("javadocConfig", globalConfig.getJavadocConfig());

        globalConfig.getTemplateConfig().getTemplate().generate(params, templatePath, entityJavaFile);

        System.out.println("DTO ---> " + entityJavaFile);
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
