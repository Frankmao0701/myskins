package com.mykins.linkin.app.login;

import android.arch.lifecycle.LifecycleOwner;

import com.mykins.linkin.component.AppSchedulers;
import com.mykins.linkin.repostory.remote.LoginService;
import com.mykins.linkin.repostory.database.UserDao;

import javax.inject.Inject;

/**
 * Created by jerry on 2017/8/24.
 */
public class LoginPresenter implements LoginContract.Presenter {
    private LifecycleOwner lifecycleOwner;
    private UserDao userDao;
    private AppSchedulers appSchedulers;
    private LoginContract.View view;
    private LoginService loginService;

    @Inject
    public LoginPresenter(UserDao userDao,
                          LoginService loginService,
                          AppSchedulers schedulers,
                          LoginContract.View view,
                          LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        this.userDao = userDao;
        this.loginService = loginService;
        this.appSchedulers = schedulers;
        this.view = view;
    }

    @Override
    public void login(String account, String password) {

    }
}
