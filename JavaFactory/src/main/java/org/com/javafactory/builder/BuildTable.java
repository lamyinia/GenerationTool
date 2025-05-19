package org.com.javafactory.builder;

import org.com.javafactory.bean.TableInfo;
import org.com.javafactory.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuildTable {
    private static final Logger logger = LoggerFactory.getLogger(BuildTable.class);

    private static Connection connection;

    private static final String SQL_SHOW_TABLE_STATUS = "show table status";
    private static final String SQL_SHOW_FIELDS = "show full fields from ";
    private static final String KEY_AUTO_INCREMENT = "auto_increment";

    public BuildTable() throws ClassNotFoundException, SQLException {
        String driverName = PropertiesUtils.getProperty("db.datasource.driver-class-name");
        String url = PropertiesUtils.getProperty("db.datasource.url");
        String username = PropertiesUtils.getProperty("db.datasource.username");
        String password = PropertiesUtils.getProperty("db.datasource.password");

        Class.forName(driverName);
        /*
            Class.forName(driverName) 是传统的JDBC驱动加载方式，确保驱动在建立连接前完成注册到 DriverManager。
            现代项目中可选择性使用，但保留它能提高代码的健壮性。
         */
        connection = DriverManager.getConnection(url, username, password);
    }

    public List<TableInfo> getTableInfo() throws SQLException {
        List<TableInfo> tableInfoList = new ArrayList<>();
        return tableInfoList;
    }
}
