package com.mw.java.test.Mianshi;

/**
 * Created by mawei on 2016/8/15.
 * 代码的执行顺序
 */
public class A {
    public static void main(String[] args) {
        new Test();
    }
}

class Apple {
    Apple(String s) {
        System.out.println(s);
    }
    /*其实这里已经覆盖了默认的构造函数，这里就没有意义了*/
    Apple() {
        System.out.println("App默认构造函数被调用");
    }
}

class Test {
    static Apple app = new Apple("静态成员app初始化");
    Apple app1 = new Apple("app1成员初始化");

    static {
        System.out.println("static 块执行");
        if (app == null) {
            System.out.println("app is null");
        }
        app = new Apple("静态块内部初始化app成员变量");
    }

    Test() {
        System.out.println("Test默认的构造函数被调用");
    }
}
