package com.mykins.linkin.app.login;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.mykins.linkin.R;
import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.app.kins.chat.ChatActivity;
import com.mykins.linkin.util.ResUtils;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by yjn on 2017/11/12.
 */

public class ForgetPwdActivity extends BaseActivity {
    Unbinder unbinder;

    @BindView(R.id.forget_pwd_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.forget_verify_btn)
    Button btn_send_vetify;

    @OnClick(R.id.forget_verify_btn)
    void sendVetify() {
        sendVerifyCode("21312312");
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        unbinder = ButterKnife.bind(this, this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    public void sendVerifyCode(String phone_or_email) {
        Observable<Long> observable = Observable.intervalRange(0, 60, 0, 1, TimeUnit.SECONDS);
//        registerService.sendRegisterVerifyCode(phone_or_email)
                observable
                .flatMap(s -> observable)
                .onErrorResumeNext(e -> observable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v -> interval(v));

    }
    public void interval(Long s) {
//        Log.e("vetify::", s + "");
        if (s < 59 && s >= 0) {
            btn_send_vetify.setEnabled(false);
            btn_send_vetify.setText("( " + (60 - s) + " )" + "秒");
        } else {
            btn_send_vetify.setEnabled(true);
            btn_send_vetify.setText(ResUtils.string(R.string.title_send_verify_code));
        }
    }
}
