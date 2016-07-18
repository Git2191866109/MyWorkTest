package com.mw.java.test.sortsMath;

/**
 * Created by mawei on 2016/7/18.
 * 基本思想：将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后,数列就变成一个有序序列。
 * 基数排序是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。
 * 由于整数也可以表达字符串（比如名字或日期）和特定格式的浮点数，
 * 所以基数排序也不是只能使用于整数。
 * 步驟：
 * 1、将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。
 * 2、从最低位开始，依次进行一次排序。
 * 3、这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。
 */
public class RadixSort {
    public static int[] radixSortAsc(int[] arr) {
        // 从低位往高位循环
        for (int d = 1; d <= getMax(arr); d++) {
            // 临时数组，用来存放排序过程中的数据
            int[] tmpArray = new int[arr.length];
            // 位记数器，从第0个元素到第9个元素依次用来记录当前比较位是0的有多少个...是9的有多少个数
            int[] count = new int[10];
            // 开始统计0有多少个，并存储在第0位，再统计1有多少个，并存储在第1位..依次统计到9有多少个
            // { 73, 22, 93, 43, 55, 14, 28, 65, 39, 81, 33, 10 };
            for (int i = 0; i < arr.length; i++) {
                count[digit(arr[i], d)] += 1;// 统计该位上有多少个数字 比如第一位上0有多少个
            }
            /*
             * 比如某次经过上面统计后结果为：[0, 2, 3, 3, 0, 0, 0, 0, 0, 0]则经过下面计算后 结果为： [0, 2,
             * 5, 8, 8, 8, 8, 8, 8, 8]但实质上只有如下[0, 2, 5, 8, 0, 0, 0, 0, 0, 0]中
             * 非零数才用到，因为其他位不存在，它们分别表示如下：2表示比较位为1的元素可以存放在索引为1、0的
             * 位置，5表示比较位为2的元素可以存放在4、3、2三个(5-2=3)位置，8表示比较位为3的元素可以存放在
             * 7、6、5三个(8-5=3)位置
             */
            for (int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }

            /*
             * 注，这里只能从数组后往前循环，因为排序时还需保持以前的已排序好的 顺序，不应该打
             * 乱原来已排好的序，如果从前往后处理，则会把原来在前面会摆到后面去，因为在处理某个
             * 元素的位置时，位记数器是从大到到小（count[digit(arr[i], d)]--）的方式来处
             * 理的，即先存放索引大的元素，再存放索引小的元素，所以需从最后一个元素开始处理。
             * 如有这样的一个序列[212,213,312]，如果按照从第一个元素开始循环的话，经过第一轮
             * 后（个位）排序后，得到这样一个序列[312,212,213]，第一次好像没什么问题，但问题会
             * 从第二轮开始出现，第二轮排序后，会得到[213,212,312]，这样个位为3的元素本应该
             * 放在最后，但经过第二轮后却排在了前面了，所以出现了问题
             */
            for (int i = arr.length - 1; i >= 0; i--) {// 只能从最后一个元素往前处理
                // for (int i = 0; i < arr.length; i++) {//不能从第一个元素开始循环
                tmpArray[count[digit(arr[i], d)] - 1] = arr[i];
                count[digit(arr[i], d)]--;
            }
            //System.arraycopy(tmpArray, 0, arr, 0, tmpArray.length);
            for (int i = 0; i < arr.length; i++) {
                arr[i] = tmpArray[i];
            }
        }
        return arr;
    }

    //求出最大数的位数的函数
    public static int getMax(int[] array) {
        // 取出最大数然后求出最大的位数
        int max = array[0];
        for (int j = 1; j < array.length; j++) {
            if (array[j] > max) {
                max = array[j];
            }
        }
        int time = 0;
        // 判断位数;
        while (max > 0) {
            max /= 10;
            time++;
        }
        return time;
        // return String.valueOf(max).length();也可以根据字符串长度返回
    }

    /**
     * 取数xxx上的第d位数字
     *
     * @param x 数字
     * @param d 第几位，从低位到高位
     * @return
     */
    public static int digit(int num, int d) {

        int pow = 1;
        while (--d > 0) {
            pow *= 10;
        }
        return num / pow % 10;
    }

    public static void main(String[] args) {
        int[] data = {73, 22, 93, 43, 55, 14, 28, 65, 39, 81, 33, 10};

        System.out.println(radixSortAsc(data));
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
    }
}
