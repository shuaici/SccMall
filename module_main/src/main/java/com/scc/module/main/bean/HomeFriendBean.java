package com.scc.module.main.bean;

public class HomeFriendBean {
    private String name;
    private String head;
    private String introduction;

    public HomeFriendBean(String name, String head, String introduction) {
        this.name = name;
        this.head = head;
        this.introduction = introduction;
    }

    public HomeFriendBean(String name) {
        this.name = name;
        head="";
        this.introduction="出师一表真名世，千载谁堪伯仲间。";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
