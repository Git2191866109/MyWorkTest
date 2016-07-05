package com.mw.java.test.extendsAndcomposite.interfacepackage;

/**
 * Created by mawei on 2016/7/4.
 * 红头鸭子会飞以及叫，所以也实现接口  FlyBehavior, QuackBehavior
 */
public class RedHeadDuck extends Duck implements FlyBehavior, QuackBehavior{
    public void display() {
        System.out.println("Red head.");
    }

    public void fly() {
        System.out.println("Fly.RedHeadDuck");
    }

    public void quack() {
        System.out.println("Quack.RedHeadDuck");
    }
}