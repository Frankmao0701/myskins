package com.mykins.linkin.util;

/**
 * Created by yjn on 2017/11/23.
 */

public final class StringUtils {

    public final static boolean isEmpty(final String str) {
        return str == null || str.trim().length() == 0;
    }

    public final static boolean isNotEmpty(final String str) {
        return str != null && str.trim().length() > 0;
    }

    public final static String valueOf(final String str){
        if (str == null)return "";
        return str;
    }

    public final static String valueOf(final String str, final String  defaultValue){
        if (str == null)return defaultValue;
        return str;
    }
}
