package com.mykins.linkin.app.register;

import android.arch.lifecycle.LifecycleOwner;

import com.mykins.linkin.injection.FragmentScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by yjn on 2017/12/11.
 */
@Module
public abstract class RegisterModule {
    @FragmentScoped
    @Binds
    abstract RegisterContract.View provideRegisterView(RegisterFragment registerFragment);

    @FragmentScoped
    @Binds
    abstract RegisterContract.Presenter registerPresenter(RegisterPresenter registerPresenter);

    @FragmentScoped
    @Binds
    abstract LifecycleOwner bindLifecycleOwner(RegisterFragment registerFragment);
}
