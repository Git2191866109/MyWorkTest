package com.mw.java.test.extendsAndcomposite.interfacepackage;

/**
 * Created by mawei on 2016/7/4.
 * 将变化的行为 fly() 以及quake()从Duck类中分离出去定义形成接口，有需求的子类中自行去实现
 * 上述代码虽然解决了一部分问题，让子类型可以有选择地提供一些行为(例如 fly() 方法将不会出现在橡皮鸭中).
 * 但我们也看到，野鸭子MallardDuck.java和红头鸭子RedHeadDuck.java的一些相同行为代码不能得到重复使用。
 */
public abstract class Duck {

    public void swim() {
        System.out.println("All ducks float, even decoys.");
    }

    public abstract void display();
}
