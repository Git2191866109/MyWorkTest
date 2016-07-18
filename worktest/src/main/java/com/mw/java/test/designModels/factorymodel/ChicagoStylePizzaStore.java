package com.mw.java.test.designModels.factorymodel;

/**
 * Created by mawei on 16/7/17.
 * 芝加哥风味的pizza店铺制作芝加哥风味的pizza
 */
public class ChicagoStylePizzaStore extends AbstractPizzaStore {
    Pizza pizza = null;

    @Override
    Pizza createPizza(String type) {
        if ("cheese".equals(type)) {
            pizza = new ChicagoStyleCheesePizza();
        } else if ("cheese".equals(type)) {
            pizza = new ChicagoStylePepperonPizza();
        }
        return pizza;
    }
}
