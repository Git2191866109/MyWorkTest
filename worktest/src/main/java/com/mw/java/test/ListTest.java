package com.mw.java.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mawei on 2016/6/21.
 */
public class ListTest {
    public static void main(String[] args) {
        List s = new ArrayList();
        s.add(3);
        s.add(2);
        s.add(1);
//        System.out.println(s.size() == 2 ? s.get(1) : s.get(2));
        System.out.println(s.toString());
        System.out.println(s.subList(0,2).toString());
        System.out.println(s.subList(0,1).toString());
    }

     /*传进去的只能是3或者2*/
//    if (listCategory.size() > 1) {//最少2级目录
//        carParts.setCategoryId(listCategory.size() == 2 ? listCategory.get(1) : listCategory.get(2));
//    } else {//为空或者只有一级
//        carParts.setCategoryId(Constant.EMPTY);
//    }

}
