package com.mw.java.test.designModels.factorymodel.singleModel;

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
     * 双重校验锁
     */
    private volatile static SingleModel singleton_shuangchong;

    /*构造函数私有化*/
    /*得到实例*/
    public static SingleModel getSingleton_shuangchong() {
        if (singleton_shuangchong == null) {
            synchronized (SingleModel.class) {
                if (singleton_shuangchong == null) {
                    singleton_shuangchong = new SingleModel();
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
