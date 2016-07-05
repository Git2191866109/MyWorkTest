package com.mw.java.test.extendsAndcomposite.compositepackage;

/**
 * Created by mawei on 2016/7/4.
 * 变化的 fly() 行为定义形成的接口
 * fly()行为以及quack()行为已经和Duck.java没有什么关系，可以充分得到复用。
 */
public interface FlyBehavior {
    void fly();
}
