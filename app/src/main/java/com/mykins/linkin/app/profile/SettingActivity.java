package com.mykins.linkin.app.profile;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.mykins.linkin.R;
import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.app.Router;
import com.mykins.linkin.app.utils.DisplayUtils;
import com.mykins.linkin.view.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SettingActivity extends BaseActivity {
    @BindView(R.id.setting_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_day)
    TextView tv_day;
    @BindView(R.id.tv_night)
    TextView tv_night;

    Unbinder mUiBinder;

    @OnClick(R.id.profile_reset_rl)
    public void goGoResetPwd() {
        Router.actResetPwd(this);
    }

    @OnClick(R.id.profile_privacy_rl)
    public void goToPrivacy() {
        Router.actPrivacy(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mUiBinder = ButterKnife.bind(this, this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tv_day.setSelected(true);
        tv_night.setSelected(false);
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
                Log.e(TAG,r.left+"::"+r.top+"::"+r.right+"::"+r.bottom+"::"+ mToolbar.getHeight()+"::"+ DisplayUtils.screenHeight);


            }
        });

    }

    @Override
    protected void onDestroy() {
        mUiBinder.unbind();
        super.onDestroy();
    }
}
