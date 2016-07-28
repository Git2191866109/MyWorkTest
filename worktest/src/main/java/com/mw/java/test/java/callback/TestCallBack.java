package com.mw.java.test.java.callback;

/**
 * Created by mawei on 16/7/28.
 * 回调函数的业务处理类
 */
public class TestCallBack {
    public void compute(int n,ComputeCallBack callBack){
        for (int i = 0; i < n; i++) {
            System.out.println(i);
        }
        callBack.onComputeEnd();
    }

}
