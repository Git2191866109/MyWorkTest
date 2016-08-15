package com.mw.java.test.Mianshi;

/**
 * Created by mawei on 2016/8/15.
 */
public class ComputValue {
    public void method(int val) {
        System.out.println("method output.... " + val);
    }

    public void method(String val) {
        System.out.println("method output.... " + val);
    }

    public static void main(String[] args) {
        ComputValue computValue = new ComputValue();
        computValue.method('g');
        computValue.method(103);
    }
}
