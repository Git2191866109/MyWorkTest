package com.mw.java.test.designmodel.celue.demo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wei.ma on 2016/10/9.
 * 它来记录每一个策略的生效区间
 */
//这是我们的有效区间注解，可以给策略添加有效区间的设置
@Target(ElementType.TYPE)//表示只能给类添加该注解
@Retention(RetentionPolicy.RUNTIME)//这个必须要将注解保留在运行时
public @interface TotalValidRegin {
    //为了简单，我们让区间只支持整数
    int max() default Integer.MAX_VALUE;
    int min() default Integer.MIN_VALUE;
}
