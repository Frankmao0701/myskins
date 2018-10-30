package com.mykins.linkin.app.main;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.R;
import com.mykins.linkin.app.Router;
import com.mykins.linkin.app.feed.FeedFragment;
import com.mykins.linkin.app.kins.KinsFragment;
import com.mykins.linkin.app.profile.ProfileFragment;
import com.mykins.linkin.util.GlideHelper;
import com.mykins.linkin.util.ResUtils;
import com.mykins.linkin.widget.AddKinsRemarkFragment;
import com.mykins.linkin.widget.QRCodeDialogFragment;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static android.support.design.widget.TabLayout.MODE_FIXED;

/**
 * Created by jerry on 2017/9/4.
 */

public class MainActivity extends BaseActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    KinsFragment mKinsFragment;
    FeedFragment mFeedFragment;
    ProfileFragment mProfileFragment;

    Unbinder mUiBinder;

    @BindView(R.id.main_toolbar)
    Toolbar mToolBar;

    @BindView(R.id.main_profile_head)
    FrameLayout mProfileHead;

    @BindView(R.id.main_profile_bg)
    ImageView mProfileBg;

    @BindView(R.id.profile_avatar)
    ImageView mProfileAvatar;

    @BindView(R.id.profile_name)
    TextView mProfileName;

    @BindView(R.id.profile_id)
    TextView mProfileId;

    @BindView(R.id.profile_qrcode)
    ImageView mProfileQr;

    @BindView(R.id.main_profile_info)
    RelativeLayout mProfileInfo;

    @BindView(R.id.main_tabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.main_viewPager)
    ViewPager mViewPager;

    private int mCurrentPager;

    @OnClick(R.id.profile_qrcode)
    public void goToQrCode() {
        final QRCodeDialogFragment dialog = new QRCodeDialogFragment();
        dialog.setOnDialogClickListener(new QRCodeDialogFragment.onDialogClick() {
            @Override
            public void onSureClick(boolean isCheck) {

            }

            @Override
            public void onLeaveClick() {
                dialog.dismiss();
            }
        });
        dialog.show(getFragmentManager(), "dialog");
    }

    @OnClick(R.id.profile_avatar)
    public void goToAddKin() {
        Router.actAddKin(this);
    }

    @OnClick({R.id.profile_id, R.id.profile_name})
    public void goToUserInfo() {
        Router.actUserInfo(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_main_act);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        mUiBinder = ButterKnife.bind(this, this);

        setSupportActionBar(mToolBar);


        final List<Fragment> fragments = Arrays.asList(new Fragment[]{
                mKinsFragment = new KinsFragment(),
                mFeedFragment = new FeedFragment(),
                mProfileFragment = new ProfileFragment()
        });
        final List<String> tabTitles = Arrays.asList(new String[]{
                ResUtils.string(R.string.title_kin),
                ResUtils.string(R.string.title_xun),
                ResUtils.string(R.string.title_profile)
        });
        final List<String> titles = Arrays.asList(new String[]{
                ResUtils.string(R.string.title_contact),
                ResUtils.string(R.string.title_feed),
                ResUtils.string(R.string.title_feed)
        });

        final List<Integer> tabIcons = Arrays.asList(new Integer[]{
                R.mipmap.ic_kin,
                R.mipmap.ic_xun,
                R.mipmap.ic_profile
        });

        final List<Integer> tabIconsSelected = Arrays.asList(new Integer[]{
                R.mipmap.ic_kin_selected,
                R.mipmap.ic_feed_selected,
                R.mipmap.ic_profile_selected
        });
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(), fragments, tabTitles));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            float lastOffset = 0.0f;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 1) {
                    float obstrOffset = Math.abs(positionOffset - lastOffset);

                    Log.d("onPageScrolled", obstrOffset + "");

                    int alpha = (int) (255 * (1 - positionOffset));
                    CharSequence alphaHex = String.format("%02X", alpha);
                    CharSequence hexColorValue = "#" + alphaHex + "27302f";
                    int colorPrimary = Color.parseColor(hexColorValue.toString());

                    int picAlpha = 255 - alpha;
                    //setImmersiveStyle(colorPrimary, position==1&&positionOffset>0, picAlpha);
                }
            }

            @Override
            public void onPageSelected(int pos) {
                mCurrentPager = pos;
                //set title
                setTitle(titles.get(pos));

                for (int i = 0; i < mTabLayout.getTabCount(); i++) {
                    ImageView icon = mTabLayout.getTabAt(i).getCustomView().findViewById(R.id.icon);
                    icon.setImageResource(pos == i ? tabIconsSelected.get(i) : tabIcons.get(i));
                }

                setImmersiveStyle(pos == 2 ? Color.TRANSPARENT : ResUtils.color(R.color.colorPrimary), pos == 2, 255);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {

            View customView = LayoutInflater.from(this).inflate(R.layout.layout_tab_custom, null);
            customView.setTag(i);
            ImageView icon = customView.findViewById(R.id.icon);
            icon.setImageResource(i == mCurrentPager ? tabIconsSelected.get(i) : tabIcons.get(i));

            TextView title = customView.findViewById(R.id.title);
            title.setText(tabTitles.get(i));

            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            tab.setCustomView(customView);
            customView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (int) v.getTag();
                    mViewPager.setCurrentItem(pos, false);


                }
            });
        }
        mTabLayout.setTabMode(MODE_FIXED);

        String url = "http://img.hb.aicdn.com/cd392e199f22b27f8d4acb4d4026a79eab46ceeed414-GM93zI_fw658";
        GlideHelper.loadUrlRound(this, url, mProfileAvatar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUiBinder.unbind();
    }

    void setImmersiveStyle(int colorPrimary, boolean showPic, int alpha) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(colorPrimary);
        }
        if (showPic) {
            mProfileHead.setVisibility(View.VISIBLE);

            mProfileBg.setAlpha(alpha);
            mProfileAvatar.setAlpha(alpha);
            mProfileQr.setAlpha(alpha);

            int textColor = Color.argb(alpha, 255, 255, 255);
            mProfileName.setTextColor(textColor);
            mProfileId.setTextColor(textColor);

        } else {
            mProfileHead.setVisibility(View.GONE);
        }
        mToolBar.setBackgroundColor(colorPrimary);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return this.dispatchingAndroidInjector;
    }

    public class MainPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragList;
        private List<String> titleList;

        public MainPagerAdapter(FragmentManager fm, List<Fragment> fragList, List<String> titleList) {
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
