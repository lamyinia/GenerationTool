package org.com.javafactory.bean;

import org.com.javafactory.utils.PropertiesUtils;

import static org.com.javafactory.utils.PropertiesUtils.getProperty;

public class Constants {
    public static Boolean IGNORE_TABLE_PREFIX;
    public static String SUFFIX_BEAN_PARAM;
    static {
        IGNORE_TABLE_PREFIX = Boolean.valueOf(PropertiesUtils.getProperty("ignore.table.prefix"));
        SUFFIX_BEAN_PARAM = PropertiesUtils.getProperty("suffix.bean.param");
    }

    public static final String[] SQL_INTEGER_TYPES = new String[] {"tinyint", "int"};
    public static final String[] SQL_LONG_TYPES = new String[] {"bigint"};
    public static final String[] SQL_DECIMAL_TYPES = new String[] {"decimal", "double", "float"};
    public static final String[] SQL_STRING_TYPES = new String[] {"char", "varchar", "text", "mediumtext", "longtext"};
    public static final String[] SQL_DATETIME_TYPES = new String[] {"datetime", "timestamp"};
    public static final String[] SQL_DATE_TYPES = new String[] {"date"};

    public static String TYPE_STRING = "String";
    public static String TYPE_DATE = "Date";
}
