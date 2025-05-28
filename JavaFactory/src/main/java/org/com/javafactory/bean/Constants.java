package org.com.javafactory.bean;

import org.com.javafactory.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
    public static final Logger logger = LoggerFactory.getLogger(Constants.class);

    public static String[] IGNORE_BEAN_TOJSON_FIELDS;
    public static String IGNORE_BEAN_TOJSON_EXPRESSION;
    public static String IGNORE_BEAN_TOJSON_CLASS;
    public static Boolean IGNORE_TABLE_PREFIX;

    public static String SUFFIX_MAPPER;
    public static String SUFFIX_MAPPER_XML;
    public static String SUFFIX_SERVICE;
    public static String SUFFIX_SERVICE_IMPL;
    public static String SUFFIX_CONTROLLER;
    public static String SUFFIX_PROPERTY_FUZZY;
    public static String SUFFIX_BEAN_PARAM;
    public static String SUFFIX_BEAN_PARAM_START;
    public static String SUFFIX_BEAN_PARAM_END;

    public static String BEAN_DATE_EXPRESSION;
    public static String BEAN_DATE_EXPRESSION_CLASS;
    public static String BEAN_DATE_FORMAT;
    public static String BEAN_DATE_FORMAT_CLASS;

    public static String PACKAGE_BASE;
    public static String PACKAGE_BEAN;
    public static String PACKAGE_MAPPER;
    public static String PACKAGE_SERVICE;
    public static String PACKAGE_SERVICE_IMPL;
    public static String PACKAGE_CONTROLLER;
    public static String PACKAGE_ENUMS;
    public static String PACKAGE_VO;
    public static String PACKAGE_PARAM;
    public static String PACKAGE_UTILS;
    public static String PACKAGE_EXCEPTION;


    public static String PATH_BASE;
    public static String PATH_BEAN;
    public static String PATH_MAPPER;
    public static String PATH_MAPPER_XML;
    public static String PATH_SERVICE;
    public static String PATH_SERVICE_IMPL;
    public static String PATH_CONTROLLER;
    public static String PATH_ENUMS;
    public static String PATH_VO;
    public static String PATH_PARAM;
    public static String PATH_UTILS;
    public static String PATH_EXCEPTION;

    private static String PATH_MAVEN = "src/main/";
    private static String PATH_JAVA = PATH_MAVEN + "java/";
    private static String PATH_RESOURCES = PATH_MAVEN + "resources/";

    public static String COMMENT_AUTHOR = PropertiesUtils.getProperty("comment.author");

    static {
        PACKAGE_BASE = PropertiesUtils.getProperty("package.base");
        PACKAGE_BEAN = PACKAGE_BASE + "." + PropertiesUtils.getProperty("package.bean");
        PACKAGE_MAPPER = PACKAGE_BASE + "." + PropertiesUtils.getProperty("package.mapper");
        PACKAGE_SERVICE = PACKAGE_BASE + "." + PropertiesUtils.getProperty("package.service");
        PACKAGE_SERVICE_IMPL = PACKAGE_BASE + "." + PropertiesUtils.getProperty("package.service.impl");
        PACKAGE_CONTROLLER = PACKAGE_BASE + "." + PropertiesUtils.getProperty("package.controller");
        PACKAGE_ENUMS = PACKAGE_BASE + "." + PropertiesUtils.getProperty("package.enums");
        PACKAGE_VO = PACKAGE_BASE + "." + PropertiesUtils.getProperty("package.vo");
        PACKAGE_PARAM = PACKAGE_BASE + "." + PropertiesUtils.getProperty("package.param");
        PACKAGE_UTILS = PACKAGE_BASE + "." + PropertiesUtils.getProperty("package.utils");
        PACKAGE_EXCEPTION = PACKAGE_BASE + "." + "exception";

        PATH_BASE = PropertiesUtils.getProperty("path.base") + PATH_JAVA;
        PATH_RESOURCES = PropertiesUtils.getProperty("path.base") + PATH_RESOURCES;
        PATH_BEAN = PATH_BASE + PACKAGE_BEAN.replace(".", "/");
        PATH_MAPPER = PATH_BASE + PACKAGE_MAPPER.replace(".", "/");
        PATH_MAPPER_XML = PATH_RESOURCES + PACKAGE_MAPPER.replace(".", "/");
        PATH_SERVICE = PATH_BASE + PACKAGE_SERVICE.replace(".", "/");
        PATH_SERVICE_IMPL = PATH_BASE + PACKAGE_SERVICE_IMPL.replace(".", "/");
        PATH_CONTROLLER = PATH_BASE + PACKAGE_CONTROLLER.replace(".", "/");
        PATH_ENUMS = PATH_BASE + PACKAGE_ENUMS.replace(".", "/");
        PATH_VO = PATH_BASE + PACKAGE_VO.replace(".", "/");
        PATH_PARAM = PATH_BASE + PACKAGE_PARAM.replace(".", "/");
        PATH_UTILS = PATH_BASE + PACKAGE_UTILS.replace(".", "/");
        PATH_EXCEPTION = PATH_BASE + PACKAGE_EXCEPTION.replace(".", "/");

        IGNORE_BEAN_TOJSON_FIELDS = PropertiesUtils.getProperty("ignore.bean.tojson.field").split(",");
        IGNORE_BEAN_TOJSON_EXPRESSION = PropertiesUtils.getProperty("ignore.bean.tojson.expression");
        IGNORE_BEAN_TOJSON_CLASS = PropertiesUtils.getProperty("ignore.bean.tojson.class");

        BEAN_DATE_EXPRESSION = PropertiesUtils.getProperty("bean.date.expression");
        BEAN_DATE_EXPRESSION_CLASS = PropertiesUtils.getProperty("bean.date.expression.class");
        BEAN_DATE_FORMAT = PropertiesUtils.getProperty("bean.date.format");
        BEAN_DATE_FORMAT_CLASS = PropertiesUtils.getProperty("bean.date.format.class");

        IGNORE_TABLE_PREFIX = Boolean.valueOf(PropertiesUtils.getProperty("ignore.table.prefix"));

        SUFFIX_BEAN_PARAM = PropertiesUtils.getProperty("suffix.bean.param");
        SUFFIX_MAPPER = PropertiesUtils.getProperty("suffix.mapper");
        SUFFIX_MAPPER_XML = PropertiesUtils.getProperty("suffix.mapper.xml");
        SUFFIX_SERVICE = PropertiesUtils.getProperty("suffix.service");
        SUFFIX_SERVICE_IMPL = PropertiesUtils.getProperty("suffix.service.impl");
        SUFFIX_CONTROLLER = PropertiesUtils.getProperty("suffix.controller");
        SUFFIX_PROPERTY_FUZZY = PropertiesUtils.getProperty("suffix.property.fuzzy");
        SUFFIX_BEAN_PARAM_START = PropertiesUtils.getProperty("suffix.bean.param.start");
        SUFFIX_BEAN_PARAM_END = PropertiesUtils.getProperty("suffix.bean.param.end");
    }

    public static final String[] SQL_INTEGER_TYPES = new String[] {"tinyint", "int"};
    public static final String[] SQL_LONG_TYPES = new String[] {"bigint"};
    public static final String[] SQL_DECIMAL_TYPES = new String[] {"decimal", "double", "float"};
    public static final String[] SQL_STRING_TYPES = new String[] {"char", "varchar", "text", "mediumtext", "longtext"};
    public static final String[] SQL_DATETIME_TYPES = new String[] {"datetime", "timestamp"};
    public static final String[] SQL_DATE_TYPES = new String[] {"date"};

    public static String TYPE_STRING = "String";
    public static String TYPE_DATE = "Date";

    public static void main(String[] args) {
        logger.info(SUFFIX_BEAN_PARAM_END);
    }
}
