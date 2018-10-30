package com.mykins.linkin.app.kins;

import android.arch.lifecycle.LifecycleOwner;

import com.mykins.linkin.injection.FragmentScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by yjn on 2018/1/12.
 */
@Module
public abstract class KinsModule {
    @FragmentScoped
    @Binds
    abstract KinsContract.View bindKinsView(KinsFragment view);

    @FragmentScoped
    @Binds
    abstract KinsContract.Presenter bindKinsPresenter(KinsPreserter preserter);

    @FragmentScoped
    @Binds
    abstract LifecycleOwner lifecycleOwner(KinsFragment lifecycleOwner);
}
