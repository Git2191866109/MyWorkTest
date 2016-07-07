package com.mw.java.test.thread.SynchronizedTest;

/**
 * Created by mawei on 2016/7/7.
 */
public class Thread_2 {
    public void m4t1() {
        synchronized (this) {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException ie) {
                }
            }
        }
    }

    /*然而，当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。*/
    public void m4t2() {
        int i = 5;
        while (i-- > 0) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
            }
        }
    }

    /*尤其关键的是，当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。*/
    public void m4t3() {
        synchronized (this) {
            System.out.println("开始被阻塞的线程......");
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : " + i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ie) {
                }
            }
        }
    }

    public static void main(String[] args) {
        final Thread_2 myt2 = new Thread_2();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                myt2.m4t1();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                myt2.m4t2();
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                myt2.m4t3();
            }
        }, "t3");
        t1.start();
        t2.start();
        t3.start();
    }
}
