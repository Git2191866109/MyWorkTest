package com.mw.java.test.invocationTest;

import java.lang.reflect.Proxy;

/**
 * Created by wei.ma on 2016/9/30.
 */
public class TestInvocationMain {
    public static void main(String[] args) {
        /*创建被代理类*/
        IHelloWorld helloWorld = new HelloWorldImpl();
        /*创建动态代理的handler*/
        HelloWorldHandler helloWorldHandler = new HelloWorldHandler(helloWorld);
        /*创建动态代理对象*/
        IHelloWorld proxyObj = (IHelloWorld) Proxy.newProxyInstance(
                helloWorld.getClass().getClassLoader(),
                helloWorld.getClass().getInterfaces(),
                helloWorldHandler
        );

        proxyObj.say("helloworld");

    }
}
