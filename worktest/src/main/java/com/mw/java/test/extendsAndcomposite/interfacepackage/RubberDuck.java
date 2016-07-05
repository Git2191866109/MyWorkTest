package com.mw.java.test.extendsAndcomposite.interfacepackage;

/**
 * Created by mawei on 2016/7/4.
 */
public class RubberDuck extends Duck implements QuackBehavior{
    //橡皮鸭叫声为吱吱叫
    public void quack() {
        System.out.println("Squeak");
    }

    //橡皮鸭显示为黄头
    public void display() {
        System.out.println("Yellow head.");
    }
}