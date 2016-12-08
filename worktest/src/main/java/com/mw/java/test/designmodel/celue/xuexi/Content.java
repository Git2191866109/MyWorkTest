package com.mw.java.test.designmodel.celue.xuexi;

/**
 * Created by wei.ma on 2016/10/9.
 * 维护一个对Strategy对象的引用
 * 上下文，它会拥有一个策略接口
 */
public class Content {
    private InterFaceStrategy interFaceStrategy;

    public void setInterFaceStrategy(InterFaceStrategy interFaceStrategy) {
        this.interFaceStrategy = interFaceStrategy;
    }

    /* chaojia 方法是上下文类的一个公开方法，实际当中一般会和业务相关*/
    public void chaojia() {
        System.out.println("一言不合，还敢顶嘴。。。");
        interFaceStrategy.hitHusband();
        System.out.println("不打不痛快，从了吧。。。");
    }
}
