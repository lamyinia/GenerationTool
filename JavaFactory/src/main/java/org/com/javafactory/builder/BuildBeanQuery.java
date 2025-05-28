package org.com.javafactory.builder;

import org.com.javafactory.bean.Constants;
import org.com.javafactory.bean.FieldInfo;
import org.com.javafactory.bean.TableInfo;
import org.com.javafactory.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BuildBeanQuery {
    public static final Logger logger = LoggerFactory.getLogger(BuildBeanQuery.class);

    public static void writeSetter(BufferedWriter writer, String tempField, String tempType, String beanName) throws IOException {
        writer.write("\n\tpublic void set" + tempField + "(" + tempType + " " + beanName + "){");
        writer.write("\n\t\tthis." + beanName + " = " + beanName + ";");
        writer.write("\n\t}");
    }
    public static void writeGetter(BufferedWriter writer, String tempField, String tempType, String beanName) throws IOException {
        writer.write("\n\tpublic " + tempType + " get"+tempField + "(){");
        writer.write("\n\t\treturn this." + beanName + ";");
        writer.write("\n\t}");
    }

    public static void execute(TableInfo tableInfo){
        File folder = new File(Constants.PATH_PARAM);
        if (!folder.exists()){
            folder.mkdirs();
        }

        String className = tableInfo.getBeanName() + Constants.SUFFIX_BEAN_PARAM;
        File beanFile = new File(folder, className + ".java");

        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile), StandardCharsets.UTF_8))){
            writer.write("package " + Constants.PACKAGE_PARAM + ";\n");
            if (tableInfo.getHaveBigDecimal()){
                writer.write("\nimport java.math.BigDecimal;");
            }
            if (tableInfo.getHaveDate() || tableInfo.getHaveDateTime()){
                writer.write("\nimport java.util.Date;");
            }

            writer.newLine();
            BuildComment.buildClassComment(writer, tableInfo.getTableComment() + "参数", true, true);
            writer.newLine();

            writer.write("\npublic class " + className + " extends BaseParam {");

            List<FieldInfo> fieldInfoList = tableInfo.getFieldList();
            for (FieldInfo fieldInfo : fieldInfoList) {
                writer.newLine();
                BuildComment.buildBeanComment(writer, fieldInfo.getComment());

                if (!Constants.TYPE_DATE.equals(fieldInfo.getJavaType())){
                    writer.write("\n\tprivate " + fieldInfo.getJavaType() + " " + fieldInfo.getBeanName() + ";");
                }

                if (Constants.TYPE_STRING.equals(fieldInfo.getJavaType())){
                    writer.write("\n\tprivate " + fieldInfo.getJavaType() + " " + fieldInfo.getBeanName() + Constants.SUFFIX_PROPERTY_FUZZY + ";");
                }

                if (Constants.TYPE_DATE.equals(fieldInfo.getJavaType())){
                    String startName = fieldInfo.getBeanName() + Constants.SUFFIX_BEAN_PARAM_START;
                    String endName = fieldInfo.getBeanName() + Constants.SUFFIX_BEAN_PARAM_END;
                    writer.write("\n\tprivate " + Constants.TYPE_STRING + " " + fieldInfo.getBeanName() + ";");
                    writer.write("\n\tprivate " + Constants.TYPE_STRING + " " + startName + ";");
                    writer.write("\n\tprivate " + Constants.TYPE_STRING + " " + endName + ";");
                }
            }
            writer.newLine();
            for (FieldInfo fieldInfo : fieldInfoList) {
                writer.newLine();

                String tempField = StringTools.upperCaseFirstLetter(fieldInfo.getBeanName());
                String tempType = fieldInfo.getJavaType();
                if (Constants.TYPE_DATE.equals(tempType)) tempType = Constants.TYPE_STRING;

                writeSetter(writer, tempField, tempType, fieldInfo.getBeanName());
                writeGetter(writer, tempField, tempType, fieldInfo.getBeanName());

                if (Constants.TYPE_STRING.equals(fieldInfo.getJavaType())){
                    String fuzzyField = tempField + Constants.SUFFIX_PROPERTY_FUZZY;
                    String fuzzyBean = fieldInfo.getBeanName() + Constants.SUFFIX_PROPERTY_FUZZY;
                    writeSetter(writer, fuzzyField, Constants.TYPE_STRING, fuzzyBean);
                    writeGetter(writer, fuzzyField, Constants.TYPE_STRING, fuzzyBean);
                }

                if (Constants.TYPE_DATE.equals(fieldInfo.getJavaType())){
                    String startName = fieldInfo.getBeanName() + Constants.SUFFIX_BEAN_PARAM_START;
                    String endName = fieldInfo.getBeanName() + Constants.SUFFIX_BEAN_PARAM_END;
                    tempField = StringTools.upperCaseFirstLetter(startName);
                    tempType = Constants.TYPE_STRING;
                    writeSetter(writer, tempField, tempType, startName);
                    writeGetter(writer, tempField, tempType, startName);

                    tempField = StringTools.upperCaseFirstLetter(endName);
                    writeSetter(writer, tempField, tempType, endName);
                    writeGetter(writer, tempField, tempType, endName);
                }
            }

            writer.write("\n}");
        } catch (IOException e){
            logger.info("创建query类失败");
        }
    }
}
