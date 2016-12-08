package com.mw.java.test.designmodel.celue.demo;

/**
 * Created by wei.ma on 2016/10/9.
 */
public class CalPriceFactory {
    private CalPriceFactory(){}
    //根据客户的总金额产生相应的策略
    public static CalPrice createCalPrice(Customer customer){
        if (customer.getTotalAmount() > 3000) {//3000则改为金牌会员计算方式
            return new GoldVip();
        }else if (customer.getTotalAmount() > 2000) {//类似
            return new SuperVip();
        }else if (customer.getTotalAmount() > 1000) {//类似
            return new Vip();
        }else {
            return new Common();
        }
    }
}
