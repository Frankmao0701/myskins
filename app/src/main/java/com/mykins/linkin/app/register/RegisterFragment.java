package com.mykins.linkin.app.register;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mykins.linkin.app.BaseFragment;
import com.mykins.linkin.R;
import com.mykins.linkin.SnackBarHelper;
import com.mykins.linkin.app.Router;
import com.mykins.linkin.injection.Injectable;
import com.mykins.linkin.util.CheckStrUtil;
import com.mykins.linkin.util.RegexUtil;
import com.mykins.linkin.util.ResUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

/**
 * Created by jerry on 2017/9/3.
 */

public class RegisterFragment extends BaseFragment implements RegisterContract.View, Injectable {

    @Inject
    RegisterPresenter mPresenter;

    Unbinder mUiBinder;

    @BindView(R.id.register_account_input)
    TextInputLayout mRegisterAccountInputLayout;
    @BindView(R.id.register_account_edt)
    EditText mRegisterAccountEdt;

    @BindView(R.id.register_passwd_input)
    TextInputLayout mRegisterPasswordInputLayout;
    @BindView(R.id.register_passwd_edt)
    EditText mRegisterPasswordEdt;
    @BindView(R.id.register_verify_input)
    TextInputLayout mRegisterVerifyInputLayout;
    @BindView(R.id.register_verify_edt)
    EditText mRegisterVerifyEdt;

    @BindView(R.id.register_verify_btn)
    Button mRegisterVerifyBtn;

    @OnClick(R.id.register_submit_btn)
    public void registerFinished() {
        Router.actUserInfo(mActivity);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.ui_register_frag, null);
        mUiBinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        mUiBinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {

    }

    boolean validatePhone() {
        mRegisterAccountInputLayout.setErrorEnabled(true);
        final String account = mRegisterAccountEdt.getText().toString().trim();
        if (CheckStrUtil.isEmpty(account)) {
            mRegisterAccountInputLayout.setError(ResUtils.string(R.string.hint_phone_email));
            return false;
        }
        if (!RegexUtil.checkPhone(account) && !RegexUtil.checkEmail(account)) {
            mRegisterAccountInputLayout.setError(ResUtils.string(R.string.error_invalid_account));
            return false;
        }
        return true;
    }

    @OnClick(R.id.register_submit_btn)
    void onRegisterClick() {
        //form validation
        // 1) phone & email
        if (!validatePhone()) return;

        // 2) password
        mRegisterPasswordInputLayout.setErrorEnabled(true);
        final String pwd = mRegisterPasswordEdt.getText().toString().trim();
        if (CheckStrUtil.isEmpty(pwd)) {
            mRegisterPasswordInputLayout.setError(ResUtils.string(R.string.hint_register_password));
            return;
        }
        if (RegexUtil.checkChinese(pwd)) {
            mRegisterPasswordInputLayout.setError(ResUtils.string(R.string.error_invalid_password_zh));
            return;
        }
        if (pwd.length() < 6 || pwd.length() > 12) {
            mRegisterPasswordInputLayout.setError(ResUtils.string(R.string.hint_register_password));
            return;
        }

        // 3) veriry code
        mRegisterVerifyInputLayout.setErrorEnabled(true);
        final String verifyCode = mRegisterVerifyEdt.getText().toString().trim();
        if (CheckStrUtil.isEmpty(verifyCode)) {
            mRegisterVerifyInputLayout.setError(ResUtils.string(R.string.hint_register_verify_code));
            return;
        }
//        if (!RegexUtil.checkDigit(verifyCode)) {
//            mRegisterVerifyInputLayout.setError(ResUtils.string(R.string.error_invalid_verify));
//            return;
//        }
        Router.actUserInfo(mActivity);
        mActivity.finish();

    }

    @OnTextChanged({R.id.register_account_edt, R.id.register_passwd_edt, R.id.register_verify_edt})
    void onEditChange() {
        mRegisterAccountInputLayout.setErrorEnabled(false);
        mRegisterPasswordInputLayout.setErrorEnabled(false);
        mRegisterVerifyInputLayout.setErrorEnabled(false);
    }

    @OnClick(R.id.register_verify_btn)
    void onSendVerifyCode() {
        if (validatePhone()) {
            final String phone_or_email = mRegisterAccountEdt.getText().toString();
            mPresenter.sendVerifyCode(phone_or_email);
        }
    }

    @Override
    public void interval(Long s) {
        Log.e("vetify::", s + "");
        if (s < 59 && s >= 0) {
            mRegisterVerifyBtn.setEnabled(false);
            mRegisterVerifyBtn.setText("( " + (60 - s) + " )" + "秒");
        } else {
            mRegisterVerifyBtn.setEnabled(true);
            mRegisterVerifyBtn.setText(ResUtils.string(R.string.title_send_verify_code));
        }
    }

    @Override
    public void showSendVerifyCodeError(String message) {
        SnackBarHelper.showError(getView(), message, SnackBarHelper.ERROR_DURATION);
    }
}
