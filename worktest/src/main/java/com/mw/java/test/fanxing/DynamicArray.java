package com.mw.java.test.fanxing;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by mawei on 17/3/1.
 */
public class DynamicArray<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public DynamicArray() {
        /*只要new了对象我们就有了一个初始化的elementData数组*/
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    /**
     * 扩展数组
     *
     * @param minCapacity
     */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elementData.length;
        /*这里说明容器的长度还够用*/
        if (oldCapacity >= minCapacity) {
            return;
        }
        /*如果长度大于默认的DEFAULT_CAPACITY,则进行动态的扩容*/
        int newCapacity = oldCapacity * 2;
        if (newCapacity < minCapacity)
            newCapacity = minCapacity;
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    public void add(E e) {
        ensureCapacity(size + 1);
        elementData[size++] = e;
    }

    public E get(int index) {
        return (E) elementData[index];
    }

    public E set(int index, E element) {
        E oldValue = get(index);
        elementData[index] = element;
        return oldValue;
    }

    public static void main(String[] args) {
        DynamicArray<Double> arr = new DynamicArray<Double>();
        Random rnd = new Random();
        int size = 1 + rnd.nextInt(100);
        for (int i = 0; i < size; i++) {
            arr.add(Math.random());
        }
        Double d = arr.get(rnd.nextInt(size));
        System.out.println(d);
    }
}
