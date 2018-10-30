package com.mykins.linkin.bean;

/**
 * Created by yjn on 2017/11/5.
 */

public class SearchResultBean {
    public final static int SEARCH_IM = 0, SEARCH_SHARE = 1, SEARCH_EVENT = 2;

    String avatar;
    String name;
    String desc;
    int type;

    public SearchResultBean(String avatar, String name, String desc, int type){
        this.name = name;
        this.avatar = avatar;
        this.desc = desc;
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
