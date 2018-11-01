package com.mykins.linkin.app.feed;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mykins.linkin.app.BaseFragment;
import com.mykins.linkin.R;
import com.mykins.linkin.app.Router;
import com.mykins.linkin.bean.FeedBean;
import com.mykins.linkin.util.ConvertUtils;
import com.mykins.linkin.util.CustomViewUtils;
import com.zyyoona7.lib.EasyPopup;

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
    EasyPopup mPopupMenu;
    View mPopupArchorView;
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.feed_menu_add) {
            if (mPopupMenu == null) {
                mPopupMenu = new EasyPopup(mContext)
                        .setContentView(R.layout.ui_feed_popup_view)
                        //.setAnimationStyle(R.style.CirclePopAnim)
                        //是否允许点击PopupWindow之外的地方消失
                        .setFocusAndOutsideEnable(true)
                        .createPopup();
                mPopupMenu.getContentView().findViewById(R.id.feed_popup_share)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Router.actPublishShare(mActivity);
                            }
                        });
                mPopupMenu.getContentView().findViewById(R.id.feed_popup_activity)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Router.actPublishActivity(mActivity);
                            }
                        });
            }
            mPopupArchorView = getActivity().findViewById(R.id.feed_menu_add);
            mPopupMenu.showAsDropDown(mPopupArchorView, -ConvertUtils.dp2px(mContext,45),0);
            return true;
        }else if (item.getItemId() == R.id.feed_menu_search){
            Router.actGroupContact(mActivity);
            return true;

        }
        return super.onOptionsItemSelected(item);
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
