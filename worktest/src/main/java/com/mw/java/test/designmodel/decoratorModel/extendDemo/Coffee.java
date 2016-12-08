package com.mw.java.test.designmodel.decoratorModel.extendDemo;

/**
 * Created by wei.ma on 2016/11/7.
 */
public class Coffee {
    void blankCoffee() {
        System.out.println("黑咖啡");
    }

    public static void main(String[] args) {
        Suger suger = new Suger();
        suger.blankCoffee();
        SugarMilke sugarMilke = new SugarMilke();
        sugarMilke.blankCoffee();

    }
}

class Suger extends Coffee {
    void blankCoffee() {
        super.blankCoffee();
        System.out.println("加糖");
    }
}

class Milk extends Coffee {
    void blankCoffee() {
        super.blankCoffee();
        System.out.println("加奶");
    }
}

/**
 * 现在要对黑咖啡加蜂蜜
 */
class Honey extends Coffee {
    void blankCoffee() {
        super.blankCoffee();
        System.out.println("加蜂蜜");
    }
}

/**
 * 现在需要加奶加糖咖啡
 */
class SugarMilke extends Milk {
    void blankCoffee() {
        super.blankCoffee();
        System.out.println("加糖");
    }
}



