package com.mw.java.test.designmodel.abstrackfactory;

/**
 * Created by wei.ma on 2016/12/8.
 */
public class HatchbackCar implements ICar {
    @Override
    public void getCar() {
        System.out.println("两箱车");
    }
}
