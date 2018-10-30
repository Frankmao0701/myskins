package com.mykins.linkin.app.register;

import com.mykins.linkin.injection.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by yjn on 2017/12/9.
 */
@Module
public abstract class RegisterFragmentModule {
    @FragmentScoped
    @ContributesAndroidInjector(modules = RegisterModule.class)
    abstract RegisterFragment registerFragment();
}
