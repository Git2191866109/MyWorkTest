package com.mw.java.test.designmodel.abstrackfactory;

/**
 * Created by wei.ma on 2016/12/8.
 */
public class TestMain {
    public static void main(String[] args) {
        IFactory carFactory = new SportFactory();
        carFactory.createCar().getCar();
    }
}
