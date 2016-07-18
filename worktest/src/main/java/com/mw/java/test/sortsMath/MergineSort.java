package com.mw.java.test.sortsMath;

/**
 * Created by mawei on 2016/7/18.
 * 基本排序：归并（Merge）排序法是将两个（或两个以上）有序表合并成一个新的有序表，即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列。
 * 归并排序是建立在归并操作上的一种有效的排序算法。
 * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 归并排序是一种稳定的排
 * 步骤：
 * 1、Divide: 把长度为n的输入序列分成两个长度为n/2的子序列。
 * 2、Conquer: 对这两个子序列分别采用归并排序。
 * 3、Combine: 将两个排序好的子序列合并成一个最终的排序序列。
 */
public class MergineSort {
    int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25,
            53, 51};

    public MergineSort() {
        sort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++)
            System.out.println(a[i]);
    }

    /**
     * @param data  待排序数组
     * @param left  数组起始位置
     * @param right 数组结束位置
     */
    public void sort(int[] data, int left, int right) {
        if (left < right) {//表明可以继续拆分
            // 找出中间索引
            int center = (left + right) / 2;
            // 对左边数组进行递归
            sort(data, left, center);
            // 对右边数组进行递归
            sort(data, center + 1, right);
            // 合并
            merge(data, left, center, right);

        }
    }

    /**
     * @param data排序完的原数组
     * @param left        起始位置
     * @param center      中间位置
     * @param right       结束位置
     */
    public void merge(int[] data, int left, int center, int right) {
        int[] tmpArr = new int[data.length];//中间临时数组
        int mid = center + 1;
        // temp记录中间数组的索引 -->就是合并这两个数组的大数组的索引
        int temp = left;
        while (left <= center && mid <= right) {
            // 从两个数组中取出最小的放入中间数组
            if (data[left] <= data[mid]) {
                tmpArr[temp] = data[left];
                left++;
                temp++;
            } else {
                tmpArr[temp] = data[mid];
                mid++;
                temp++;
            }
        }
        // 剩余部分依次放入中间数组(见上面的合并图解)
        while (mid <= right) {
            tmpArr[temp] = data[mid];
            mid++;
            temp++;

        }
        while (left <= center) {
            tmpArr[temp] = data[left];
            left++;
            temp++;
        }
        // 将中间数组中的内容复制回原数组
        for (int i = 0; i <= right; i++) {
            data[i] = tmpArr[i];
        }

    }

}
