package com.mykins.linkin.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.mykins.linkin.injection.AppInjector;
import com.mykins.linkin.util.GlideHelper;
import com.mykins.linkin.util.ResUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by jerry on 2017/8/24.
 */

public class MyApp extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private static MyApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        AppInjector.init(this);
        //initial some helper classes
        ResUtils.init(this);
        GlideHelper.init(this);
        ZXingLibrary.initDisplayOpinion(this);
    }

    public static MyApp getInstance() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
//        MultiDex.install(this);
        super.attachBaseContext(base);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
