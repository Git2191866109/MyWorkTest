package com.mw.java.test.java.nio;

import java.nio.IntBuffer;

/**
 * Created by mawei on 16/7/20.
 */
public class JavaNIO {
    public static void main(String[] args) {
        /**
         * intbuffer\charbuffer\bytebuffer\shortbuffer\longbuffer\doublebuffer
         * buffer中三个关键的值:
         *  position:下一个缓存区读取或者写入的操作的指针
         *  limit:表示还有多少数据需要存储或者读取
         *  capacity:缓存区的最大容量
         */
        /*开辟容量为100大小的IntBuffer*/
        intBuffer();

    }

    private static void intBuffer() {
        IntBuffer intBuffer = IntBuffer.allocate(100);
        System.out.println("position " + intBuffer.position() + ",limit " + intBuffer.limit() + ",capacity " + intBuffer.capacity());
        int[] data = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        intBuffer.put(100);//向缓存区写入一个整数
        intBuffer.put(data);
        System.out.println("position " + intBuffer.position() + ",limit " + intBuffer.limit() + ",capacity " + intBuffer.capacity());
        intBuffer.flip();//重设缓存区
        System.out.println("position " + intBuffer.position() + ",limit " + intBuffer.limit() + ",capacity " + intBuffer.capacity());
//        intBuffer.reset();

        /*输出缓存区内容*/
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
