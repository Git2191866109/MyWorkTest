package com.mw.java.test.initdata;

import com.mw.java.test.initdata.InitializeData;
import com.mw.java.test.initdata.TestGetInitData;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Created by mawei on 2016/6/23.
 */
public class MainTest_Init {

    @Test
    public void testMain() {
        /**
         * 1.没有前缀：默认为项目的classpath下相对路径
         */
        ApplicationContext context = new ClassPathXmlApplicationContext("testapp.xml");
        /**
         * 2.前缀classpath：表示的是项目的classpath下相对路径
         */
//        ApplicationContext context_classpath = new ClassPathXmlApplicationContext("classpath:testapp.xml");
        /**
         * 3.使用前缀file 表示的是文件的绝对路径
         */
//        ApplicationContext context_file = new ClassPathXmlApplicationContext("file:testapp.xml");
        /**
         * 4.可以同时加载多个文件
         */
//        ApplicationContext context_file_mutiply = new ClassPathXmlApplicationContext("classpath:testapp.xml","application.xml");
        /**
         * 5.使用通配符加载所有符合要求的文件
         */
//        ApplicationContext context_all = new ClassPathXmlApplicationContext("classpath:*xml");
        /**
         * 得到自己定义的配置文件的数据
         */
        InitializeData initializeData = (InitializeData) context.getBean("initializeData");
        Map<String, List<Object>> configFileData = initializeData.getConfigFileData();
        String result = "";
        if (initializeData != null) {
            List<Object> dmList = configFileData.get("wage_growth_rate");
            if (dmList != null) {
                for (Object obj : dmList) {
                    if (obj instanceof String) {
                        result = obj.toString();
                        break;
                    }
                }
            }
        }
        System.out.println(result);
    }
}
