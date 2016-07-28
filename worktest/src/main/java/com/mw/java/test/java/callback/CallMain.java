package com.mw.java.test.java.callback;

/**
 * Created by mawei on 16/7/28.
 * new 了一个业务处理类,处理业务逻辑,并在处理完后执行callback函数
 */
public class CallMain {
    public static void main(String[] args) {
        /**
         * 传递的第二个参数是实现了回调接口的匿名类对象
         */
        new TestCallBack().compute(100, new ComputeCallBack() {
            @Override
            public void onComputeEnd() {
                System.out.println("end back");
            }
        });
    }
}
