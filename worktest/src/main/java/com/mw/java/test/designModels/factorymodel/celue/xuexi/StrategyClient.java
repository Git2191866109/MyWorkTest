package com.mw.java.test.designModels.factorymodel.celue.xuexi;

/**
 * Created by wei.ma on 2016/10/9.
 */
public class StrategyClient {
    public static void main(String[] args) {
        Content content = new Content();

        InterFaceStrategy interFaceStrategyA = new StrategyMethodA();
        InterFaceStrategy interFaceStrategyB = new StrategyMethodB();
        InterFaceStrategy interFaceStrategyC = new StrategyMethodC();
        content.setInterFaceStrategy(interFaceStrategyA);
        content.chaojia();
        System.out.println("##########################");
        content.setInterFaceStrategy(interFaceStrategyB);
        content.chaojia();
        System.out.println("##########################");
        content.setInterFaceStrategy(interFaceStrategyC);
        content.chaojia();

    }
}
