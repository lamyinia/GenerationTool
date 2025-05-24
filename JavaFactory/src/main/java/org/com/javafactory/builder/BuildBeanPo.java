package org.com.javafactory.builder;

import org.com.javafactory.bean.Constants;
import org.com.javafactory.bean.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

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


        } catch (Exception e){
            logger.info("创建bean失败");
        }
    }
}
