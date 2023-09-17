package com.github.fank243.generator.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.fank243.study.core.domain.enums.PermTypeEnum;
import com.github.fank243.study.core.domain.enums.UserStatusEnum;

import lombok.Builder;
import lombok.Data;

/**
 * @author FanWeiJie
 * @date 2023-09-16 21:28
 */
@Data
public class MyTable {

    public static final Map<String, String[]> TABLES_GROUP = new HashMap<>(20);

    static {
        TABLES_GROUP.put("log", new String[] {"tb_operation_log"});
        TABLES_GROUP.put("support", new String[] {"tb_file", "tb_support_area", "tb_support_sms"});
        TABLES_GROUP.put("oauth2", new String[] {"tb_oauth_access_token", "tb_oauth_client", "tb_oauth_user"});
        TABLES_GROUP.put("system", new String[] {"tb_sys_perm", "tb_sys_role", "tb_sys_role_perm", "tb_sys_user",
            "tb_sys_user_login_log", "tb_sys_user_role"});
    }

    private String tableName;
    private String modelName;
    private List<MyColumn> columnList;

    @Data
    @Builder
    public static class MyColumn {

        private boolean isPrimary;

        private String tableName;

        private String columnName;

        private String enumName;

        public String getEnumName() {
            switch (this.tableName) {
                case "tb_sys_user" -> {
                    if ("status".equalsIgnoreCase(this.columnName)) {
                        return UserStatusEnum.class.getName();
                    }

                }
                case "tb_sys_perm" -> {
                    if ("perm_type".equalsIgnoreCase(this.columnName)) {
                        return PermTypeEnum.class.getName();
                    }
                }

                default -> {
                }
            }
            return null;
        }
    }
}
