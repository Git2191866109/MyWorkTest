package com.mw.java.test.sortsMath;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mawei on 2016/7/8.
 * 希尔排序
 */
public class ShellSort {
    private int[] preAry = null;

    @Before
    public void init() {
        preAry = new int[]{49, 38, 65, 97, 76, 13, 27, 48, 55, 4};
    }

    /**
     * 算法先将要排序的一组数按某个增量d（n/2,n为要排序数的个数）分成若干组，
     * 每组中记录的下标相差d.对每组中全部元素进行直接插入排序，然后再用一个较小的增量（d/2）对它进行分组，
     * 在每组中再进行直接插入排序。当增量减到1时，进行直接插入排序后，排序完成。
     */
    @Test
    public void shellSort() {
        int temp = 0;
        double d1 = preAry.length;
        while (true) {
            d1 = Math.ceil(d1 / 2);
            int d = (int) d1;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < preAry.length; i += d) {
                    int j = i - d;
                    temp = preAry[i];
                    for (; j >= 0 && temp < preAry[j]; j -= d) {
                        preAry[j + d] = preAry[j];
                    }
                    preAry[j + d] = temp;
                }
            }
            if (d == 1) {
                break;
            }
        }
    }

    @After
    public void print() {
        for (Integer value : preAry) {
            System.out.println(value);
        }
    }
}
