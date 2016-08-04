package com.mw.java.test;

import com.mw.java.test.java.map.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
//        System.out.println(s.toString());
//        System.out.println(s.subList(0,2).toString());
//        System.out.println(s.subList(0,1).toString());
        Collections.sort(s);
//        System.out.println(s.toString());

        Person p =new Person("张三",12);
        Person p_2 =new Person("李四",13);
        Person p_3 =new Person("王五",14);
        List s_3 = new ArrayList();
        s_3.add(p);
        s_3.add(p_2);
        s_3.add(p_3);
        Collections.sort(s_3);
        System.out.println(s_3.toString());




    }

     /*传进去的只能是3或者2*/
//    if (listCategory.size() > 1) {//最少2级目录
//        carParts.setCategoryId(listCategory.size() == 2 ? listCategory.get(1) : listCategory.get(2));
//    } else {//为空或者只有一级
//        carParts.setCategoryId(Constant.EMPTY);
//    }

}
