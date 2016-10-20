package com.mw.java.test.designModels.factorymodel.celue.demo;

/**
 * Created by wei.ma on 2016/10/9.
 * 原价，八折，七折和半价
 */
//我们使用嵌套注解，并且制定我们打折的各个策略顺序是99，这算是很靠后的
//因为我们最后打折算出来钱是最多的，这个一算就很清楚，LZ不再解释数学问题
@TotalValidRegion(@ValidRegion(max = 1000, order = 99))
class Common implements CalPrice {

    public Double calPrice(Double originalPrice) {
        return originalPrice;
    }

}

@TotalValidRegion(@ValidRegion(min = 1000, max = 2000, order = 99))
class Vip implements CalPrice {

    public Double calPrice(Double originalPrice) {
        return originalPrice * 0.8;
    }

}

@TotalValidRegion(@ValidRegion(min = 2000, max = 3000, order = 99))
class SuperVip implements CalPrice {

    public Double calPrice(Double originalPrice) {
        return originalPrice * 0.7;
    }

}

@TotalValidRegion(@ValidRegion(min = 3000, order = 99))
class GoldVip implements CalPrice {

    public Double calPrice(Double originalPrice) {
        return originalPrice * 0.5;
    }

}

@OnceValidRegion(@ValidRegion(min = 1000, max = 2000, order = 40))
class OneTDTwoH implements CalPrice {

    public Double calPrice(Double originalPrice) {
        return originalPrice - 200;
    }

}

@OnceValidRegion(@ValidRegion(min = 2000, order = 40))
class TwotDFourH implements CalPrice {

    public Double calPrice(Double originalPrice) {
        return originalPrice - 400;
    }

}
