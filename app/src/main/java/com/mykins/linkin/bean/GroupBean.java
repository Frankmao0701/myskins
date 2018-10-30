package com.mykins.linkin.bean;

import java.io.Serializable;

/**
 * Created by yjn on 2018/1/14.
 */

public class GroupBean implements Serializable {
    private String[] member_avatars;
    private String name;

    public String[] getMember_avatars() {
        return member_avatars;
    }

    public void setMember_avatars(String[] member_avatars) {
        this.member_avatars = member_avatars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
