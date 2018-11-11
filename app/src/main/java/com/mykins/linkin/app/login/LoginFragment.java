package com.mykins.linkin.app.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.mykins.linkin.app.BaseFragment;
import com.mykins.linkin.R;
import com.mykins.linkin.app.Router;
import com.mykins.linkin.injection.Injectable;
import com.mykins.linkin.app.main.MainActivity;
import com.mykins.linkin.app.register.RegisterActivity;
import com.mykins.linkin.util.KeyboardUtils;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by jerry on 2017/8/24.
 */

public class LoginFragment extends BaseFragment implements LoginContract.View, Injectable {
    private Unbinder mUnbinder;

    @Inject
    LoginContract.Presenter mPresenter;

    @BindView(R.id.login_logo)
    ImageView mLogo;

    @BindView(R.id.login_edt_account)
    EditText mTextAccount;

    @BindView(R.id.login_edt_pwd)
    EditText mTextPassword;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.ui_login_frag, container, false);
        mUnbinder = ButterKnife.bind(this, root);
        KeyboardVisibilityEvent.setEventListener(mActivity, isOpen -> setLogo(!isOpen));
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        KeyboardUtils.hideSoftInput(mActivity);
    }

    @Override
    public void onDestroyView() {
        mUnbinder.unbind();
        super.onDestroyView();
    }

    void setLogo(boolean visible) {
        if (visible) {
            mLogo.setVisibility(View.VISIBLE);
        } else {
            mLogo.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.login_register_btn)
    void onRegisterClick() {
        Intent it = new Intent(mActivity, RegisterActivity.class);
        mActivity.startActivity(it);
    }

    @OnClick(R.id.login_submit_btn)
    void onLoginClick() {
        mPresenter.login(
                mTextAccount.getText().toString().trim(),
                mTextPassword.getText().toString().trim()
        );
        startActivity(new Intent(mActivity, MainActivity.class));
        mActivity.finish();
    }

    @OnClick(R.id.login_forget_btn)
    void onForgetClick() {
        Router.actForgetActivity(mActivity);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void validateFailed() {

    }

    @Override
    public void loginFailed(String msg) {

    }

    @Override
    public void loginSuccess() {

    }

    public static LoginFragment newInstance() {
        LoginFragment f = new LoginFragment();
        return f;
    }
}
