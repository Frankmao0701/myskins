package com.mykins.linkin.app.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mykins.linkin.app.BaseFragment;
import com.mykins.linkin.R;
import com.mykins.linkin.bean.SearchResultBean;
import com.mykins.linkin.util.ResUtils;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yjn on 2017/11/4.
 */

public class SearchFragment extends BaseFragment {
    Unbinder mUnbinder;

    @BindView(R.id.search_start)
    LinearLayout mSearchStart;

    @BindView(R.id.recyclerView)
    RecyclerView mRecylerView;

    LinearLayoutManager mLinearLayoutManager;

    SearchAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ui_search_frag, null);
        mUnbinder = ButterKnife.bind(this, view);

        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecylerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new SearchAdapter(mActivity, this);
        mRecylerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    void showStart(){
        mSearchStart.setVisibility(View.VISIBLE);
        mRecylerView.setVisibility(View.GONE);
    }

    void loadData() {
        mSearchStart.setVisibility(View.GONE);
        mRecylerView.setVisibility(View.VISIBLE);

        boolean isEmpty = mAdapter.getItemCount()==0;
        if (!isEmpty)return;


        ArrayList<Object> data = new ArrayList<>();
        data.add(ResUtils.string(R.string.title_search_im));
        data.addAll(Arrays.asList(new SearchResultBean[]{
                new SearchResultBean("http://img.hb.aicdn.com/859b6e9c965eb9a1cb60dd095443dead5b96ab0578890-Ech9SR_fw658", "痞子厨子", "不想当厨师的工程师不是好的将军", SearchResultBean.SEARCH_IM),
                new SearchResultBean("http://img.hb.aicdn.com/859b6e9c965eb9a1cb60dd095443dead5b96ab0578890-Ech9SR_fw658", "痞子厨子", "不想当厨师的工程师不是好的将军", SearchResultBean.SEARCH_IM),
                new SearchResultBean("http://img.hb.aicdn.com/859b6e9c965eb9a1cb60dd095443dead5b96ab0578890-Ech9SR_fw658", "痞子厨子", "不想当厨师的工程师不是好的将军",SearchResultBean.SEARCH_IM),
                new SearchResultBean("http://img.hb.aicdn.com/859b6e9c965eb9a1cb60dd095443dead5b96ab0578890-Ech9SR_fw658", "痞子厨子", "不想当厨师的工程师不是好的将军",SearchResultBean.SEARCH_IM)
        }));


        data.add(ResUtils.string(R.string.title_search_share));
        data.addAll(Arrays.asList(new SearchResultBean[]{
                new SearchResultBean("http://img.hb.aicdn.com/1bb534a3c66ceef02f2ad0ce0368c05966437cc819d23-cRA0rz_fw658", "索隆1", "不想当厨师的工程师不是好的将军",SearchResultBean.SEARCH_SHARE),
                new SearchResultBean("http://img.hb.aicdn.com/1bb534a3c66ceef02f2ad0ce0368c05966437cc819d23-cRA0rz_fw658", "索隆2", "不想当厨师的工程师不是好的将军",SearchResultBean.SEARCH_SHARE),
                new SearchResultBean("http://img.hb.aicdn.com/1bb534a3c66ceef02f2ad0ce0368c05966437cc819d23-cRA0rz_fw658", "索隆3", "不想当厨师的工程师不是好的将军",SearchResultBean.SEARCH_SHARE),
                new SearchResultBean("http://img.hb.aicdn.com/1bb534a3c66ceef02f2ad0ce0368c05966437cc819d23-cRA0rz_fw658", "索隆4", "不想当厨师的工程师不是好的将军",SearchResultBean.SEARCH_SHARE)
        }));

        data.add(ResUtils.string(R.string.title_search_event));
        data.addAll(Arrays.asList(new SearchResultBean[]{
                new SearchResultBean("http://img.hb.aicdn.com/1bb534a3c66ceef02f2ad0ce0368c05966437cc819d23-cRA0rz_fw658", "索隆1", "不想当厨师的工程师不是好的将军",SearchResultBean.SEARCH_EVENT),
                new SearchResultBean("http://img.hb.aicdn.com/1bb534a3c66ceef02f2ad0ce0368c05966437cc819d23-cRA0rz_fw658", "索隆2", "不想当厨师的工程师不是好的将军",SearchResultBean.SEARCH_EVENT),
                new SearchResultBean("http://img.hb.aicdn.com/1bb534a3c66ceef02f2ad0ce0368c05966437cc819d23-cRA0rz_fw658", "索隆3", "不想当厨师的工程师不是好的将军",SearchResultBean.SEARCH_EVENT),
                new SearchResultBean("http://img.hb.aicdn.com/1bb534a3c66ceef02f2ad0ce0368c05966437cc819d23-cRA0rz_fw658", "索隆4", "不想当厨师的工程师不是好的将军", SearchResultBean.SEARCH_EVENT)
        }));

        mAdapter.addData(data);
    }
}
