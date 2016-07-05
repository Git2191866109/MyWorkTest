package com.mw.java.test.extendsAndcomposite.extendspackage;

/**
 * Created by mawei on 2016/7/4.
 */
public class RedHeadDuck {
    //橡皮鸭叫声为吱吱叫，所以重写父类以改写行为
    public void quack() {
        System.out.println("Squeak");
    }

    //橡皮鸭显示为黄头
    public void display() {
        System.out.println("Yellow head.");
    }
}
