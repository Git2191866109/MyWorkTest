package com.mw.java.test.extendsAndcomposite.extendspackage;

/**
 * Created by mawei on 2016/7/4.
 * 通过继承在父类中提供行为，会导致以下缺点：
 * a. 代码在多个子类中重复;
 * b. 运行时的行为不容易改变；
 * c. 改变会牵一发动全身，造成部分子类型不想要的改变；
 */
public abstract class Duck {
    //所有的鸭子均会叫以及游泳，所以父类中处理这部分代码
    public void quack() {
        System.out.println("Quack");
    }

    public void swim() {
        System.out.println("All ducks float, even decoys.");
    }

    //因为每种鸭子的外观是不同的，所以父类中该方法是抽象的，由子类型自己完成。
    public abstract void display();
}
