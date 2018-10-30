package com.mykins.linkin.injection;

import com.mykins.linkin.app.kins.KinsFragmentModule;
import com.mykins.linkin.app.kins.profile.KinsProfileActivity;
import com.mykins.linkin.app.kins.profile.KinsProfileFragmentModule;
import com.mykins.linkin.app.login.LoginActivity;
import com.mykins.linkin.app.login.LoginFragmentModule;
import com.mykins.linkin.app.main.MainActivity;
import com.mykins.linkin.app.register.RegisterActivity;
import com.mykins.linkin.app.register.RegisterFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by yjn on 2017/12/9.
 */
@Module(includes = {
        LoginFragmentModule.class,
        RegisterFragmentModule.class,
        KinsProfileFragmentModule.class})
public abstract class AppModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = LoginFragmentModule.class)
    abstract LoginActivity loginActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = RegisterFragmentModule.class)
    abstract RegisterActivity registerActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = KinsProfileFragmentModule.class)
    abstract KinsProfileActivity kinsProfileActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = KinsFragmentModule.class)
    abstract MainActivity mainActivity();
}
