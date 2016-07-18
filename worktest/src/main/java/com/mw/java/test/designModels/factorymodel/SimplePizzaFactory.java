package com.mw.java.test.designModels.factorymodel;

/**
 * Created by mawei on 16/7/17.
 */
public class SimplePizzaFactory {
    Pizza pizza = null;

    public Pizza createPizza(String type) {
        if ("cheese".equals(type)) {
            pizza = new CheesePizza();
        } else if ("cheese".equals(type)) {
            pizza = new PepperonPizza();
        }
        return pizza;
    }
}
