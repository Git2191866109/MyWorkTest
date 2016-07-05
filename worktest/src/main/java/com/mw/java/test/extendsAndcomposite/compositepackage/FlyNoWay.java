package com.mw.java.test.extendsAndcomposite.compositepackage;

/**
 * Created by mawei on 2016/7/4.
 * 变化的 fly() 行为的实现类之二
 */
public class FlyNoWay implements FlyBehavior {
    public void fly() {
        System.out.println("I can't fly.FlyNoWay");
    }
}
