package com.mykins.linkin.app.kins.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.mykins.linkin.R;
import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.app.Router;
import com.mykins.linkin.app.feed.publish.KinsRangeAdapter;
import com.mykins.linkin.bean.GroupContactBean;
import com.mykins.linkin.bean.KinsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class GroupContactActivity extends BaseActivity {
    @BindView(R.id.group_contact_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycle_group_contact)
    RecyclerView recycle_group_contact;


    Unbinder mUiBinder;
    private GroupContactAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_group_contact);
        super.onCreate(savedInstanceState);
        mUiBinder = ButterKnife.bind(this, this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initView();


    }

    private void initView() {
        List<GroupContactBean> data = new ArrayList<>();

        GroupContactBean indexBean = new GroupContactBean();
        indexBean.type = GroupContactBean.TYPE_INDEX;
        indexBean.letter = "C";
        data.add(indexBean);

        GroupContactBean contactBean1 = new GroupContactBean();
        contactBean1.type = GroupContactBean.TYPE_CONTACT;
        contactBean1.letter = "C";
        List<KinsBean> kinsBeans = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            KinsBean kinsBean = new KinsBean();
            kinsBean.setName("李橙凌" + i);
            kinsBeans.add(kinsBean);
        }
        contactBean1.kins = kinsBeans;
        data.add(contactBean1);

        GroupContactBean indexBean2 = new GroupContactBean();
        indexBean2.type = GroupContactBean.TYPE_INDEX;
        indexBean2.letter = "M";
        data.add(indexBean);

        GroupContactBean contactBean2 = new GroupContactBean();
        contactBean2.type = GroupContactBean.TYPE_CONTACT;
        contactBean2.letter = "M";
        List<KinsBean> kinsBeans2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            KinsBean kinsBean = new KinsBean();
            kinsBean.setName("毛毛" + i);
            kinsBeans2.add(kinsBean);
        }
        contactBean2.kins = kinsBeans2;
        data.add(contactBean2);

        if (mAdapter == null) {
            mAdapter = new GroupContactAdapter(this);
        }
        if (mLayoutManager == null) {
            mLayoutManager = new LinearLayoutManager(this);
        }
        recycle_group_contact.setLayoutManager(mLayoutManager);
        recycle_group_contact.setAdapter(mAdapter);
        mAdapter.add(data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_group_contact, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.group_contact_done) {
            Toast.makeText(mContext, "完成", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        mUiBinder.unbind();
        super.onDestroy();
    }
}
