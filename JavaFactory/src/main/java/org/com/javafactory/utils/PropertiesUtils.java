package org.com.javafactory.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/*
    老师讲的可能有问题
    查看源码发现 Properties.get(key) 是线程安全的
 */
public class PropertiesUtils {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    private static Properties props = new Properties();
    private static Map<String, String> PropertiesMap = new ConcurrentHashMap<>();
    static {
        try (InputStream input = PropertiesUtils.class.getClassLoader().getResourceAsStream("application.properties");
             BufferedReader reader = new BufferedReader(new InputStreamReader(input, "utf-8"))){

            props.load(reader);
            Iterator<Object> iterator = props.keySet().iterator();
            while (iterator.hasNext()){
                String key = (String) iterator.next();
                PropertiesMap.put(key, props.getProperty(key));
                logger.info(key + "=" + props.getProperty(key));
            }

        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }
    public static String getProperty(String key){
        return PropertiesMap.get(key);
    }
    public static void main(String[] args) {

    }
}
