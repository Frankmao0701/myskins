package com.mykins.linkin.app.feed.publish;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.mykins.linkin.R;
import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.app.Router;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class KinsRangeActivity extends BaseActivity {
    @BindView(R.id.kins_range_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycle_range)
    RecyclerView recycle_range;

    Unbinder mUiBinder;
    private KinsRangeAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_kins_range);
        super.onCreate(savedInstanceState);
        mUiBinder = ButterKnife.bind(this, this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initView();

    }

    private void initView() {
        if (mAdapter == null) {
            mAdapter = new KinsRangeAdapter(this);
        }
        if (mLayoutManager == null) {
            mLayoutManager = new LinearLayoutManager(this);
        }
        recycle_range.setLayoutManager(mLayoutManager);
        recycle_range.setAdapter(mAdapter);
    }

    @Override
    protected void onDestroy() {
        mUiBinder.unbind();
        super.onDestroy();
    }
}
