package com.mw.java.test.designModels.factorymodel;

/**
 * Created by mawei on 16/7/17.
 */
public abstract class AbstractPizza {
    String name;
    Dough dough;
    Sauce sauce;
    Veggies veggies[];
    Cheese cheese;
    Pepperoni pepperoni;
    Clams clams;

    /*把prepare方法声明成抽象,在这个方法中,需要收集比萨所需要的原料,而这些原料来自原料工厂*/
    abstract void prepare();

    void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    void cut() {
        System.out.println("Cutting the pizza into diagonal silces");
    }

    void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    public String toString() {
        return null;
    }
}
