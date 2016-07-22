package com.mw.java.test.utils;

import org.junit.Assert;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by mawei on 16/7/2.
 */
public class PropertiesUtils {
    /**
     * 根据key从prop文件中读取value
     * 要求,先实现key-value的形式,不带有匹配符,
     * 只要在resource/properties中配置的文件,都可以加载使用
     *
     * @param key
     * @return
     */
    public static String getPropValue(String key) {

        return null;
    }

    static Properties prop = new Properties();

    /**
     * 读取配置文件
     */
    static {
        InputStream inputStream = null;
        try {
            inputStream = PropertiesUtils.class.getResourceAsStream("/properties/kafka.properties");
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取value
     *
     * @param key
     * @return
     */
    public static String getValue(String key) {
        Assert.assertNotNull("key不能为空！", key);
        return prop.getProperty(key).trim();
    }

    public static void main(String[] args) {
//        PropertiesUtils propertiesUtils = new PropertiesUtils();
//        ApplicationContext context = new ClassPathXmlApplicationContext("testapp.xml");
//        String key = "driverClassName";
        System.out.println(getValue("ZKQUORUM"));
    }
}
