package com.mykins.linkin.app.feed.publish;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.mykins.linkin.R;
import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.util.GlideHelper;
import com.mykins.linkin.widget.ReplyDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 活动详情页面
 */
public class ActivityDetailActivity extends BaseActivity {
    @BindView(R.id.activity_detail_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.profile_add_avatar_img)
    ImageView image_avatar;
    @BindView(R.id.img_activity_theme)
    ImageView img_activity_theme;

    Unbinder mUiBinder;

    @OnClick(R.id.btn_reply)
    public void replyDialog() {
        final ReplyDialogFragment dialog = new ReplyDialogFragment();
        dialog.show(getFragmentManager(), "dialog");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_detail);
        mUiBinder = ButterKnife.bind(this, this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        String url = "http://img.hb.aicdn.com/cd392e199f22b27f8d4acb4d4026a79eab46ceeed414-GM93zI_fw658";
        GlideHelper.loadUrlRound(mContext, url, image_avatar);
        String urlTheme = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553796933684&di=6705fe902e823203463d708086d526f8&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2Fd000baa1cd11728b12a6debfc2fcc3cec2fd2ca7.jpg";
        GlideHelper.loadUrl(mContext, urlTheme, img_activity_theme);

    }

    @Override
    protected void onDestroy() {
        mUiBinder.unbind();
        super.onDestroy();
    }
}
