package com.mw.java.test.designModels.factorymodel.simplefactory;


/**
 * Created by mawei on 16/7/17.
 */
public class PizzaTestDrive {
    public static void main(String[] args) {
        SimplePizzaFactory simplePizzaFactory = new SimplePizzaFactory();
        PizzaStore pizzaStore = new PizzaStore(simplePizzaFactory);
        Pizza cheesePizza = pizzaStore.orderPizza("cheese");
        Pizza pepperonPizza = pizzaStore.orderPizza("pepperon");
    }
}
