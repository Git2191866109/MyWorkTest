package com.mw.java.test.java.spring.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by mawei on 2016/8/15.
 * 用@Aspect的注解来标识切面
 * 这个@Pointcut注解指定了切点，
 * 而@Before和@AfterReturning指定了运行时的通知
 * 注意的是要在注解中传入切点的名称
 * 然后加上这个标签:
 * <aop:aspectj-autoproxy/> 有了这个Spring就能够自动扫描被@Aspect标注的切面了
 */
@Aspect
public class SleepHelperAnnotation {
    public SleepHelperAnnotation() {
    }

    @Pointcut("execution(* *.sleep())")
    public void sleeppoint() {
    }

    @Before("sleeppoint()")
    public void beforeSleep() {
        System.out.println("睡觉前要脱衣服!");
    }

    @AfterReturning("sleeppoint()")
    public void afterSleep() {
        System.out.println("睡醒了要穿衣服！");
    }

}
