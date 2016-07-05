package com.mw.java.test.extendsAndcomposite.compositepackage;

/**
 * Created by mawei on 2016/7/4.
 * 在Duck.java子类型MallardDuck.java的构造方法中，直接实例化行为类型，在编译的时侯便指定具体行为类型。当然，我们可以：
 * 1) 我们可以通过工厂模式或其它模式进一步解藕(可参考后续模式讲解);
 * 2) 或做到在运行时动态地改变行为。
 */
public class MainTest {
    public static void main(String[] args) {
        Duck duck = new MallardDuck();
        duck.performFly();
        duck.performQuack();
        duck.setFlyBehavior(new FlyNoWay());
        duck.performFly();
    }
}
