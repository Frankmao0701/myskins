package com.mykins.linkin.app.kins.manage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.R;
import com.mykins.linkin.util.ConvertUtils;
import com.mykins.linkin.util.CustomViewUtils;
import com.mykins.linkin.util.ResUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yjn on 2017/11/3.
 */

public class KinsManageActivity extends BaseActivity {
    @BindView(R.id.kins_manage_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.kins_manage_tabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.kins_manage_viewPager)
    ViewPager mViewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_kins_manage_act);
        ButterKnife.bind(this, this);

        setSupportActionBar(mToolbar);

        NewKinsFragment newKinsFragment = new NewKinsFragment();
        GroupSettingFragment groupSettingFragment = new GroupSettingFragment();
        AddingKinsFragment addingKinsFragment = new AddingKinsFragment();

        List<Fragment> fragments = Arrays.asList(new Fragment[]{
                newKinsFragment,
                groupSettingFragment,
                addingKinsFragment
        });
        List<String> titles = Arrays.asList(new String[]{
                ResUtils.string(R.string.title_new_kins),
                ResUtils.string(R.string.title_group_setting),
                ResUtils.string(R.string.title_adding_kins)
        });

        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), fragments, titles));
        mTabLayout.setupWithViewPager(mViewPager);
        int start, end;
        start = end = ConvertUtils.dp2px(getResources(), 20);
        CustomViewUtils.setUpIndicatorWidth(mTabLayout, start, end);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class PagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragList;
        private List<String> titleList;

        public PagerAdapter(FragmentManager fm, List<Fragment> fragList, List<String> titleList) {
            super(fm);
            this.fragList = fragList;
            this.titleList = titleList;
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragList.get(arg0);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public int getCount() {
            return fragList.size();
        }
    }
}
