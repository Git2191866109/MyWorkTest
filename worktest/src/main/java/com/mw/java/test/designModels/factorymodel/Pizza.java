package com.mw.java.test.designModels.factorymodel;

import java.util.ArrayList;

/**
 * Created by mawei on 16/7/17.
 */
public abstract class Pizza {
    /*名称*/
    String name;
    /*面团类型*/
    String dough;
    /*酱料类型*/
    String sauce;
    /*一套佐料*/
    ArrayList toppings = new ArrayList();

    public void prepare() {
        System.out.println("Prepare " + name);
        System.out.println("Tossing dough...");
        System.out.println("Adding sauce...");
        System.out.println("Adding toppings: ");
        for (int i = 0; i < toppings.size(); i++) {
            System.out.println(" " + toppings.get(i));
        }

    }

    public void bake() {
        System.out.println("Bake for 25 minites at 350");
    }

    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    public void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    public String getName() {
        return name;
    }
}
