package com.mykins.linkin.app.start;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.R;
import com.mykins.linkin.app.Router;

/**
 * Created by yjn on 2017/11/4.
 */

public class StartActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_start_act);
        Router.actLogin(this);
        finish();
    }
}
