package org.com.javafactory.builder;

import org.apache.commons.lang3.ArrayUtils;
import org.com.javafactory.bean.Constants;
import org.com.javafactory.bean.FieldInfo;
import org.com.javafactory.bean.TableInfo;
import org.com.javafactory.utils.PropertiesUtils;
import org.com.javafactory.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildTable {
    private static final Logger logger = LoggerFactory.getLogger(BuildTable.class);

    public static Connection connection;

    private static final String SQL_SHOW_TABLE_STATUS = "show table status";
    private static final String SQL_SHOW_FIELDS = "show full fields from ";
    private static final String SQL_SHOW_INDEXES = "show index from ";
    private static final String KEY_AUTO_INCREMENT = "auto_increment";

    public BuildTable() throws ClassNotFoundException, SQLException {
        String driverName = PropertiesUtils.getProperty("db.datasource.driver-class-name");
        String url = PropertiesUtils.getProperty("db.datasource.url");
        String username = PropertiesUtils.getProperty("db.datasource.username");
        String password = PropertiesUtils.getProperty("db.datasource.password");
        /* 处理转义问题 */
        if (password.charAt(0) == '\"' && password.charAt(password.length() - 1) == '\"'){
            password = password.substring(1, password.length() - 1);
        }
        Class.forName(driverName);
        /*
            Class.forName(driverName) 是传统的JDBC驱动加载方式，确保驱动在建立连接前完成注册到 DriverManager。
            现代项目中可选择性使用，但保留它能提高代码的健壮性。
         */
        connection = DriverManager.getConnection(url, username, password);
    }

    public List<TableInfo> getTableInfo() throws SQLException {
        List<TableInfo> tableInfoList = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(SQL_SHOW_TABLE_STATUS);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                String tableName = rs.getString("Name");
                String comment = rs.getString("Comment");
                String beanName = tableName;
                if (Constants.IGNORE_TABLE_PREFIX){
                    int i = beanName.indexOf("_");
                    if (i != -1){
                        beanName = beanName.substring(i+1);
                    }
                }
                beanName = processName(beanName, true);

                TableInfo tableInfo = new TableInfo();
                tableInfo.setTableName(tableName);
                tableInfo.setBeanName(beanName);
                tableInfo.setTableComment(comment);
                tableInfo.setBeanParamName(beanName + Constants.SUFFIX_BEAN_PARAM);
                Map<String, FieldInfo> fieldMap = new HashMap<>();
                List <FieldInfo> fieldList = readFieldInfo(tableInfo, fieldMap);
                tableInfo.setFieldList(fieldList);

                getKeyIndexInfo(tableInfo, fieldMap);

                tableInfoList.add(tableInfo);
            }
        } catch (SQLException e) {
            logger.error("读取表失败");
        }

        return tableInfoList;
    }
    public static void getKeyIndexInfo(TableInfo tableInfo, Map<String, FieldInfo> fieldMap) throws SQLException {
        try(PreparedStatement stmt = connection.prepareStatement(SQL_SHOW_INDEXES + tableInfo.getTableName());
            ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                String keyName = rs.getString("Key_name");
                String fieldName = rs.getString("Column_name");
                int nonUnique = rs.getInt("Non_unique");
                if (nonUnique == 0){
                    tableInfo.getKeyIndexMap().computeIfAbsent(keyName, list -> new ArrayList<>()).add(fieldMap.get(fieldName));
                }
            }
        } catch (SQLException e) {
            logger.error("读取索引失败");
        }
    }
    public static List<FieldInfo> readFieldInfo(TableInfo tableInfo, Map<String,FieldInfo> fieldMap) throws SQLException {
        List<FieldInfo> fieldInfoList = new ArrayList<>();

        try(PreparedStatement stmt = connection.prepareStatement(SQL_SHOW_FIELDS + tableInfo.getTableName());
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                String fieldName = rs.getString("Field");
                String beanName = processName(fieldName, false);
                String sqlType = rs.getString("Type");
                String extra = rs.getString("Extra");
                String fieldComment = rs.getString("Comment");
                if (sqlType.indexOf("(") > 0){
                    sqlType = sqlType.substring(0, sqlType.indexOf("("));
                }
                String javaType = processType(sqlType);

                if (ArrayUtils.contains(Constants.SQL_DATETIME_TYPES, sqlType)){
                    tableInfo.setHaveDateTime(true);
                }
                if (ArrayUtils.contains(Constants.SQL_DATE_TYPES, sqlType)){
                    tableInfo.setHaveDate(true);
                }
                if (ArrayUtils.contains(Constants.SQL_DECIMAL_TYPES, sqlType)){
                    tableInfo.setHaveBigDecimal(true);
                }

                FieldInfo fieldInfo = new FieldInfo();
                fieldInfo.setFieldName(fieldName);
                fieldInfo.setSqlType(sqlType);
                fieldInfo.setFieldComment(fieldComment);
                fieldInfo.setBeanName(beanName);
                fieldInfo.setJavaType(javaType);
                if (KEY_AUTO_INCREMENT.equalsIgnoreCase(extra)){
                    fieldInfo.setAutoIncrement(true);
                } else {
                    fieldInfo.setAutoIncrement(false);
                }

                fieldMap.put(fieldName, fieldInfo);
                fieldInfoList.add(fieldInfo);
            }
        } catch (SQLException e) {
            logger.error("读取字段失败");
        }

        return fieldInfoList;
    }

    private static String processName(String beanName, Boolean upperFirst){
        StringBuffer ret = new StringBuffer();
        String[] beans = beanName.split("_");
        ret.append(upperFirst ? StringUtils.upperCaseFirstLetter(beans[0]) : beans[0]);
        for (int i = 1; i < beans.length; i++) {
            ret.append(StringUtils.upperCaseFirstLetter(beans[i]));
        }
        return ret.toString();
    }
    private static String processType(String type){
        if (ArrayUtils.contains(Constants.SQL_INTEGER_TYPES, type)){
            return "Integer";
        } else if (ArrayUtils.contains(Constants.SQL_LONG_TYPES, type)){
            return "Long";
        } else if (ArrayUtils.contains(Constants.SQL_DECIMAL_TYPES, type)){
            return "BigDecimal";
        } else if (ArrayUtils.contains(Constants.SQL_STRING_TYPES, type)){
            return "String";
        } else if (ArrayUtils.contains(Constants.SQL_DATETIME_TYPES, type) || ArrayUtils.contains(Constants.SQL_DATE_TYPES, type)){
            return "Date";
        } else {
            throw new RuntimeException("无法识别的类型: " + type);
        }
    }
}
