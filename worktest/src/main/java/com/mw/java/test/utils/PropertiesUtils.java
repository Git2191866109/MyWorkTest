package com.mw.java.test.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mawei on 16/7/2.
 */
public class PropertiesUtils {
    /**
     * 根据key从prop文件中读取value
     * 要求,先实现key-value的形式,不带有匹配符,
     * 只要在resource/properties中配置的文件,都可以加载使用
     * @param key
     * @return
     */
    public static String getPropValue(String key){

        return null;
    }

    public static void main(String[] args) {
        PropertiesUtils propertiesUtils = new PropertiesUtils();
        ApplicationContext context = new ClassPathXmlApplicationContext("testapp.xml");
        String key = "driverClassName";

    }
}
