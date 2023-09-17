package com.github.fank243.generator.utils;

import java.util.HashMap;
import java.util.Map;

import com.mybatisflex.codegen.entity.Table;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;

/**
 * @author FanWeiJie
 * @date 2023-09-17 14:22
 */
public class GeneratorUtils {

    private static final Map<String, String> RAW_TYPE_MAP = new HashMap<>(5);
    static {
        RAW_TYPE_MAP.put("DATETIME", "TIMESTAMP");
        RAW_TYPE_MAP.put("TINYINT", "TINYINT");
        RAW_TYPE_MAP.put("TEXT", "VARCHAR");
        RAW_TYPE_MAP.put("LONGTEXT", "LONGVARCHAR");
        RAW_TYPE_MAP.put("INT", "INTEGER");
    }

    public static Map<String, Object> convertColumn(Table table) {
        var ref = new Object() {
            String primaryColumnName = "", primaryPropertyType = "", propertySimpleType = "", primaryPropertyName = "";
            String logicDeleteName = "";
        };
        table.getColumns().forEach(column -> {
            String rawType = column.getRawType();
            for (Map.Entry<String, String> entry : RAW_TYPE_MAP.entrySet()) {
                if (StrUtil.containsIgnoreCase(rawType, entry.getKey())) {
                    column.setRawType(entry.getValue());
                }
            }

            boolean isLogicDelete = Convert.toBool(column.getColumnConfig().getLogicDelete(), false);
            if (isLogicDelete) {
                column.setPropertyType(Boolean.class.getName());
                ref.logicDeleteName = column.getProperty();
            }
            // 取第一个主键
            if (StrUtil.isBlank(ref.primaryColumnName) && column.isPrimaryKey()) {
                ref.primaryColumnName = column.getName();
                ref.primaryPropertyType = column.getPropertyType();
                ref.primaryPropertyName = column.getProperty();
                ref.propertySimpleType = column.getPropertySimpleType();
            }
        });

        Map<String, Object> map = new HashMap<>();
        map.put("primaryColumnName", ref.primaryColumnName);
        map.put("primaryPropertyType", ref.primaryPropertyType);
        map.put("propertySimpleType", ref.propertySimpleType);
        map.put("primaryPropertyName", ref.primaryPropertyName);
        map.put("primaryPropertyNameUpper", StrUtil.upperFirst(ref.primaryPropertyName));
        map.put("logicDeleteName", ref.logicDeleteName);
        map.put("entityNameLower", StrUtil.lowerFirst(table.getEntityJavaFileName()));
        return map;
    }
}
