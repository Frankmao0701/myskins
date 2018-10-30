package com.mykins.linkin.app.register;

import com.mykins.linkin.app.BaseView;
import com.mykins.linkin.bean.User;

/**
 * Created by jerry on 2017/9/3.
 */

public interface RegisterContract {
    interface Presenter {
        void sendVerifyCode(String phone_or_email);

        void register(User user);
    }

    interface View extends BaseView<Presenter> {
        void interval(Long s);

        void showSendVerifyCodeError(String message);
    }
}
