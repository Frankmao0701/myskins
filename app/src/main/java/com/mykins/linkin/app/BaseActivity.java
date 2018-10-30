package com.mykins.linkin.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jerry on 2017/8/24.
 */

public class BaseActivity extends AppCompatActivity {
    public final String TAG = this.getClass().getSimpleName();
    protected Context mContext;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume...");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
