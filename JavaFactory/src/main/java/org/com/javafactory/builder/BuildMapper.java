package org.com.javafactory.builder;

import org.com.javafactory.bean.Constants;
import org.com.javafactory.bean.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class BuildMapper {
    public static final Logger logger = LoggerFactory.getLogger(BuildMapper.class);

    public static void execute(TableInfo tableInfo){
        File folder = new File(Constants.PATH_MAPPER);
        if (!folder.exists()){
            folder.mkdirs();
        }

        String className = tableInfo.getBeanName() + Constants.SUFFIX_MAPPER;
        File mapperFile = new File(folder, className + ".java");

        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperFile), StandardCharsets.UTF_8))){
            writer.write("package " + Constants.PACKAGE_MAPPER + ";");


        } catch (IOException e){
            logger.info("创建mapper失败");
        }
    }
}
