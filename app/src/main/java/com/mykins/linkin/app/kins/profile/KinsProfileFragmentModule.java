package com.mykins.linkin.app.kins.profile;

import com.mykins.linkin.injection.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by yjn on 2017/12/18.
 */

@Module
public abstract class KinsProfileFragmentModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = KinsProfileModule.class)
    public abstract KinsProfileFragment kinsProfileFragment();
}
