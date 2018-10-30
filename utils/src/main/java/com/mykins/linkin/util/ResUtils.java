package com.mykins.linkin.util;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;

import java.util.Locale;

/**
 * Created by jerry on 2016/5/20.
 * 获取资源相关
 */
public final class ResUtils {

    private static Resources mLocaleResource;

    private static Resources getLocaleResource(Application app) {
        if (mLocaleResource == null) {
            Context ctx = app.getApplicationContext();
            Resources res = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                Configuration conf = getLocaleConfiguration(ctx);
                res = ctx.createConfigurationContext(conf).getResources();
            } else {
                res = ctx.getResources();
            }
            mLocaleResource = res;
        }
        return mLocaleResource;
    }

    public static void init(Application app){
        mLocaleResource = getLocaleResource(app);
    }

    public static void resetLocale(Application app) {
        mLocaleResource = null;
        mLocaleResource = getLocaleResource(app);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    private static Configuration getLocaleConfiguration(Context context) {
        Configuration configuration = new Configuration(context.getResources().getConfiguration());
        configuration.setLocale(Locale.CHINESE);
        return configuration;
    }

    /**
     * 获取字符串
     *
     * @param resId
     * @return
     */
    public static String string(int resId) {
        try {
            return string(resId, (Object) null);
        } catch (Exception e) {
            //处理 resource id 不存在的异常
        }
        return "null~";
    }

    /**
     * 获取字符串
     *
     * @param resId
     * @return
     */
    public static String string(int resId, Object... formatArgs) {
        try {
            if (formatArgs != null && formatArgs.length > 0) {
                return mLocaleResource.getString(resId, formatArgs);
            } else {
                return mLocaleResource.getString(resId);
            }
        } catch (Exception e) {
            //处理 resource id 不存在的异常
        }
        return "null~";
    }


    public static int color(int resColor) {
        return mLocaleResource.getColor(resColor);
    }
}
