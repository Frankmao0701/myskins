package com.mykins.linkin.app.feed.publish;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.mykins.linkin.R;
import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.widget.ReplyDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 活动详情页面
 */
public class ShareDetailActivity extends BaseActivity {
    @BindView(R.id.share_detail_toolbar)
    Toolbar mToolbar;


    Unbinder mUiBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_detail);
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
