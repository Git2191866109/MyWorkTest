package com.mw.java.test.designModels.factorymodel;

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
        return pizza;
    }
}
