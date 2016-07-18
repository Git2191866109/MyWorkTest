package com.mw.java.test.sortsMath;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by mawei on 2016/7/8.
 * 直接插入排序
 */
public class DirectInsert {
    private int[] preAry = null;

    @Before
    public void init() {
        preAry = new int[]{21, 25, 49, 25, 16, 8};
    }

    /**
     * 假设当前待比较项前面的数都是已经排好序的,(n-1) [n>=2] 则拿当前待比较项与前面最后一个数比较
     * 如果比前面的数小，则把前面的数后移,索引减一  直到它比前面的数大则退出当前循环
     * 当记录比较少时，可以选择
     */
    @Test
    public void directInsert() {
        for (int i = 1; i < preAry.length; i++) {//假设第一个元素是排好序的,从第二个元素循环整个数组
            //记录当前元素的索引
            int j = i;
            int temp = preAry[j];
            //循环将当前的值与前面的值进行比较,如果当前的值比前面的元素小，则将前面的值向后移(复制),再将索引向前移动,直到移动到数组的开头索引0位置
            while (j > 0 && temp < preAry[j - 1]) {
                preAry[j] = preAry[j - 1];
                j--;
            }
            //将当前的值放到合适的位置
            preAry[j] = temp;
        }
    }

    @After
    public void print() {
        for (Integer value : preAry) {
            System.out.println(value);
        }
    }
}
