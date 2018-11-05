package com.mykins.linkin.bean;

import java.io.Serializable;
import java.util.List;

public class GroupContactBean implements Serializable {
    public static final int TYPE_INDEX = 1;
    public static final int TYPE_CONTACT = 0;
    public int type;
    public String letter;
    public List<KinsBean> kins;
}
