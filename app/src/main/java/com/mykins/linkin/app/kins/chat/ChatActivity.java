package com.mykins.linkin.app.kins.chat;

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
 * Created by yjn on 2017/11/12.
 */

public class ChatActivity extends BaseActivity {
    Unbinder unbinder;
    ChatFragment mChatFragment;

    @BindView(R.id.chat_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_chat_act);
        unbinder = ButterKnife.bind(this, this);

        setSupportActionBar(mToolbar);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(android.R.id.content);
        if (fragment == null) {
            fm.beginTransaction()
                    .add(R.id.contentFrame, mChatFragment = new ChatFragment())
                    .commit();
        } else {
            mChatFragment = (ChatFragment) fragment;
        }
    }

    @Override
    public void onBackPressed() {
        if (!mChatFragment.onBackPressed())
            super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
