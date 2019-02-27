package com.mykins.linkin.app.kins;

import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.mykins.linkin.app.BaseFragment;
import com.mykins.linkin.R;
import com.mykins.linkin.bean.GroupBean;
import com.mykins.linkin.injection.Injectable;
import com.mykins.linkin.util.CustomViewUtils;
import com.mykins.linkin.app.Router;
import com.zyyoona7.lib.EasyPopup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.Binds;

/**
 * 亲页面
 * Created by jerry on 2017/9/4.
 */
public class KinsFragment extends BaseFragment implements KinsContract.View, Injectable {
    Unbinder mUiBinder;

    @BindView(R.id.kins_fast_indicator)
    FastPositionIndicator mFastPositionIndicator;

    @BindView(R.id.kins_fast_showIndicator)
    TextView mFastShowIndicator;

    @BindView(R.id.kins_contact_rcv)
    RecyclerView mRecyclerView;

    KinsAdapter mAdapter;

    EasyPopup mPopupMenu;
    View mPopupArchorView;

    private LinearLayoutManager layoutManager;

    @Inject
    KinsContract.Presenter kinsPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_kins, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.kins_menu_more) {
            if (mPopupMenu == null) {
                mPopupMenu = new EasyPopup(mContext)
                        .setContentView(R.layout.ui_kins_popup_view)
                        //.setAnimationStyle(R.style.CirclePopAnim)
                        //是否允许点击PopupWindow之外的地方消失
                        .setFocusAndOutsideEnable(true)
                        .createPopup();
                mPopupMenu.getContentView().findViewById(R.id.kins_popup_search)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Router.actSearch(mActivity);
                            }
                        });
                mPopupMenu.getContentView().findViewById(R.id.kins_popup_diskins)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Router.actDiskins(mActivity);
                            }
                        });
            }
            mPopupArchorView = getActivity().findViewById(R.id.kins_menu_more);
            mPopupMenu.showAsDropDown(mPopupArchorView);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.ui_kins_frag, null, false);
        mUiBinder = ButterKnife.bind(this, root);

        layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        CustomViewUtils.setRecyclerViewDivider(mContext, mRecyclerView, layoutManager);

        mAdapter = new KinsAdapter(mActivity, this);///
        mRecyclerView.setAdapter(mAdapter);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        kinsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPopupMenu != null) mPopupMenu.dismiss();
    }

    @Override
    public void onDestroyView() {
        mUiBinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void showData(ArrayList<String[]> data) {
        mAdapter.add(data);
    }

    @Override
    public void showIndexer(ArrayList<String> letters, HashMap<String, Integer> lettersMappingPosition) {
        final HashMap<String, Integer> mLettersMappingPosition = lettersMappingPosition;
        mFastPositionIndicator.setLetters(letters.toArray(new String[0]));
        mFastPositionIndicator.setTextDialog(mFastShowIndicator);

        mFastPositionIndicator.setOnTouchingLetterChangedListener(s -> {
            boolean first = s.equals(letters.get(0));
            final int selectedPos = lettersMappingPosition.get(s) + +(first ? 0 : 1);
            layoutManager.scrollToPositionWithOffset(selectedPos, 0);
        });
    }

    @Override
    public void showGroup(ArrayList<GroupBean> groups) {
        mAdapter.addGroup(groups);
    }


    public static class PinyinComparator implements Comparator<String> {
        public int compare(String o1, String o2) {
            o1 = o1 != null ? o1.trim() : o1;
            o2 = o2 != null ? o2.trim() : o2;

            if (o1 == null && o2 == null) return 0;
            if (o1 == null && o2 != null) return -1;
            if (o1 != null && o2 == null) return 1;
            if (o1 != null && o1.equals(o2)) return 0;

            char c1, c2;
            try {
                c1 = PinyinHelper.getShortPinyin(o1).charAt(0);
                c2 = PinyinHelper.getShortPinyin(o2).charAt(0);
                return Character.getNumericValue(c1) < Character.getNumericValue(c2) ? -1 : 1;
            } catch (PinyinException e) {
                e.printStackTrace();
            }
            return -1;
        }
    }

}

