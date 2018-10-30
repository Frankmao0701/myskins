package com.mykins.linkin.app.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.mykins.linkin.R;
import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.app.Router;
import com.mykins.linkin.widget.AddKinsRemarkFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddKinActivity extends BaseActivity {
    @BindView(R.id.addkin_toolbar)
    Toolbar mToolbar;


    Unbinder mUiBinder;

    @OnClick(R.id.profile_add_sure_btn)
    public void showDialog() {
        final AddKinsRemarkFragment dialog = new AddKinsRemarkFragment();
        dialog.setOnDialogClickListener(new AddKinsRemarkFragment.onDialogClick() {
            @Override
            public void onSureClick(boolean isCheck) {

            }

            @Override
            public void onLeaveClick() {
                dialog.dismiss();
            }
        });
        dialog.show(getFragmentManager(), "remarkDialog");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_addkin);
        super.onCreate(savedInstanceState);
        mUiBinder = ButterKnife.bind(this, this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    @Override
    protected void onDestroy() {
        mUiBinder.unbind();
        super.onDestroy();
    }
}
