package com.mykins.linkin.app.kins.profile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.mykins.linkin.R;
import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.util.RegexUtil;
import com.mykins.linkin.util.ResUtils;
import com.mykins.linkin.util.StringUtils;
import com.mykins.linkin.view.ExsitEditingDialog;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import butterknife.Optional;
import butterknife.Unbinder;

/**
 * Created by yjn on 2017/11/22.
 */

public class KinsProfileEditActivity extends BaseActivity {

    Unbinder unbinder;
    LayoutInflater mInflater;

    Object mPreparedData;
    int mCategory;

    @BindView(R.id.kins_profile_editing_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.kins_profile_editing_content)//kins.profile.editing.content
            FrameLayout mContent;

    //edit name
    @Nullable
    @BindView(R.id.first_name_layout)
    TextInputLayout mFirstNameLayout;
    @Nullable
    @BindView(R.id.kins_profile_editing_first_name)
    EditText mFirstName;

    @Nullable
    @BindView(R.id.second_name_layout)
    TextInputLayout mSecondNameLayout;
    @Nullable
    @BindView(R.id.kins_profile_editing_second_name)
    EditText mSecondName;
    @Nullable
    @BindView(R.id.edit_content)
    EditText edit_content;

    private boolean mChanged;

    boolean validateName() {
        boolean error = true;
        String firstName = mFirstName.getText().toString();
        if (StringUtils.isEmpty(firstName)) {
            mFirstNameLayout.setErrorEnabled(true);
            mFirstNameLayout.setError(ResUtils.string(R.string.error_empty));
            mFirstName.setBackgroundColor(Color.WHITE);
            error = false;
        } else {
            mFirstNameLayout.setErrorEnabled(false);
        }

        String secondName = mSecondName.getText().toString();
        if (StringUtils.isEmpty(secondName)) {
            mSecondNameLayout.setErrorEnabled(true);
            mSecondNameLayout.setError(ResUtils.string(R.string.error_empty));
            error = false;
        } else {
            mSecondNameLayout.setErrorEnabled(false);
        }
        return error;
    }

    Serializable getName() {
        String first = mFirstName.getText().toString();
        String second = mSecondName.getText().toString();
        return new String[]{first, second};
    }

    void setName(String[] name) {
        if (name == null) {
            mFirstName.setHint(ResUtils.string(R.string.hint_first_name));
            mSecondName.setHint(ResUtils.string(R.string.hint_second_name));
        } else {
            mFirstName.setText(name[0]);
            mSecondName.setText(name[1]);
            mFirstName.setSelection(name[0].length());
        }
    }

    @Nullable
    @BindView(R.id.input_layout)
    TextInputLayout mEditInputLayout;
    @Nullable
    @BindView(R.id.edit)
    EditText mEdit;

    ExsitEditingDialog mExsitEditingDialog;

    //edit phone
    boolean validatePhone() {
        boolean validate = false;
        String val = mEdit.getText().toString();
        String error = null;
        if (StringUtils.isEmpty(val)) {
            error = ResUtils.string(R.string.error_empty);
        } else if (!RegexUtil.checkPhone(val)) {
            error = ResUtils.string(R.string.error_invalid_phone);
        } else {
            validate = true;
        }
        if (!validate && error != null) {
            mEditInputLayout.setErrorEnabled(true);
            mEditInputLayout.setError(error);
        }
        return validate;
    }

    Serializable getPhone() {
        String val = mEdit.getText().toString();
        return val;
    }

    void setPhone(String data) {
        if (StringUtils.isEmpty(data)) {
            mEdit.setHint(ResUtils.string(R.string.hint_phone));
        } else {
            mEdit.setText(data);
            mEdit.setSelection(data.length());
        }
    }

    //edit email
    boolean validateEmail() {
        boolean validate = false;
        String val = mEdit.getText().toString();
        String error = null;
        if (StringUtils.isEmpty(val)) {
            error = ResUtils.string(R.string.error_empty);
        } else if (!RegexUtil.checkEmail(val)) {
            error = ResUtils.string(R.string.error_invalid_email);
        } else {
            validate = true;
        }
        if (!validate && error != null) {
            mEditInputLayout.setErrorEnabled(true);
            mEditInputLayout.setError(error);
        }
        return validate;
    }

    Serializable getEmail() {
        String val = mEdit.getText().toString();
        return val;
    }

    void setEmail(String data) {
        if (StringUtils.isEmpty(data)) {
            mEdit.setHint(ResUtils.string(R.string.hint_email));
        } else {
            mEdit.setText(data);
            mEdit.setSelection(data.length());
        }
    }

    //edit address
    boolean validateAddress() {
        boolean validate = false;
        String error = null;
        String val = mEdit.getText().toString();
        if (StringUtils.isEmpty(val)) {
            error = ResUtils.string(R.string.error_empty);
        } else {
            validate = true;
        }
        if (!validate && error != null) {
            mEditInputLayout.setErrorEnabled(true);
            mEditInputLayout.setError(error);
        }
        return validate;
    }

    String getAddress() {
        String val = mEdit.getText().toString();
        return val;
    }

    void setAddress(String data) {
        if (StringUtils.isEmpty(data)) {
            mEdit.setHint(ResUtils.string(R.string.hint_address));
        } else {
            mEdit.setText(data);
            mEdit.setSelection(data.length());
        }
    }

    //edit phone
    boolean validateMark() {
        boolean validate = false;
        String error = null;
        String val = mEdit.getText().toString();
        if (StringUtils.isEmpty(val)) {
            error = ResUtils.string(R.string.error_empty);
        } else {
            validate = true;
        }
        if (!validate && error != null) {
            mEditInputLayout.setErrorEnabled(true);
            mEditInputLayout.setError(error);
        }
        return validate;
    }

    boolean validateActivityTitle() {
        boolean validate = false;
        String error = null;
        String val = mEdit.getText().toString();
        if (StringUtils.isEmpty(val)) {
            error = ResUtils.string(R.string.text_input_activity_title);
        } else {
            validate = true;
        }
        if (!validate && error != null) {
            mEditInputLayout.setErrorEnabled(true);
            mEditInputLayout.setError(error);
        }
        return validate;
    }

    boolean validateActivityContent() {
        boolean validate = false;
        String error = null;
        String val = edit_content.getText().toString();
        if (StringUtils.isEmpty(val)) {
            error = ResUtils.string(R.string.text_input_activity_content);
        } else {
            validate = true;
        }
        if (!validate && error != null) {
            mEditInputLayout.setErrorEnabled(true);
            mEditInputLayout.setError(error);
        }
        return validate;
    }

    boolean validateActivityAddress() {
        boolean validate = false;
        String error = null;
        String val = mEdit.getText().toString();
        if (StringUtils.isEmpty(val)) {
            error = ResUtils.string(R.string.text_input_activity_address);
        } else {
            validate = true;
        }
        if (!validate && error != null) {
            mEditInputLayout.setErrorEnabled(true);
            mEditInputLayout.setError(error);
        }
        return validate;
    }

    Serializable getMark() {
        return mEdit.getText().toString();
    }

    Serializable getActivityContent() {
        return edit_content.getText().toString();
    }

    Serializable getActivityAddress() {
        return mEdit.getText().toString();
    }

    Serializable getActivityTitle() {
        return mEdit.getText().toString();
    }

    void setMark(String data) {
        if (StringUtils.isEmpty(data)) {
            mEdit.setHint(ResUtils.string(R.string.hint_mark));
        } else {
            mEdit.setText(data);
            mEdit.setSelection(data.length());
        }
    }

    void setActivityContent(String data) {
        if (StringUtils.isEmpty(data)) {
            edit_content.setHint(ResUtils.string(R.string.text_input_activity_content));
        } else {
            edit_content.setText(data);
            edit_content.setSelection(data.length());
        }
    }

    void setActivityAddress(String data) {
        if (StringUtils.isEmpty(data)) {
            mEdit.setHint(ResUtils.string(R.string.text_input_activity_address));
        } else {
            mEdit.setText(data);
            mEdit.setSelection(data.length());
        }
    }

    void setActivityTitle(String data) {
        if (StringUtils.isEmpty(data)) {
            mEdit.setHint(ResUtils.string(R.string.text_input_activity_title));
        } else {
            mEdit.setText(data);
            mEdit.setSelection(data.length());
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCategory = (int) getIntent().getExtras().get("category");
        mPreparedData = getIntent().getExtras().get("data");

        mInflater = LayoutInflater.from(this);
        setContentView(R.layout.ui_kins_profile_edit_act);
        unbinder = ButterKnife.bind(this, this);
        setContent(mCategory);
        setSupportActionBar(mToolbar);
        mExsitEditingDialog = ExsitEditingDialog.newInstance();
        mExsitEditingDialog.setOnExsitingListener(new ExsitEditingDialog.OnExsitingListener() {
            @Override
            public void onCancel() {
                //ignore
                mExsitEditingDialog.dismiss();
            }

            @Override
            public void onOk() {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_save, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else {
            boolean isValidate = validate(mCategory);
            if (isValidate) {
                Serializable value = getValue(mCategory);
                setResult(isValidate ? RESULT_OK : RESULT_CANCELED, new Intent().putExtra("data", value == null ? "" : value));
                finish();
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mChanged) {
            mExsitEditingDialog.showDialog(this);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Optional
    @OnTextChanged({
            R.id.kins_profile_editing_first_name,
            R.id.kins_profile_editing_second_name,
            R.id.edit,
            R.id.edit_content
    })
    void onEditTextChange() {
        mChanged = true;
    }

    void setContent(final int category) {
        int layoutId = 0;
        switch (category) {
            case Editing.NAME:
                layoutId = R.layout.include_edit_profile_name;
                break;
            case Editing.ACTIVITY_CONTENT:
                layoutId = R.layout.include_edit_activity_content;
                break;
            default:
                layoutId = R.layout.include_edit_text;
        }
        View view = mInflater.inflate(layoutId, null);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        mContent.addView(view, params);
        unbinder = ButterKnife.bind(this, this);
        setValue(mCategory, mPreparedData);
    }

    boolean validate(int category) {
        boolean result = false;
        switch (category) {
            case Editing.NAME: {
                return validateName();
            }
            case Editing.ADDRESS: {
                return validateAddress();
            }
            case Editing.PHONE: {
                return validatePhone();
            }
            case Editing.EMAIL: {
                return validateEmail();
            }
            case Editing.MARK: {
                return validateMark();
            }
            case Editing.TITLE: {
                return validateActivityTitle();
            }
            case Editing.ACTIVITY_CONTENT: {
                return validateActivityContent();
            }
            case Editing.ACTIVITY_ADDRESS: {
                return validateActivityAddress();
            }
        }
        return result;
    }

    void setValue(int category, Object data) {
        switch (category) {
            case Editing.NAME: {
                setName((String[]) data);
                break;
            }
            case Editing.ADDRESS: {
                setAddress((String) data);
                break;
            }
            case Editing.PHONE: {
                setPhone((String) data);
                break;
            }
            case Editing.EMAIL: {
                setEmail((String) data);
                break;
            }
            case Editing.MARK: {
                setMark((String) data);
                break;
            }
            case Editing.TITLE: {
                setActivityTitle((String) data);
                break;
            }
            case Editing.ACTIVITY_CONTENT: {
                setActivityContent((String) data);
                break;
            }
            case Editing.ACTIVITY_ADDRESS: {
                setActivityAddress((String) data);
                break;
            }
        }
        // reset EditText changed
        mChanged = false;
    }

    Serializable getValue(int category) {
        if (validate(category)) {
            switch (category) {
                case Editing.NAME: {
                    return getName();
                }
                case Editing.ADDRESS: {
                    return getAddress();
                }
                case Editing.PHONE: {
                    return getPhone();
                }
                case Editing.EMAIL: {
                    return getEmail();
                }
                case Editing.MARK: {
                    return getMark();
                }
                case Editing.TITLE: {
                    return getActivityTitle();
                }
                case Editing.ACTIVITY_CONTENT: {
                    return getActivityContent();
                }
                case Editing.ACTIVITY_ADDRESS: {
                    return getActivityAddress();
                }
            }
        }
        return null;
    }

    public interface Editing {
        int NAME = 1;
        int ADDRESS = 2;
        int PHONE = 3;
        int EMAIL = 4;
        int MARK = 5;
        int TITLE = 6;
        int ACTIVITY_ADDRESS = 7;
        int ACTIVITY_CONTENT = 8;
    }
}
