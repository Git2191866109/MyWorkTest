package com.mw.java.test.designModels.factorymodel;

/**
 * Created by mawei on 16/7/17.
 * 纽约风味的pizza店铺制作纽约风味的pizza
 */
public class NYStylePizzaStore extends AbstractPizzaStore {
    Pizza pizza = null;

    @Override
    Pizza createPizza(String type) {
        if ("cheese".equals(type)) {
            pizza = new NYStyleCheesePizza();
        } else if ("cheese".equals(type)) {
            pizza = new NYStylePepperonPizza();
        }
        return pizza;
    }
}
