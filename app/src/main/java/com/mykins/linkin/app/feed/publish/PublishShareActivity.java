package com.mykins.linkin.app.feed.publish;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.mykins.linkin.R;
import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.app.Router;
import com.mykins.linkin.app.utils.MyGlideEngine;
import com.mykins.linkin.util.GlideHelper;
import com.zhihu.matisse.Matisse;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PublishShareActivity extends BaseActivity {
    public static final int REQUEST_CODE = 0x11;
    public static final int REQUEST_CODE_ULBUM = 0x12;
    @BindView(R.id.publish_share_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.publish_share_add_img)
    ImageView mPhoto;

    Unbinder mUiBinder;

    private List<Uri> mSelected;

    @OnClick(R.id.share_rang_rl)
    public void goToRange() {
        Router.actKinsRange(this, REQUEST_CODE);
    }

    @OnClick(R.id.publish_share_add_img)
    public void goToSysUlbum() {
        Router.actSysUlbum(this, REQUEST_CODE_ULBUM);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_publish_share);
        super.onCreate(savedInstanceState);
        mUiBinder = ButterKnife.bind(this, this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_publish, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.publish_menu_send) {
            Router.actShareDetail(mContext);
            Toast.makeText(mContext, "发布成功", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {

        } else if (requestCode == REQUEST_CODE_ULBUM && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            Log.d(TAG, "mSelected: " + mSelected);
            if (mSelected != null && mSelected.size() == 1) {
                String url = mSelected.get(0).toString();
                GlideHelper.loadUrl(mContext, url, mPhoto);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        mUiBinder.unbind();
        super.onDestroy();
    }
}
