package com.mw.java.test.designModels.factorymodel.decoratorModel.decorateDemo;

/**
 * Created by wei.ma on 2016/11/7.
 */
public class CoffeeImpl implements Coffee {
    @Override
    public void coffee() {
        System.out.println("黑咖啡");
    }
}

/*
* 在给黑咖啡添加调味品之前,我们先定义一个类,这个类是所有添加调味品咖啡的父类,进行包装
*/
class CoffeeWrapper implements Coffee {
    private Coffee cof;

    public CoffeeWrapper(Coffee cof) {
        this.cof = cof;
    }

    public void coffee() {
        cof.coffee();
    }
}

/**
 * 加糖咖啡
 */
class Sugar extends CoffeeWrapper {
    public Sugar(Coffee cof) {
        super(cof);
    }

    public void coffee() {
        super.coffee();
        System.out.println("加糖");
    }
}

/**
 * 加奶咖啡
 */
class Milk extends CoffeeWrapper {
    public Milk(Coffee cof) {
        super(cof);
    }

    public void coffee() {
        super.coffee();
        System.out.println("加奶");
    }
}

/**
 * 蜂蜜咖啡
 */
class Honey extends CoffeeWrapper {
    public Honey(Coffee cof) {
        super(cof);
    }

    public void coffee() {
        super.coffee();
        System.out.println("加蜂蜜");
    }
}

/**
 * 演示
 */
class Demo {
    public static void main(String[] args) {
        //首先创建一个黑咖啡
//        Coffee cof = new CoffeeImpl();
        //加糖咖啡
//        Coffee sugar = new Sugar(cof);
//        sugar.coffee();
        //加糖又加奶咖啡
//        Coffee milk = new Milk(sugar);
        Coffee milk = new Milk(new Sugar(new CoffeeImpl()));
        milk.coffee();
    }

}