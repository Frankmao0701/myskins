package com.mykins.linkin.app.kins;

import com.mykins.linkin.injection.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by yjn on 2018/1/12.
 */

@Module
public abstract class KinsFragmentModule {

    @FragmentScoped
    @ContributesAndroidInjector(modules = KinsModule.class)
    abstract KinsFragment kinsFragment();
}
