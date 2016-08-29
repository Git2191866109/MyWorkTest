package com.mw.java.test.java;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wei.ma on 2016/8/29.
 */
public class LamdaTest {
    @Test
    public void testLambda() {
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));

// 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
// 看起来像C++的作用域解析运算符
        features.forEach(System.out::println);
    }

    @Test
    public void testLambda_value() {
        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5);
        int sum = values.stream().reduce(0, (l,r) -> l+r);
        System.out.println(sum);
    }
}
