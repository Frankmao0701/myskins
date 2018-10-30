package com.mykins.linkin.app.diskins;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yjn on 2017/11/5.
 */

public class DisKinsActivity extends BaseActivity {
    Unbinder mUnbinder;

    @BindView(R.id.diskins_toolbar)
    Toolbar mToolbar;

    DiskinsFragment mContentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_diskins_act);
        mUnbinder = ButterKnife.bind(this, this);

        setSupportActionBar(mToolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            mContentFragment = new DiskinsFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.contentFrame, mContentFragment)
                    .commit();
        }
    }
}
