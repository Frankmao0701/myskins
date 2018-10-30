package com.mykins.linkin.app.register;

import android.arch.lifecycle.LifecycleOwner;

import com.mykins.linkin.component.AppSchedulers;
import com.mykins.linkin.repostory.remote.RegisterService;
import com.mykins.linkin.bean.User;
import com.mykins.linkin.repostory.database.UserDao;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by jerry on 2017/9/3.
 */
public class RegisterPresenter implements RegisterContract.Presenter {
    private UserDao userDao;
    private AppSchedulers schedulers;
    private RegisterContract.View view;
    private LifecycleOwner lifecycleProvider;
    private RegisterService registerService;

    @Inject
    public RegisterPresenter(UserDao userDao,
                             AppSchedulers schedulers,
                             RegisterService registerService,
                             RegisterContract.View view,
                             LifecycleOwner lifecycleOwner) {
        this.userDao = userDao;
        this.schedulers = schedulers;
        this.registerService = registerService;
        this.view = view;
        this.lifecycleProvider = lifecycleOwner;
    }

    public void sendVerifyCode(String phone_or_email) {
        Observable<Long> observable = Observable.intervalRange(0, 60, 0, 1, TimeUnit.SECONDS);
        registerService.sendRegisterVerifyCode(phone_or_email)
                .flatMap(s -> observable)
                .onErrorResumeNext(e -> observable)
                .observeOn(schedulers.mainThread())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleProvider)))
                .subscribe(v -> view.interval(v));

    }

    @Override
    public void register(User user) {

    }
}
