package com.scc.module.main.bean;

import java.io.Serializable;

public class UserSerializable implements Serializable {
    private static final long serialVersionUID = 1522126340746830861L;
    public String name;
    public int age = 0;

    public UserSerializable(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
