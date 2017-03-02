package com.mw.java.test.fanxing;

import org.junit.Test;

/**
 * Created by mawei on 17/3/2.
 */
public class Pair<U, V> {
    U first;
    V second;

    public Pair() {
    }

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public U getFirst() {
        return first;
    }

    public void setFirst(U first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }


    /**
     * 泛型方法
     */
    public static <T> int indexOf(T[] arr, T elm) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(elm)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 参数可以有多个，多个以逗号分隔
     * @param first
     * @param second
     * @param <U>
     * @param <V>
     * @return
     */
    public static <U, V> Pair<U, V> makePair(U first, V second) {
        Pair<U, V> pair = new Pair<>(first, second);
        return pair;
    }


    public static void testIndexOf() {
        System.out.println(indexOf(new Integer[]{1, 3, 5}, 3));
        System.out.println(indexOf(new String[]{"hello", "老马", "编程"}, "老马"));
    }

    public static void main(String[] args) {
//        Pair<String, Integer> pair = new Pair<String, Integer>("老马", 100);
//        String id = pair.getFirst();
//        Integer name = pair.getSecond();
//        System.out.println(id + "..." + name);
        testIndexOf();
        /*这里并不需要告诉编译器给的参数是什么类型,编译器可以自行推断出来*/
        makePair(1,"老马");
    }
}
