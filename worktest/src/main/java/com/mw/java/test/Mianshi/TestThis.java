package com.mw.java.test.Mianshi;

/**
 * Created by mawei on 2016/8/15.
 */
public class TestThis {
    private int num = 100;

    public void countOne(B b) {
        int num = 101;
        countTwo(num);
        System.out.println("num1=" + num + "");
        b.add(num);
    }

    public void countTwo(int num) {
        /*此处this引用的是全局变量*/
        num = this.num;
        System.out.println("num2= " + this.num + "");
    }

    public static void main(String[] args) {
        TestThis testThis = new TestThis();
        B b = testThis.new B();
        testThis.countOne(b);
        b.print();
    }

    private class B {
        private int num = 100;

        public B add(int n) {
            num = num++ + n;
            return this;
        }

        public void print() {
            System.out.println("num3 = " + num + "");
        }
    }
}
