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
    public static final int TYPE_SINGLE = 0;
    public static final int TYPE_GROUP = 1;
    Unbinder unbinder;
    ChatFragment mChatFragment;

    @BindView(R.id.chat_toolbar)
    Toolbar mToolbar;
    private int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_chat_act);
        unbinder = ButterKnife.bind(this, this);
        type = getIntent().getIntExtra("type", 0);
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
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        mChatFragment.setArguments(bundle);
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
