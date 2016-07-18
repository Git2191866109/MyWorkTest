package com.mw.java.test.designModels.factorymodel;

/**
 * Created by mawei on 16/7/17.
 */
public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        Veggies veggies[] = {new Garlic(), new Onion(), new Mushroom(), new RedPepper()};
        return veggies;
    }

    @Override
    public Pepperoni createPepperoni() {
        /*切片的意式腊肠*/
        return new SlicedPepperoni();
    }

    @Override
    public Clams createClams() {
        /*新鲜蛤蜊*/
        return new FreshClams();
    }
}
