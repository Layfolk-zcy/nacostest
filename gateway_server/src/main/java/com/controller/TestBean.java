package com.controller;

/**
 * @author 微服务底座平台
 * @version 2.0.0
 * @title: TestBean
 * @projectName: erukeribbon
 * @description: test  bean
 * @date: 2022-09-08 19:00
 **/
public class TestBean {
    private String name;
    private String address;
    private String port;
    private String application;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
