package com.mw.java.test.designModels.factorymodel.celue.xuexi;

/**
 * Created by wei.ma on 2016/10/9.
 * 定义所有支持的算法的公共接口
 * 对同一数据的不同处理方式封装起来，继承于统一的接口，就可以封装这些变化，达到扩展性的目的
 */
public interface InterFaceStrategy {

    /**
     * 打丈夫的方式
     */
    void hitHusband();
}
