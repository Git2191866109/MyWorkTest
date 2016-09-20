package com.mw.java.test.domain;

/**
 * Created by wei.ma on 2016/9/14.
 */
public class Weibo {
    private int id;
    private String city;

    public Weibo() {
    }

    public Weibo(int s, String s1) {
        this.id = s;
        this.city = s1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Weibo{" +
                "id=" + id +
                ", city='" + city + '\'' +
                '}';
    }
}
