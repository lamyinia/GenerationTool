package org.com.javafactory.builder;

import org.com.javafactory.bean.Constants;
import org.com.javafactory.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BuildBaseJava {
    private static final Logger logger = LoggerFactory.getLogger(BuildBaseJava.class);

    public static void execute(){
        List<String> headInfoList = new ArrayList<String>();

        headInfoList.clear();
        headInfoList.add("package " + Constants.PACKAGE_MAPPER);
        build(headInfoList, "BaseMapper", Constants.PATH_MAPPER);

        headInfoList.clear();
        headInfoList.add("package " + Constants.PACKAGE_VO);
        headInfoList.add("import java.util.ArrayList");
        headInfoList.add("import java.util.List");
        build(headInfoList, "PaginationResultVO", Constants.PATH_VO);

        headInfoList.clear();
        headInfoList.add("package " + Constants.PACKAGE_PARAM);
        build(headInfoList, "BaseParam", Constants.PATH_PARAM);
        headInfoList.clear();
        headInfoList.add("package " + Constants.PACKAGE_PARAM);
        headInfoList.add("import " + Constants.PACKAGE_ENUMS+ ".PageSizeEnum");
        build(headInfoList, "SimplePage", Constants.PATH_PARAM);

        headInfoList.clear();
        headInfoList.add("package " + Constants.PACKAGE_ENUMS);
        build(headInfoList, "ResponseCodeEnum", Constants.PATH_ENUMS);
        headInfoList.clear();
        headInfoList.add("package " + Constants.PACKAGE_ENUMS);
        build(headInfoList, "DateTimePatternEnum", Constants.PATH_ENUMS);
        headInfoList.clear();
        headInfoList.add("package " + Constants.PACKAGE_ENUMS);
        build(headInfoList, "PageSizeEnum", Constants.PATH_ENUMS);

    }
    private static void build(List<String> headInfoList, String packageName, String outPutPath){
        File folder = new File(outPutPath);
        if(!folder.exists()){
            folder.mkdirs();
        }

        File beanFile = new File(outPutPath, packageName + ".java");

        try(FileOutputStream out = new FileOutputStream(beanFile);
            InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream("template/" + packageName + ".txt");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));){

            for (String s : headInfoList) {
                writer.write(s + ";\n");
            }
            writer.newLine();

            String line = null;
            while ((line = reader.readLine()) != null){
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            logger.info("导入基本类失败");
        }
    }

    public static void main(String[] args) {
        execute();
    }
}
