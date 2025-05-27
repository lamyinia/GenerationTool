package org.com.javafactory;

import org.com.javafactory.bean.FieldInfo;
import org.com.javafactory.bean.TableInfo;
import org.com.javafactory.builder.BuildBaseJava;
import org.com.javafactory.builder.BuildBeanPo;
import org.com.javafactory.builder.BuildTable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GenerateCode {
    private static final Logger logger = LoggerFactory.getLogger(GenerateCode.class);
    public static void main(String[] args) {

        try {
            BuildTable builder = new BuildTable();
            List<TableInfo> tables = builder.getTableInfo();
            for (TableInfo table : tables) {

                BuildBaseJava.execute();

                BuildBeanPo.execute(table);

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("生成代码失败");
        }

    }
}
/*
            打印表和字段
            for (TableInfo tableInfo : tables) {
                logger.info("{}, {}, {}, {}", tableInfo.getTableName(), tableInfo.getBeanName(), tableInfo.getTableComment(), tableInfo.getBeanParamName());
            }
            List<FieldInfo> list = tables.get(0).getFieldList();
            for (FieldInfo fieldInfo : list) {
                logger.info(JsonUtils.toJson(fieldInfo));
            }
            for (TableInfo table : tables){
                BuildBeanPo.execute(table);
            }
 */
