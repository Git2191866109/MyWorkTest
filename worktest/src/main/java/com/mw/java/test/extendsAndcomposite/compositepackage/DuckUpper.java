package com.mw.java.test.extendsAndcomposite.compositepackage;

/**
 * Created by mawei on 2016/7/4.
 * 将变化的行为 fly() 以及quake()从Duck类中分离出去定义形成接口，有需求的子类中自行去实现
 * 上述代码虽然解决了一部分问题，让子类型可以有选择地提供一些行为(例如 fly() 方法将不会出现在橡皮鸭中).
 * 但我们也看到，野鸭子MallardDuck.java和红头鸭子RedHeadDuck.java的一些相同行为代码不能得到重复使用。
 */
public abstract class DuckUpper {
    //将行为类声明为接口类型，降低对行为实现类型的依赖
//    Duck.java将 fly()以及quack()的行为委拖给行为类处理。
//    在父类Duck.java中增加设定行为类型的setter方法，接受行为类型对象的参数传入。为了降藕，行为参数被声明为接口类型。这样，既便在运行时，也可以通过调用这二个方法以改变行为。
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    //在刚才Duck.java中加入以下二个方法。
    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void performFly() {
        //不自行处理fly()行为，而是委拖给引用flyBehavior所指向的行为对象
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void swim() {
        System.out.println("All ducks float, even decoys.");
    }

    public abstract void display();
}
