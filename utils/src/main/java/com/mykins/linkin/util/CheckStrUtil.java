package com.mykins.linkin.util;

/**
 * Created by jerry on 2017/9/4.
 */

public final class CheckStrUtil {
    public static boolean isEmpty(final String val) {
        return val == null || val.trim().length() == 0;
    }
}
