package com.mw.java.test.sortsMath;

/**
 * Created by mawei on 2016/7/18.
 */
public class QuickSort {
    /**
     * 选择一个基准元素,通常选择第一个元素或者最后一个元素,
     * 通过一趟扫描，将待排序列分成两部分,一部分比基准元素小,一部分大于等于基准元素,此时基准元素在其排好序后的正确位置,
     * 然后再用同样的方法递归地排序划分的两部分。
     * @param a
     * @param left
     * @param right
     */
    public static void quickSort(int[] a, int left, int right) {
        //快速排序算法
        //找一个基准元素,把数组分为两个部分,前面一部分比基准元素小  后面一部分比基准元素大 保证前面一部分的元素都小于后面一部分的元素
        //一般以第一个元素作为基准元素
        //找两个助手 ,低助手和高助手  ,分别位于数组的两端
        //低助手从左往右找比基准元素大的元素 找到则交换 记录该位置 找不到的话 低助手索引加1
        //高助手从右往前找比基准元素小的元素，找到则交换 ，记录该位置，找不到的话，低助手索引减1
        //直到低助手和高助手重叠 ,则记录该位置   ,数组就被分成了两个部分 再对这两部分进行递归,再分成两部分  最后合即为排好序的数组

        if (left == right) {
            return;
        }
        int partition = partition(a, left, right);
        //递归
        quickSort(a, left, partition);
        quickSort(a, partition + 1, right);

    }

    /**
     * 找中间位置的方法
     *
     * @param a      待排序数组
     * @param left   ：低助手   低助手从左往右找比基准元素大的元素 找到则交换 记录该位置 找不到的话 低助手索引加1
     * @param right: 高助手  高助手从右往前找比基准元素小的元素，找到则交换 ，记录该位置，找不到的话，低助手索引减1
     */
    public static int partition(int[] a, int left, int right) {
        int base = a[left];
        //找两个助手 ,低助手和高助手  ,分别位于数组的两端
        left--;
        right++;
        while (left < right) {
            left++;
            while (a[left] < base) {
                left++;
            }
            right--;
            while (a[right] > base) {
                right--;
            }

            if (left < right) {
                swap(a, left, right);
            }
        }
        return right;
    }

    //交换值的函数
    public static void swap(int[] a, int i, int j) {
        int temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
