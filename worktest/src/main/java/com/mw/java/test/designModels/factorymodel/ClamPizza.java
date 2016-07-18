package com.mw.java.test.designModels.factorymodel;

/**
 * Created by mawei on 16/7/17.
 */
public class ClamPizza extends AbstractPizza {
    PizzaIngredientFactory ingredientFactory;

    public ClamPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    void prepare() {
        /*一步一步的创建芝士比萨,当需要原料时就跟工厂要*/
        System.out.println("prepare " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
        /*如果是纽约工厂就需要新鲜的蛤蜊,如果是芝加哥工厂就需要冷冻的蛤蜊*/
        clams = ingredientFactory.createClams();
    }
}
