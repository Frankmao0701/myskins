package com.mykins.linkin.app.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.mykins.linkin.R;
import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.app.Router;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PrivacyActivity extends BaseActivity {
    @BindView(R.id.profile_privacy_toolbar)
    Toolbar mToolbar;

    Unbinder mUiBinder;
    @OnClick(R.id.profile_privacy_blackened_rl)
    public void gotoBlackend(){
        Router.actGroupContact(this);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_privacy);
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
