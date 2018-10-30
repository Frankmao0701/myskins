package com.mykins.linkin.app.kins.manage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mykins.linkin.app.BaseFragment;
import com.mykins.linkin.R;
import com.mykins.linkin.bean.KinsBean;
import com.mykins.linkin.util.CustomViewUtils;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yjn on 2017/11/3.
 */

public class NewKinsFragment extends BaseFragment {

    @BindView(R.id.kins_new_recyclerView)
    RecyclerView mRecyclerView;

    LinearLayoutManager mLinearLayoutManager;

    NewKinsAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ui_new_kins_frag, null);
        ButterKnife.bind(this, view);
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new NewKinsAdapter(mActivity, this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        CustomViewUtils.setRecyclerViewDivider(mContext, mRecyclerView, mLinearLayoutManager);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    private void loadData() {
        ArrayList<KinsBean> newKins = new ArrayList<>();
        newKins.addAll(Arrays.asList(new KinsBean[]{
          new KinsBean("http://img.hb.aicdn.com/859b6e9c965eb9a1cb60dd095443dead5b96ab0578890-Ech9SR_fw658", "痞子厨子", "不想当厨师的工程师不是好的将军"),
                new KinsBean("http://img.hb.aicdn.com/859b6e9c965eb9a1cb60dd095443dead5b96ab0578890-Ech9SR_fw658", "痞子厨子", "不想当厨师的工程师不是好的将军"),
                new KinsBean("http://img.hb.aicdn.com/859b6e9c965eb9a1cb60dd095443dead5b96ab0578890-Ech9SR_fw658", "痞子厨子", "不想当厨师的工程师不是好的将军"),
                new KinsBean("http://img.hb.aicdn.com/859b6e9c965eb9a1cb60dd095443dead5b96ab0578890-Ech9SR_fw658", "痞子厨子", "不想当厨师的工程师不是好的将军")
        }));

        ArrayList<KinsBean> recommendKin = new ArrayList<>();
        recommendKin.addAll(Arrays.asList(new KinsBean[]{
                new KinsBean("http://img.hb.aicdn.com/1bb534a3c66ceef02f2ad0ce0368c05966437cc819d23-cRA0rz_fw658", "索隆1", "不想当厨师的工程师不是好的将军"),
                new KinsBean("http://img.hb.aicdn.com/1bb534a3c66ceef02f2ad0ce0368c05966437cc819d23-cRA0rz_fw658", "索隆2", "不想当厨师的工程师不是好的将军"),
                new KinsBean("http://img.hb.aicdn.com/1bb534a3c66ceef02f2ad0ce0368c05966437cc819d23-cRA0rz_fw658", "索隆3", "不想当厨师的工程师不是好的将军"),
                new KinsBean("http://img.hb.aicdn.com/1bb534a3c66ceef02f2ad0ce0368c05966437cc819d23-cRA0rz_fw658", "索隆4", "不想当厨师的工程师不是好的将军")
        }));

        mAdapter.addNewKins(newKins);
        mAdapter.addRecommenKins(recommendKin);
    }
}
