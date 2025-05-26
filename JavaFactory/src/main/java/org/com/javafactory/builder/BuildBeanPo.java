package org.com.javafactory.builder;

import org.apache.commons.lang3.ArrayUtils;
import org.com.javafactory.bean.Constants;
import org.com.javafactory.bean.FieldInfo;
import org.com.javafactory.bean.TableInfo;
import org.com.javafactory.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class BuildBeanPo {
    public static final Logger logger = LoggerFactory.getLogger(BuildBeanPo.class);

    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_BEAN);
        if (!folder.exists()){
            folder.mkdirs();
        }

        File beanFile = new File(folder, tableInfo.getBeanName() + ".java");

        try(OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(beanFile), "utf-8");
            BufferedWriter bw = new BufferedWriter(osw)){

            bw.write("package " + Constants.PACKAGE_BEAN + ";\n");

            /* 导入基本的类 */
            if (Constants.IGNORE_BEAN_TOJSON_CLASS != null){
                bw.write("\nimport " + Constants.IGNORE_BEAN_TOJSON_CLASS + ";");
            }
            if (Constants.BEAN_DATE_FORMAT != null){
            bw.write("\nimport " + Constants.BEAN_DATE_FORMAT_CLASS + ";");
            }
            bw.write("\n\nimport java.io.Serializable;");
            if (tableInfo.getHaveBigDecimal()){
                bw.write("\nimport java.math.BigDecimal;");
            }
            if (tableInfo.getHaveDate() || tableInfo.getHaveDateTime()){
                bw.write("\nimport " + Constants.BEAN_DATE_EXPRESSION_CLASS + ";");
                bw.write("\nimport java.util.Date;");
            }

            bw.newLine();
            BuildComment.buildClassComment(bw, tableInfo.getTableComment(), true, true);
            bw.newLine();

            bw.write("public class " + tableInfo.getBeanName() + " implements Serializable {\n");

            bw.write("\tprivate static final long serialVersionUID = 1L;\n");

            List<FieldInfo> fieldList = tableInfo.getFieldList();

            /* 声明基本的属性 */
            for (FieldInfo fieldInfo : fieldList){
                bw.newLine();
                BuildComment.buildBeanComment(bw, fieldInfo.getComment());

                if (Constants.IGNORE_BEAN_TOJSON_FIELDS != null){
                    Boolean ignore = false;
                    for (String s : Constants.IGNORE_BEAN_TOJSON_FIELDS) {
                        if (s.equals(fieldInfo.getFieldName())){
                            ignore = true;
                            break;
                        }
                    }
                    if (ignore){
                        bw.write("\n\t" + Constants.IGNORE_BEAN_TOJSON_EXPRESSION);
                    }
                }

                if (ArrayUtils.contains(Constants.SQL_DATE_TYPES, fieldInfo.getSqlType()) ||
                    ArrayUtils.contains(Constants.SQL_DATETIME_TYPES, fieldInfo.getSqlType())){
                    String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
                    if (ArrayUtils.contains(Constants.SQL_DATE_TYPES, fieldInfo.getSqlType())){
                        dateTimePattern = "yyyy-MM-dd";
                    }
                    bw.write("\n\t" + String.format(Constants.BEAN_DATE_EXPRESSION, dateTimePattern));
                    bw.write("\n\t" + String.format(Constants.BEAN_DATE_FORMAT, dateTimePattern));
                }

                bw.write("\n\tprivate " + fieldInfo.getJavaType() + " " + fieldInfo.getBeanName() + ";");
            }

            /* 生成 getter 和 setter 方法*/
            for (FieldInfo fieldInfo : fieldList) {
                String tempField = StringUtils.upperCaseFirstLetter(fieldInfo.getBeanName());
                bw.write("\n\tpublic void set" + tempField + "(" + fieldInfo.getJavaType() + " " + fieldInfo.getBeanName() + "){");
                bw.write("\n\t\tthis." + fieldInfo.getBeanName() + " = " + fieldInfo.getBeanName() + ";");
                bw.write("\n\t}");

                bw.write("\n\tpublic " + fieldInfo.getJavaType() + " get"+tempField+"(){");
                bw.write("\n\t\treturn this." + fieldInfo.getBeanName() + ";");
                bw.write("\n\t}");
            }

            bw.write("\n}");
        } catch (Exception e){
            logger.info("创建bean失败");
        }
    }
}
