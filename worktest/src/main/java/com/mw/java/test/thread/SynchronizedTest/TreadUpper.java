package com.mw.java.test.thread.SynchronizedTest;

/**
 * Created by mawei on 2016/7/7.
 */
public class TreadUpper {
    /**
     * synchronized 关键字，它包括两种用法：synchronized 方法和 synchronized 块。
     */
    /**
     * synchronized 方法：通过在方法声明中加入 synchronized关键字来声明 synchronized 方法。
     * synchronized 方法控制对类成员变量的访问：
     * 每个类实例对应一把锁，每个 synchronized 方法都必须获得调用该方法的类实例的锁方能执行，否则所属线程阻塞，
     * 方法一旦执行，就独占该锁，直到从该方法返回时才将锁释放，此后被阻塞的线程方能获得该锁，重新进入可执行状态。
     * 这种机制确保了同一时刻对于每一个类实例，其所有声明为 synchronized 的成员函数中至多只有一个处于可执行状态（因为至多只有一个能够获得该类实例对应的锁），
     * 从而有效避免了类成员变量的访问冲突（只要所有可能访问类成员变量的方法均被声明为 synchronized）
     */
    /**
     *  synchronized 块：通过 synchronized关键字来声明synchronized 块。
     *  语法如下：
             synchronized(syncObject) {
             //允许访问控制的代码
             }
     * synchronized 块是这样一个代码块，其中的代码必须获得对象 syncObject （如前所述，可以是类实例或类）的锁方能执行，具体机制同前所述。
     * 由于可以针对任意代码块，且可任意指定上锁的对象，故灵活性较高。
     */
    /**
     * 对synchronized(this)的一些理解
     * 一、当两个并发线程访问同一个对象object中的这个synchronized(this)同步代码块时，一个时间内只能有一个线程得到执行。另一个线程必须等待当前线程执行完这个代码块以后才能执行该代码块。
     * 二、然而，当一个线程访问object的一个synchronized(this)同步代码块时，另一个线程仍然可以访问该object中的非synchronized(this)同步代码块。
     * 三、尤其关键的是，当一个线程访问object的一个synchronized(this)同步代码块时，其他线程对object中所有其它synchronized(this)同步代码块的访问将被阻塞。
     * 四、第三个例子同样适用其它同步代码块。也就是说，当一个线程访问object的一个synchronized(this)同步代码块时，它就获得了这个object的对象锁。结果，其它线程对该object对象所有同步代码部分的访问都被暂时阻塞。
     */
}
