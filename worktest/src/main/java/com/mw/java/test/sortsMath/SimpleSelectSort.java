package com.mw.java.test.sortsMath;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mawei on 2016/7/8.
 * 希尔排序
 */
public class SimpleSelectSort {
    private int[] preAry = null;

    @Before
    public void init() {
        preAry = new int[]{49, 38, 65, 97, 76, 13, 27, 48, 55, 4};
    }

    /**
     * 在要排序的一组数中，选出最小的一个数与第一个位置的数交换；
     * 然后在剩下的数当中再找最小的与第二个位置的数交换，如此循环到倒数第二个数和最后一个数比较为止
     */
    @Test
    public void simpleSelectSort() {
        int position = 0;
        for (int i = 0; i < preAry.length; i++) {
            int j = i + 1;
            position = i;
            int temp = preAry[i];
            for (; j < preAry.length; j++) {
                if (preAry[j] < temp) {
                    temp = preAry[j];
                    position = j;
                }
            }
            preAry[position] = preAry[i];
            preAry[i] = temp;
        }
    }

    @After
    public void print() {
        for (Integer value : preAry) {
            System.out.println(value);
        }
    }
}
