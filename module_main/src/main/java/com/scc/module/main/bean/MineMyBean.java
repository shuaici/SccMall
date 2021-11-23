package com.scc.module.main.bean;

public class MineMyBean {
    private String name;
    private String url;
    private String introduce;
    private String platform;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public MineMyBean() {
    }

    public MineMyBean(String name, String url, String introduce, String platform) {
        this.name = name;
        this.url = url;
        this.introduce = introduce;
        this.platform = platform;
    }
}
