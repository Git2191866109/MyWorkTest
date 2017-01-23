package com.mw.java.test.domain;

/**
 * Created by wei.ma on 2016/9/14.
 */
public class User {
    private int id;
    private String name;
    private Boolean noIsTest; //  在framker中不要以is开头
    private Boolean isTest; //  在framker中如果以is开头：1. 改boolean 为Boolean 2. 将生成的get set方法中的is加上

    public User() {
    }

    public User(String name, Boolean noIsTest, Boolean isTest) {
        this.name = name;
        this.noIsTest = noIsTest;
        this.isTest = isTest;
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

    public Boolean getNoIsTest() {
        return noIsTest;
    }

    public void setNoIsTest(Boolean noIsTest) {
        this.noIsTest = noIsTest;
    }

    public Boolean getIsTest() {
        return isTest;
    }

    public void setIsTest(Boolean isTest) {
        isTest = isTest;
    }
}
