package com.mw.java.test.designModels.factorymodel;

/**
 * Created by mawei on 16/7/17.
 * 为工厂定义一个接口,负责创建所有的原料
 */
public interface PizzaIngredientFactory {
    /*生面团*/
    Dough createDough();

    /*酱,调味汁*/
    Sauce createSauce();

    /*奶酪*/
    Cheese createCheese();

    /*蔬菜*/
    Veggies[] createVeggies();

    /*意大利辣肉肠*/
    Pepperoni createPepperoni();

    /*蛤蜊*/
    Clams createClams();
}
