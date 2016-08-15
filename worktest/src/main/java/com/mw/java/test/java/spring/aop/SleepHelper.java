package com.mw.java.test.java.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by mawei on 2016/8/15.
 */
public class SleepHelper implements MethodBeforeAdvice, AfterReturningAdvice {

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("通常情况下睡觉之前要脱衣服！");
    }

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("起床后要先穿衣服！");
    }

    public void afterSleep(JoinPoint joinPoint) throws Throwable {
        System.out.println("起床后要先穿衣服！");
    }

    public void beforeSleep(JoinPoint joinPoint) throws Throwable {
        System.out.println("通常情况下睡觉之前要脱衣服！");
    }
}
