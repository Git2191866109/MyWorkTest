package com.mw.java.test.designModels.factorymodel;

/**
 * Created by mawei on 16/7/17.
 */
public abstract class AbstractPizzaStore {

    /**
     * 订购pizza
     */
    public Pizza orderPizza(String type) {
        Pizza pizza = null;
        pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    /**
     * 将createPizza的方法抽象出来,
     * 产生的是工厂对象
     */
    abstract Pizza createPizza(String type);
}
