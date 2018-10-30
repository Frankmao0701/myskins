package com.mykins.linkin.app.login;

import com.mykins.linkin.injection.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by yjn on 2017/12/9.
 */
@Module
public abstract class LoginFragmentModule {
    @FragmentScoped
    @ContributesAndroidInjector(modules = LoginModule.class)
    abstract LoginFragment loginFragment();
}
