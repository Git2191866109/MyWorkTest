package com.mw.java.test.fanxing;

import org.junit.Test;

/**
 * Created by mawei on 17/3/2.
 * 定义一个子类NumberPair，限定两个类型参数必须为Number
 */
public class NumberPair<U extends Number, V extends Number> extends Pair<U, V> {
    public NumberPair() {
    }

//    public NumberPair(U first, V second) {
//        super(first, second);
//    }

    public double sum() {
        return getFirst().doubleValue()
                + getSecond().doubleValue();
    }

    /**
     * T表示一种数据类型，必须实现Comparable接口，且必须可以与相同类型的元素进行比较
     *
     * @param arr
     * @param <T>
     * @return
     */
    public <T extends Comparable<T>> T max(T[] arr) {
        T max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(max) > 0) {
                max = arr[i];
            }
        }
        return max;
    }

    @Test
    public void testCompare() {
        Integer[] integers = new Integer[10];
        for (int i = 0; i < 10; i++) {
            integers[i] = i;
        }

        System.out.println(max(integers));
    }

    @Test
    public void test() {
        NumberPair<Integer, Double> pair = new NumberPair<>();
        pair.setFirst(10);
        pair.setSecond(12.34);
        double sum = pair.sum();
        System.out.println(sum);
    }
}
