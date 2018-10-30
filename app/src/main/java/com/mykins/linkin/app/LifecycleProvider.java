package com.mykins.linkin.app;

import android.arch.lifecycle.LifecycleOwner;

import com.mykins.linkin.injection.ActivityScoped;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by jerry on 2017/12/14.
 */
@ActivityScoped
public final class LifecycleProvider {

    private HashMap<Class<? extends LifecycleOwner>, Provider<LifecycleOwner>> provider;

    @Inject
    public LifecycleProvider(HashMap<Class<? extends LifecycleOwner>, Provider<LifecycleOwner>> provider) {
        this.provider = provider;
    }

    public LifecycleOwner get(Class<? extends LifecycleOwner> key) {
        return this.provider.get(key).get();
    }
}
