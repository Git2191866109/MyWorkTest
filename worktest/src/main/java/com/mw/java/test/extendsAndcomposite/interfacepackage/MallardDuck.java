package com.mw.java.test.extendsAndcomposite.interfacepackage;

/**
 * Created by mawei on 2016/7/4.
 * 野鸭子会飞以及叫，所以实现接口  FlyBehavior, QuackBehavior
 */
public class MallardDuck extends Duck implements FlyBehavior, QuackBehavior {
    public void display() {
        System.out.println("Green head.MallardDuck");
    }

    public void fly() {
        System.out.println("Fly.MallardDuck");
    }

    public void quack() {
        System.out.println("Quack.MallardDuck");
    }
}
