package com.mykins.linkin.injection;

import android.annotation.TargetApi;
import android.arch.lifecycle.LifecycleOwner;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

/**
 * Created by jerry on 2017/12/13.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@MapKey
public @interface LifecycleKey {
    Class<? extends LifecycleOwner> value();
}
