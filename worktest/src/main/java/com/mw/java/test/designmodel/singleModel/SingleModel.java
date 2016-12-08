package com.mw.java.test.designmodel.singleModel;

/**
 * Created by mawei on 2016/8/1.
 */
public class SingleModel {
    /**
     * 懒汉，线程不安全
     * 致命的是在多线程不能正常工作。
     */
    private static SingleModel instance_lanhan;

    private SingleModel() {
    }

    public static SingleModel getInstance_lanhan_nosafe() {
        if (instance_lanhan == null) {
            instance_lanhan = new SingleModel();
        }
        return instance_lanhan;
    }

    /**
     * 懒汉，线程安全
     * 效率很低，99%情况下不需要同步。
     */
    public static synchronized SingleModel getInstance_lanhan_safe() {
        if (instance_lanhan == null) {
            instance_lanhan = new SingleModel();
        }
        return instance_lanhan;
    }

    /**
     * 饿汉式:很饿，一上来就new
     */
    private static SingleModel instance_ehanshi = new SingleModel();

    /*构造函数私有化*/
    /*得到实例*/
    public static SingleModel getInstance_ehanshi() {
        return instance_ehanshi;
    }

    /**
     * 双重校验锁——bug版本
     * 这里有个问题：如果多个实例调用getSingleton_shuangchong方法，
     * 当 singleton_shuangchong 为 null 时，两个线程可以并发地进入 if 语句内部。然后，一个线程进入 synchronized 块来初始化 instance，而另一个线程则被阻断。
     * 当第一个线程退出 synchronized 块时，等待着的线程进入并创建另一个 Singleton 对象。
     * 注意：当第二个线程进入 synchronized 块时，它并没有检查 instance 是否非 null。
     *
     * 声明成 volatile 的变量被认为是顺序一致的，即，不是重新排序的。
     */
    private volatile static SingleModel singleton_shuangchong;

    /*构造函数私有化*/
    /*得到实例*/
    public static SingleModel getSingleton_shuangchong() {
        if (singleton_shuangchong == null) {
            synchronized (SingleModel.class) {  // 1
                singleton_shuangchong = new SingleModel();// 3
            }
        }
        return singleton_shuangchong;
    }

    /**
     * 双重校验锁——安全
     * 加了 //2步骤
     */
    public static SingleModel getSingleton_shuangchong_safe() {
        if (singleton_shuangchong == null) {
            if (singleton_shuangchong == null) {
                synchronized (SingleModel.class) {  // 1
                    if (singleton_shuangchong == null) {// 2
                        singleton_shuangchong = new SingleModel();// 3
                    }
                }
            }
        }
        return singleton_shuangchong;
    }


    /**
     * 静态内部类:既实现了线程安全，又避免了同步带来的性能影响
     */
    private static class LazyHolder {
        private static final SingleModel INSTANCE = new SingleModel();
    }

    public static final SingleModel getInstance() {
        return LazyHolder.INSTANCE;
    }

}
