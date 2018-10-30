package com.mykins.linkin.app.profile.gather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.mykins.linkin.R;
import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.widget.AddKinsRemarkFragment;
import com.mykins.linkin.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 汇总页面
 */
public class GatherActivity extends BaseActivity {
    @BindView(R.id.gather_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycle_gather)
    RecyclerView recycle_gather;

    Unbinder mUiBinder;
    private GatherAdapter mAdapter;
    private LinearLayoutManager mManager;
    private List<GatherBean> data;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_gather);
        super.onCreate(savedInstanceState);
        mUiBinder = ButterKnife.bind(this, this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initView();

    }

    private void initView() {
        loadData();
        if (mAdapter == null) {
            mAdapter = new GatherAdapter(this);
        }
        if (mManager == null) {
            mManager = new LinearLayoutManager(this);
        }
        recycle_gather.addItemDecoration(new RecycleViewDivider(
                mContext, LinearLayoutManager.VERTICAL, 20, ContextCompat.getColor(mContext, R.color.white)));
        recycle_gather.setLayoutManager(mManager);
        recycle_gather.setAdapter(mAdapter);
        mAdapter.setData(data);
    }

    private void loadData() {
        data = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            GatherBean bean = new GatherBean();
            bean.type = GatherBean.TYPE_ACTIVITY;
            data.add(bean);
        }
        for (int i = 0; i < 3; i++) {
            GatherBean bean = new GatherBean();
            bean.type = GatherBean.TYPE_SHARE;
            data.add(bean);
        }
    }

    @Override
    protected void onDestroy() {
        mUiBinder.unbind();
        super.onDestroy();
    }
}
