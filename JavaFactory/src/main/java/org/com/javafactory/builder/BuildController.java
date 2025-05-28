package org.com.javafactory.builder;

import org.com.javafactory.bean.Constants;
import org.com.javafactory.bean.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class BuildController {
    public static final Logger logger = LoggerFactory.getLogger(BuildController.class);

    public static void execute(TableInfo tableInfo){
        File folder = new File(Constants.PATH_CONTROLLER);
        if (!folder.exists()){
            folder.mkdir();
        }

        String className = tableInfo.getBeanName() + Constants.SUFFIX_CONTROLLER;
        File controllerFile = new File(folder, className + ".java");

        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(controllerFile), StandardCharsets.UTF_8))){
            writer.write("package " + Constants.PACKAGE_CONTROLLER + ";");


        } catch (IOException e){
            logger.info("创建controller失败");
        }
    }
}
