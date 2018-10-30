package com.mykins.linkin.app.profile.gather;

import java.io.Serializable;

public class GatherBean implements Serializable {
    public static final int TYPE_ACTIVITY = 0;
    public static final int TYPE_SHARE = 1;
    public String id;
    public String content;
    public String time;
    public int pic_count;
    public int type;
}
