package com.mykins.linkin.bean;

/**
 * Created by yjn on 2017/11/4.
 */

public class KinsBean {
    String name;
    String description;
    String avatar;
    private String kinsTitle;

    public KinsBean(String avatar, String name, String description) {
        this.avatar = avatar;
        this.name = name;
        this.description = description;
    }

    public KinsBean(String avatar, String name, String description, String kinsTitle) {
        this.avatar = avatar;
        this.name = name;
        this.description = description;
        this.kinsTitle = kinsTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getKinsTitle() {
        return kinsTitle;
    }

    public void setKinsTitle(String kinsTitle) {
        this.kinsTitle = kinsTitle;
    }
}
