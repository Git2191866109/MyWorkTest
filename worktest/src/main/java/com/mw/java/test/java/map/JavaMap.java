package com.mw.java.test.java.map;

import java.util.*;

/**
 * Created by mawei on 16/7/20.
 * 在实际的工作中,map一般都是做为查询来使用,如果要输出key-value的全部内容需要基于
 * map.entrySet构建的集合来访问key-value
 */
public class JavaMap {
    /**
     * java中的引用类型:
     * a)强引用:在内存不足时,jvm就是出现OOM错误也不回收对象
     * b)软引用:在内存不足时,会回收对象用来实现内存敏感的高速缓存
     * c)弱引用:无论内存是否紧张,被GC发现都被立即回收
     * d)虚引用:和没有引用是一样的
     *
     * @param args
     */
    public static void main(String[] args) {
        mapbasic();
//        treeMap();
//        weakHashMap();
//        indentityHashMap();
//        sortedHashMap();

    }

    /**
     *
     */
    private static void sortedHashMap() {
        SortedMap<String, Integer> data = new TreeMap<String, Integer>();
        data.put("hadoop", 10);
        data.put("spark", 11);
        data.put("kafka", 7);
        data.put("flume", 9);
        data.put("storm", 17);
        System.out.println(data.headMap("hadoop"));
        System.out.println(data.tailMap("kafka"));
        System.out.println(data.subMap("kafka", "spark"));
        System.out.println("======================");
        Set<String> keys = data.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("======================");
        Collection<Integer> values = data.values();
        Iterator<Integer> valuesitor = values.iterator();
        while (valuesitor.hasNext()) {
            System.out.println(valuesitor.next());
        }
    }

    /**
     * 使用IdentityHashMap可以允许map中的key重复
     */
    private static void indentityHashMap() {
        Map<Person, String> data = new IdentityHashMap<Person, String>();
//        Map<Person, String> data = new HashMap<Person, String>();
        data.put(new Person("hadoop", 10), "hadoop-10");
        data.put(new Person("spark", 18), "spark-18");
        data.put(new Person("kafka", 9), "kafka-9");
        data.put(new Person("flume", 7), "flume-7");
        data.put(new Person("flume", 7), "flume-7");
        Set<Map.Entry<Person, String>> entrySet = data.entrySet();
        Iterator<Map.Entry<Person, String>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Person, String> item = iterator.next();
            System.out.println(item.getKey() + "->" + item.getValue());
        }
    }

    /**
     * weakhashmap能够在具体的元素不被使用的情况下通过垃圾回收来自动清理暂时不需要使用的数据
     * 这可以极大的减少内存的浪费
     * 它在实例化的时候key的设置必须是new出来的引用对象,例如new String,这样在
     * System.gc的时候就会立即触发回收行为
     */
    private static void weakHashMap() {
        Map<String, Integer> data = new WeakHashMap<>();
        data.put("hadoop", 10);
        data.put("spark", 11);
        data.put("kafka", 7);
        data.put("flume", 7);
        data.put(new String("hadoop"), 10);
        data.put(new String("spark"), 18);
        data.put(new String("kafka"), 9);
        data.put(new String("flume"), 7);
        /*手动触发GC*/
        System.gc();
        System.out.println(data);
    }

    /**
     * 使用Treemap构建的集合元素会按照key进行排序,所以要保证key已经实现了Comparable接口
     * 例如java中的String类型已经实现了Comparable接口,所以就可以直接使用
     * 如果使用了自定义的类做为key,必须实现Comparable接口
     */
    private static void treeMap() {
        Map<String, Integer> data = new TreeMap<String, Integer>();
        data.put("hadoop", 10);
        data.put("spark", 11);
        data.put("kafka", 7);
        System.out.println("======================");
        Set<String> keys = data.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("======================");
        Collection<Integer> values = data.values();
        Iterator<Integer> valuesitor = values.iterator();
        while (valuesitor.hasNext()) {
            System.out.println(valuesitor.next());
        }

    }

    private static void mapbasic() {
        Map<String, Integer> data = new HashMap<String, Integer>();
        data.put("hadoop", 10);
        data.put("spark", 6);

        if (data.containsKey("spark")) {
            System.out.println("spark is here");
        }

        if (data.containsValue(10)) {
            System.out.println("hadoop is here");
        }
        System.out.println("======================");
        Set<String> keys = data.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("======================");
        Collection<Integer> values = data.values();
        Iterator<Integer> valuesitor = values.iterator();
        while (valuesitor.hasNext()) {
            System.out.println(valuesitor.next());
        }
        System.out.println("========遍历map==============");
        for (Map.Entry<String, Integer> item : data.entrySet()) {
            System.out.println(item.getKey() + " -> " + item.getValue());
        }
    }

}
