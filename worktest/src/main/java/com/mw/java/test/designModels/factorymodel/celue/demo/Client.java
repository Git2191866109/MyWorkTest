package com.mw.java.test.designModels.factorymodel.celue.demo;

/**
 * Created by wei.ma on 2016/10/9.
 */
public class Client {
    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.buy(500D);
        System.out.println("客户需要付钱：" + customer.calLastAmount());
        customer.buy(1200D);
        System.out.println("客户需要付钱：" + customer.calLastAmount());
        customer.buy(1200D);
        System.out.println("客户需要付钱：" + customer.calLastAmount());
        customer.buy(1200D);
        System.out.println("客户需要付钱：" + customer.calLastAmount());
        customer.buy(2600D);
        System.out.println("客户需要付钱：" + customer.calLastAmount());
    }
}
