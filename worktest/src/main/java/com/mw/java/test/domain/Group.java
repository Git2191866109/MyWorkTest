package com.mw.java.test.domain;

import java.util.List;

/**
 * Created by wei.ma on 2016/9/14.
 */
public class Group {
    private int id;
    private String name;
    private List list;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
