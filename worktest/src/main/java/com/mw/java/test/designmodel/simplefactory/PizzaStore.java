package com.mw.java.test.designmodel.simplefactory;

/**
 * Created by mawei on 16/7/17.
 */
public class PizzaStore {
    SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    /**
     * 订购pizza
     */
    public Pizza orderPizza(String type) {
        Pizza pizza = null;
        pizza = factory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
//        System.out.println(pizza.getName() + "被创建");
        return pizza;
    }
}
