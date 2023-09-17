package com.github.fank243.generator.utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.fank243.generator.domain.MyTable;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

/**
 * Jdbc Tool
 *
 * @author FanWeiJie
 * @date 2023/09/16 22:28
 */
public class DBUtils {
    public static final String JDBC_URL;

    public static final String USERNAME;

    public static final String PASSWORD;

    static {
        JDBC_URL = System.getenv("STUDY_MYSQL_URL").replaceAll("#dbName#", "fank243-study");
        USERNAME = System.getenv("STUDY_MYSQL_USERNAME");
        PASSWORD = System.getenv("STUDY_MYSQL_PASSWORD");
        try {
            // 注册驱动
            Class.forName(Driver.class.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取数据库连接
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 查询所有
    public static List<MyTable> executeSql(String sql) {
        Connection conn = getConnection();
        try {
            if (conn == null) {
                return null;
            }
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            List<MyTable.MyColumn> columnList = new ArrayList<>();
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                String columnName = resultSet.getString("COLUMN_NAME");
                String columnKey = resultSet.getString("COLUMN_KEY");

                boolean isPrimary = StrUtil.equalsIgnoreCase("PRI", columnKey);

                // @formatter:off
                MyTable.MyColumn column = MyTable.MyColumn.builder()
                    .tableName(tableName)
                    .columnName(columnName)
                    .isPrimary(isPrimary)
                        .build();
                // @formatter:on

                columnList.add(column);
            }
            if (CollUtil.isEmpty(columnList)) {
                return new ArrayList<>(0);
            }
            List<MyTable> tableList = new ArrayList<>();

            Map<String, List<MyTable.MyColumn>> groupMap =
                columnList.stream().collect(Collectors.groupingBy(MyTable.MyColumn::getTableName));
            groupMap.forEach((tableName, myColumns) -> {
                MyTable myTable = new MyTable();
                myTable.setTableName(tableName);
                myTable.setColumnList(myColumns);

                tableList.add(myTable);
            });

            return tableList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();
        for (int x = 1; x <= columns; x++) {
            if (columnName.equals(rsmd.getColumnName(x))) {
                return true;
            }
        }
        return false;
    }

    // 关闭数据库连接
    public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
