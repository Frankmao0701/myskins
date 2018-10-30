package com.mykins.linkin.app.kins.profile;

import android.arch.lifecycle.LifecycleOwner;

import com.mykins.linkin.injection.FragmentScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by yjn on 2017/12/18.
 */

@Module
public abstract class KinsProfileModule {

    @FragmentScoped
    @Binds
    abstract KinsProfileContract.View bindKinsProfileView(KinsProfileFragment view);

    @FragmentScoped
    @Binds
    abstract KinsProfileContract.Presenter bindKinsProfiePresenter(KinsProfilePresenter presenter);

    @FragmentScoped
    @Binds
    abstract LifecycleOwner bindKinsProfileLifecycle(KinsProfileFragment lifecycleOwner);
}
