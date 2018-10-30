package com.mykins.linkin.app.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.mykins.linkin.R;
import com.mykins.linkin.app.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StatisticsActivity extends BaseActivity {
    @BindView(R.id.statistics_toolbar)
    Toolbar mToolbar;

    Unbinder mUiBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_statistics);
        super.onCreate(savedInstanceState);
        mUiBinder = ButterKnife.bind(this, this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        mUiBinder.unbind();
        super.onDestroy();
    }
}
