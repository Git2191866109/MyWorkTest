package com.mw.java.test.designmodel.abstrackfactory;

/**
 * Created by wei.ma on 2016/12/8.
 */
public class HatchbackFactory implements IFactory {
    @Override
    public ICar createCar() {
        return new HatchbackCar();
    }
}
