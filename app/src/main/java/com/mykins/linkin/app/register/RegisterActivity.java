package com.mykins.linkin.app.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.R;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by jerry on 2017/9/3.
 */

public class RegisterActivity extends BaseActivity implements HasSupportFragmentInjector{
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_register_act);

        RegisterFragment registerFragment =
                (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (registerFragment == null) {
            registerFragment = new RegisterFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentFrame, registerFragment)
                    .commit();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return  this.dispatchingAndroidInjector;
    }
}
