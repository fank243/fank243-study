package com.github.fank243.generator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.github.fank243.generator.domain.MyTable;
import com.github.fank243.generator.impl.MyDTOrGenerator;
import com.github.fank243.generator.impl.MyMapperGenerator;
import com.github.fank243.generator.impl.MyMapperXmlGenerator;
import com.github.fank243.generator.utils.DBUtils;
import com.github.fank243.kong.core.base.BaseEntity;
import com.github.fank243.kong.core.constants.Constants;
import com.github.fank243.kong.core.model.mf.MybatisFlexTableListener;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.ColumnConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.TableConfig;
import com.mybatisflex.codegen.constant.GenTypeConst;
import com.mybatisflex.codegen.generator.GeneratorFactory;
import com.mybatisflex.core.keygen.KeyGenerators;
import com.mybatisflex.core.mask.Masks;
import com.zaxxer.hikari.HikariDataSource;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

/**
 * Mybatis Flex 代码生成器
 *
 * @author FanWeiJie
 * @date 2023/09/16 19:37
 */
public class GeneratorApp {

    public static void main(String[] args) {
        // 配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(DBUtils.JDBC_URL);
        dataSource.setUsername(DBUtils.USERNAME);
        dataSource.setPassword(DBUtils.PASSWORD);

        GeneratorFactory.registerGenerator(GenTypeConst.MAPPER, new MyMapperGenerator());
        GeneratorFactory.registerGenerator(GenTypeConst.MAPPER_XML, new MyMapperXmlGenerator());
        GeneratorFactory.registerGenerator("dto", new MyDTOrGenerator());
        for (MyTable myTable : buildTable()) {
            GlobalConfig globalConfig = createGlobalConfigUseStyle(myTable);
            Generator generator = new Generator(dataSource, globalConfig);
            // 生成代码
            generator.generate();
        }
    }

    public static List<MyTable> buildTable() {
        List<MyTable> tableList = DBUtils.executeSql(
            "select TABLE_NAME,COLUMN_NAME,COLUMN_KEY from information_schema.COLUMNS where TABLE_SCHEMA = (select database())");
        if (CollUtil.isEmpty(tableList)) {
            throw new RuntimeException("数据库连接异常");
        }

        for (MyTable table : tableList) {
            String tableName = table.getTableName();
            if (StrUtil.isEmpty(tableName) || StrUtil.equalsAny("tb_sys_role", "tb_role_perm")) {
                continue;
            }

            for (Map.Entry<String, String[]> entry : MyTable.TABLES_GROUP.entrySet()) {
                String modelName = entry.getKey();
                Optional<String> optional =
                    Arrays.stream(entry.getValue()).filter(s -> StrUtil.equals(tableName, s)).findFirst();
                if (optional.isPresent()) {
                    table.setModelName(modelName);
                }
            }
        }

        return tableList.stream()
            .filter(myTable -> StrUtil.isAllNotEmpty(myTable.getTableName(), myTable.getModelName()))
            .collect(Collectors.toList());
    }

    public static GlobalConfig createGlobalConfigUseStyle(MyTable myTable) {
        String srcDir = "F:/workspaces/fank243/fank243-study/study-generator/src/main/java";
        String basePackage = Constants.BASE_PACKAGE + "." + myTable.getModelName();

        // 创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor("FanWeiJie");
        globalConfig.setSince(DateUtil.now());

        // dir
        globalConfig.setSourceDir(srcDir);
        globalConfig.setMapperXmlPath(srcDir + "/com/github/fank243/study/" + myTable.getModelName() + "/mapper/xml");

        globalConfig.getPackageConfig().setBasePackage(basePackage);
        globalConfig.setEntityPackage(basePackage + ".domain");

        // 设置表前缀和只生成哪些表，setGenerateTable 未配置时，生成所有表
        globalConfig.getStrategyConfig().setTablePrefix("tb_").setGenerateTable(myTable.getTableName())
            .setLogicDeleteColumn("is_deleted")
            .setIgnoreColumns("created_by", "created_date", "last_modified_by", "last_modified_date");

        // Table
        globalConfig.setTableConfig(TableConfig.create().setInsertListenerClass(MybatisFlexTableListener.class)
            .setUpdateListenerClass(MybatisFlexTableListener.class));

        // Entity & lombok
        globalConfig.enableEntity().setWithLombok(true).setOverwriteEnable(true).setClassSuffix("Entity")
            .setSuperClass(BaseEntity.class);

        // Mapper
        globalConfig.enableMapper().setOverwriteEnable(true).setClassPrefix("I");

        // Mapper XML
        globalConfig.enableMapperXml().setOverwriteEnable(true);

        // Column Primary Enum
        for (MyTable.MyColumn column : myTable.getColumnList()) {
            if (column.isPrimary()) {
                globalConfig.getStrategyConfig().setColumnConfig(myTable.getTableName(),
                    ColumnConfig.builder().columnName(column.getColumnName()).build().setPrimaryKey(true)
                        .setKeyType(KeyType.Generator).setKeyValue(KeyGenerators.snowFlakeId));
            }
            if (StrUtil.isNotBlank(column.getEnumName())) {
                globalConfig.getStrategyConfig().setColumnConfig(myTable.getTableName(), ColumnConfig.builder()
                    .columnName(column.getColumnName()).build().setPropertyType(column.getEnumName()));
            }
        }
        globalConfig.getStrategyConfig().setColumnConfig(myTable.getTableName(),
            ColumnConfig.builder().columnName("password").maskType(Masks.PASSWORD).build());
        globalConfig.getStrategyConfig().setColumnConfig(myTable.getTableName(),
            ColumnConfig.builder().columnName("mobile").maskType(Masks.MOBILE).build());

        return globalConfig;
    }
}