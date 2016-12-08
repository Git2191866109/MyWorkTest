package com.mw.java.test.designmodel.abstrackfactory;

/**
 * Created by wei.ma on 2016/12/8.
 */
public class JeepFactory implements IFactory {
    @Override
    public ICar createCar() {
        return new JeepCar();
    }
}
