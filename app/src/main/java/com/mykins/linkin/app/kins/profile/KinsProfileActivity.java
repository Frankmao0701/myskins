package com.mykins.linkin.app.kins.profile;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.mykins.linkin.app.BaseActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by yjn on 2017/11/21.
 * 亲友信息
 */
public class KinsProfileActivity extends BaseActivity implements HasSupportFragmentInjector {
    public static final int TYPE_KINS = 1;
    public static final int TYPE_DIS_KINS = 2;//逝亲

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    KinsProfileFragment kinsProfileFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        int type = getIntent().getExtras().getInt("data", TYPE_KINS);

        FragmentManager fragmentManager = getSupportFragmentManager();
        kinsProfileFragment = (KinsProfileFragment) fragmentManager.findFragmentById(android.R.id.content);
        if (kinsProfileFragment == null) {
            kinsProfileFragment = KinsProfileFragment.newInstance(type);
            fragmentManager.beginTransaction()
                    .add(android.R.id.content, kinsProfileFragment)
                    .commit();
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    /*@Override
    public void onBackPressed() {
        if (kinsProfileFragment != null && kinsProfileFragment.isAdded()
                && kinsProfileFragment.backPressed()){
            return;
        }else{
        super.onBackPressed();
        }
    }*/
}
