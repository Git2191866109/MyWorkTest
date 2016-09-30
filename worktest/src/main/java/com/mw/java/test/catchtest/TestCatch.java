package com.mw.java.test.catchtest;

/**
 * Created by wei.ma on 2016/9/30.
 */
public class TestCatch {
    public static void main(String[] args) {
        System.out.println(CatchManager.getSimpleFlag("alksd"));
        CatchManager.putCache("abc", new Cache());
        CatchManager.putCache("def", new Cache());
        CatchManager.putCache("ccc", new Cache());
        CatchManager.clearOnly("");
        Cache c = new Cache();
        for (int i = 0; i < 10; i++) {
            CatchManager.putCache("" + i, c);
        }
        CatchManager.putCache("aaaaaaaa", c);
        CatchManager.putCache("abchcy;alskd", c);
        CatchManager.putCache("cccccccc", c);
        CatchManager.putCache("abcoqiwhcy", c);
        System.out.println("删除前的大小："+CatchManager.getCacheSize());
        System.out.println("删除前的大小："+ CatchManager.getCacheAllkey());
        CatchManager.clearAll("aaaa");
        System.out.println("删除后的大小："+CatchManager.getCacheSize());
        System.out.println("删除后的大小："+ CatchManager.getCacheAllkey());


    }
}
