package com.mw.java.test.domain;

/**
 * Created by mawei on 2016/6/23.
 * 应用场景：Bean中需要直接注入Properties配置文件中的值 。
 * 例如下面的代码中需要获取上述demo-remote.properties中的值
 */
public class Client {
    private String ip;
    private String port;
    private String service;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

}
