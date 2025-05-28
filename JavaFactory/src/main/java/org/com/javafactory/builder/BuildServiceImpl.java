package org.com.javafactory.builder;

import org.com.javafactory.bean.Constants;
import org.com.javafactory.bean.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class BuildServiceImpl {
    public static final Logger logger = LoggerFactory.getLogger(BuildServiceImpl.class);

    public static void execute(TableInfo tableInfo){
        File folder = new File(Constants.PATH_SERVICE_IMPL);
        if (!folder.exists()){
            folder.mkdirs();
        }

        String className = tableInfo.getBeanName() + Constants.SUFFIX_SERVICE_IMPL;
        File serviceImplFile = new File(folder, className + ".java");

        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(serviceImplFile), StandardCharsets.UTF_8))){
            writer.write("package " + Constants.PACKAGE_SERVICE_IMPL + ";");


        } catch (IOException e){
            logger.info("创建ServiceImpl失败");
        }
    }
}
