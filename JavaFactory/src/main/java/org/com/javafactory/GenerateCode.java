package org.com.javafactory;

import org.com.javafactory.bean.FieldInfo;
import org.com.javafactory.bean.TableInfo;
import org.com.javafactory.builder.BuildTable;
import org.com.javafactory.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GenerateCode {
    private static final Logger logger = LoggerFactory.getLogger(GenerateCode.class);
    public static void main(String[] args) {

        try {
            BuildTable builder = new BuildTable();
            List<TableInfo> table = builder.getTableInfo();
            for (TableInfo tableInfo : table) {
                logger.info("{}, {}, {}, {}", tableInfo.getTableName(), tableInfo.getBeanName(), tableInfo.getTableComment(), tableInfo.getBeanParamName());
            }
            List<FieldInfo> list = table.get(0).getFieldList();
            for (FieldInfo fieldInfo : list) {
                logger.info(JsonUtils.toJson(fieldInfo));
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("生成代码失败");
        }

    }
}
/*
                List<FieldInfo> list = builder.readFieldInfo(tableInfo);
                for (FieldInfo fieldInfo : list) {
                    logger.info("{}, {}, {}, {}, {}, {}", fieldInfo.getFieldName(), fieldInfo.getPropertyName(), fieldInfo.getComment(),
                            fieldInfo.getSqlType(), fieldInfo.getAutoIncrement(), fieldInfo.getJavaType());
                }

                logger.info("表:{}", JsonUtils.toJson(tableInfo));
                logger.info("字段:{}", JsonUtils.toJson(list));
 */
