package org.com.javafactory.builder;

import org.com.javafactory.bean.Constants;
import org.com.javafactory.bean.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class BuildMapperXml {
    public static final Logger logger = LoggerFactory.getLogger(BuildMapper.class);

    public static void execute(TableInfo tableInfo){
        File folder = new File(Constants.PATH_MAPPER_XML);
        if (!folder.exists()){
            folder.mkdirs();
        }

        String className = tableInfo.getBeanName() + Constants.SUFFIX_MAPPER_XML;
        File mapperXmlFile = new File(folder, className + ".xml");

        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperXmlFile), StandardCharsets.UTF_8))){


        } catch (IOException e){
            logger.info("创建mapperXml失败");
        }
    }
}
