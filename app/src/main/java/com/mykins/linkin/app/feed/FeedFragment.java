package com.mykins.linkin.app.feed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mykins.linkin.app.BaseFragment;
import com.mykins.linkin.R;
import com.mykins.linkin.bean.FeedBean;
import com.mykins.linkin.util.CustomViewUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jerry on 2017/9/6.
 * 讯
 */

public class FeedFragment extends BaseFragment {
    Unbinder mUiBinder;

    @BindView(R.id.include_empty)
    View mEmptyView;

    @BindView(R.id.include_empty_text)
    TextView mEmptyText;

    @BindView(R.id.feed_recyclerView)
    RecyclerView mRecyclerView;

    LinearLayoutManager mLinearLayoutManager;

    FeedAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.ui_feed_frag, null, false);
        mUiBinder = ButterKnife.bind(this, root);

        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter = new FeedAdapter(mActivity, this));
        CustomViewUtils.setRecyclerViewDivider(mContext, mRecyclerView, mLinearLayoutManager);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_feed, menu);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUiBinder.unbind();
    }

    void loadData() {
        ArrayList<FeedBean> data = new ArrayList<>();
    }
}
