package com.mw.java.test.java.spring;

import com.mw.java.test.java.spring.aop.Sleepable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mawei on 2016/8/15.
 * Spring支持五种类型的通知：
 * Before(前)  org.apringframework.aop.MethodBeforeAdvice
 * After-returning(返回后) org.springframework.aop.AfterReturningAdvice
 * After-throwing(抛出后) org.springframework.aop.ThrowsAdvice
 * Arround(周围) org.aopaliance.intercept.MethodInterceptor
 * Introduction(引入) org.springframework.aop.IntroductionInterceptor
 */
public class TestAop {
    /**
     * 1.创建通知：实现这几个接口，把其中的方法实现了
     * 2.定义切点和通知者：在Spring配制文件中配置这些信息
     * 3.使用ProxyFactoryBean来生成代理
     */
    public static void main(String[] args) {
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("testapp.xml");
//        Sleepable sleeper = (Sleepable) appCtx.getBean("humanProxy");
        /*这是开启自动代理的方式*/
        Sleepable sleeper = (Sleepable) appCtx.getBean("human");
        sleeper.sleep();
    }
}
