package com.mykins.linkin.app.login;

import com.mykins.linkin.app.BaseView;

/**
 * Created by jerry on 2017/8/24.
 */

public interface LoginContract {
    interface Presenter {
        void login(final String account, final String password);
    }

    interface View extends BaseView<Presenter> {
        void showProgress();

        void validateFailed();

        void loginFailed(String msg);

        void loginSuccess();
    }
}
