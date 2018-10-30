package com.mykins.linkin.app.diskins;

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
import com.mykins.linkin.bean.KinsBean;
import com.mykins.linkin.app.Router;
import com.mykins.linkin.app.kins.FastPositionIndicator;
import com.mykins.linkin.app.kins.KinsFragment;
import com.mykins.linkin.app.kins.profile.KinsProfileActivity;
import com.mykins.linkin.util.ConvertUtils;
import com.mykins.linkin.util.CustomViewUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yjn on 2017/11/5.
 */

public class DiskinsFragment extends BaseFragment {
    Unbinder mUnbinder;

    @BindView(R.id.diskins_fast_indicator)
    FastPositionIndicator mFastPositionIndicator;

    @BindView(R.id.diskins_fast_showIndicator)
    TextView mFastShowIndicator;

    @BindView(R.id.diskins_recyclerView)
    RecyclerView mRecyclerView;

    LinearLayoutManager mLinearLayoutManager;

    DiskinsAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ui_diskins_frag, null);
        mUnbinder = ButterKnife.bind(this, view);

        mLinearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new DiskinsAdapter(mActivity, this);
        mRecyclerView.setAdapter(mAdapter);
        CustomViewUtils.setRecyclerViewDivider(mContext, mRecyclerView, mLinearLayoutManager);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_diskins, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mActivity.finish();
            return true;
        } else if (item.getItemId() == R.id.diskins_menu_add) {
            Router.actKinsProfile(mActivity, KinsProfileActivity.TYPE_DIS_KINS);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    void loadData() {
        String[] names = new String[]{
                "李彦龙",
                "李浩鹏",
                "李天一",
                "李铁刚",
                "李君昊",
                "李国艳",
                "李恩德",
                "王满",
                "王琳",
                "王锐",
                "张观博",
                "张欣竹",
                "张欣阳",
                "刘佳乐",
                "刘慧娴",
                "刘嘉源",
                "刘盈锐",
                "陈鸿琳",
                "陈鸿玉",
                "陈鸿玲",
                "陈玲彤",
                "杨文锦",
                "杨泽晨",
                "杨博瀚",
                "杨伊珂",
                "赵泽晨",
                "赵子桐",
                "赵建川",
                "黄奕轩",
                "黄天罡",
                "黄亦琰",
                "黄亦琦",
                "吴皓龙",
                "吴雨萱",
                "吴雨晴",
                "吴亚吴",
                "徐玮",
                "徐艺鸣",
                "徐昊勇",
                "徐徫",
                "孙子淇",
                "孙一丹",
                "孙宛彤",
                "孙菱彤",
                "胡泽洋",
                "胡藁跃",
                "胡煜羲",
                "胡煜曦",
                "高悦跃",
                "高铭烁",
                "高爱敏",
                "高骏臣",
                "高歌",
                "林绮君",
                "林兴钰",
                "林兴易",
                "林兴渲",
                "何瑞",
                "何昊宇",
                "何昊霖",
                "何雨轩",
                "邓安翔",
                "邓安晏",
                "邓安宜",
                "柴波光",
                "柴波鸿",
                "柴波峻",
                "柴波涛",
                "傅炜博",
                "傅炜皓",
                "傅向阳",
                "蒋思渊",
                "蒋欣尉",
                "蒋兆聪",
                "蒋兆沿",
                "彭建林",
                "彭荣轩",
                "彭瑜轩",
                "彭钰轩"
        };
        List<String> rawData = Arrays.asList(names);
        Collections.sort(rawData, new KinsFragment.PinyinComparator());

        final HashMap<String, Integer> lettersMappingPosition = new HashMap<>();
        ArrayList<Object[]> data = new ArrayList<>();
        List<String> letters = new ArrayList<>();
        try {
            String lastLetter = null;
            int position = 0;

            KinsBean kinsBean = null;
            for (String s : rawData) {
                if (s == null || s.trim().length() == 0) continue;
                String letter = PinyinHelper.getShortPinyin(s).substring(0, 1);

                if (!letter.equals(lastLetter)) {
                    lastLetter = letter;

                    lettersMappingPosition.put(letter, position + letters.size());
                    letters.add(letter);
                    data.add(new String[]{letter});
                }
                kinsBean = new KinsBean(
                        "http://img.hb.aicdn.com/859b6e9c965eb9a1cb60dd095443dead5b96ab0578890-Ech9SR_fw658",
                        "痞子厨子",
                        "诞辰90周年",
                        "一轮明月");

                data.add(new Object[]{letter, kinsBean});
                position++;
            }
        } catch (PinyinException e) {
            e.printStackTrace();
        }

        mFastPositionIndicator.setLetters(letters.toArray(new String[0]));
        mFastPositionIndicator.setTextDialog(mFastShowIndicator);

        mFastPositionIndicator.setOnTouchingLetterChangedListener(new FastPositionIndicator.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                boolean first = s.equals(letters.get(0));
                final int selectedPos = lettersMappingPosition.get(s) + +(first ? 0 : 1);
                mLinearLayoutManager.scrollToPositionWithOffset(selectedPos, ConvertUtils.dp2px(getResources(), 23));
            }
        });

        mAdapter.updateData(data);
    }
}
