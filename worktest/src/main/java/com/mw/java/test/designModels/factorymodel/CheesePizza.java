package com.mw.java.test.designModels.factorymodel;

/**
 * Created by mawei on 16/7/17.
 */
public class CheesePizza extends AbstractPizza {
    PizzaIngredientFactory ingredientFactory;

    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    void prepare() {
        /*一步一步的创建芝士比萨,当需要原料时就跟工厂要*/
        System.out.println("prepare " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
    }
}
