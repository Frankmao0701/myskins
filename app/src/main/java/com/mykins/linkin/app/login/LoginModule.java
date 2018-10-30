package com.mykins.linkin.app.login;

import android.arch.lifecycle.LifecycleOwner;

import com.mykins.linkin.injection.FragmentScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by yjn on 2017/12/11.
 */

@Module
public abstract class LoginModule {
    @FragmentScoped
    @Binds
    abstract LoginContract.View provideLoginView(LoginFragment loginFragment);

    @FragmentScoped
    @Binds
    abstract LoginContract.Presenter loginPresenter(LoginPresenter loginPresenter);

    @FragmentScoped
    @Binds
    abstract LifecycleOwner provideLifeCycleOwner(LoginFragment loginFragment);
}
