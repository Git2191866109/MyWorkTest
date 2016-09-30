package com.mw.java.test.invocationTest;

/**
 * Created by wei.ma on 2016/9/30.
 */
public class HelloWorldImpl implements IHelloWorld {
    @Override
    public void say(String helloworld) {
        System.out.println("Hello world!!!");
    }
}
