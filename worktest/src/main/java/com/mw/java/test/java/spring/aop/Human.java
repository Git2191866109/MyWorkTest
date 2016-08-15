package com.mw.java.test.java.spring.aop;

/**
 * Created by mawei on 2016/8/15.
 */
public class Human implements Sleepable {
    /**
     * 这人莫非跟寡人差不多？
     * 除了睡觉睡的比较好之外其余的什么也不会做？
     */
    public void sleep() {
        System.out.println("睡觉了！梦中自有颜如玉！");
    }
}
