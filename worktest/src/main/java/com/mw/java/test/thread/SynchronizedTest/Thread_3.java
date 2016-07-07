package com.mw.java.test.thread.SynchronizedTest;

/**
 * Created by mawei on 2016/7/7.
 * 尽管线程t1获得了对Inner的对象锁，但由于线程t2访问的是同一个Inner中的非同步部分。所以两个线程互不干扰。
 */
public class Thread_3 {
    class Inner {
        private void m4t1() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : Inner.m4t1()=" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        }

        private void m4t2() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : Inner.m4t2()=" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        }

        //        现在在Inner.m4t3()前面加上synchronized：
        /*尽管线程t1与t3访问了同一个Inner对象中两个毫不相关的部分,但因为t1先获得了对Inner的对象锁，所以t3对Inner.m4t3()的访问也被阻塞，因为m4t3()是Inner中的一个同步方法。*/
        private synchronized void m4t3() {
            int i = 5;
            while (i-- > 0) {
                System.out.println(Thread.currentThread().getName() + " : Inner.m4t3()=" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                }
            }
        }
    }

    private void m4t1(Inner inner) {
        synchronized (inner) { //使用对象锁
            inner.m4t1();
        }
    }

    private void m4t2(Inner inner) {
        inner.m4t2();
    }

    private void m4t3(Inner inner) {
        inner.m4t3();
    }

    public static void main(String[] args) {
        final Thread_3 myt3 = new Thread_3();
        final Inner inner = myt3.new Inner();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                myt3.m4t1(inner);
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                myt3.m4t2(inner);
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                myt3.m4t3(inner);
            }
        }, "t3");
        t1.start();
//        t2.start();
        t3.start();
    }

}
