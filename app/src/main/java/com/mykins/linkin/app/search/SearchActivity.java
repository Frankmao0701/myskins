package com.mykins.linkin.app.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

/**
 * Created by yjn on 2017/11/4.
 */

public class SearchActivity extends BaseActivity {
    Unbinder mUnBinder;
    @BindView(R.id.search_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.include_search_input)
    EditText mSearchInput;

    @BindView(R.id.include_search_clear)
    ImageView mSearchClear;

    SearchFragment mSearchFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_search_act);
        mUnBinder = ButterKnife.bind(this, this);

        setSupportActionBar(mToolbar);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.contentFrame);
        if(fragment == null){
            mSearchFragment = new SearchFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.contentFrame, mSearchFragment)
                    .commit();
        }
    }

    @OnTextChanged(R.id.include_search_input)
    void onSearchInput(CharSequence text){
        if (!TextUtils.isEmpty(text)){
            mSearchClear.setVisibility(View.VISIBLE);
            mSearchFragment.loadData();
        }else{
            mSearchFragment.showStart();
            mSearchClear.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.include_search_clear)
    void onSearchClear(){
        mSearchFragment.showStart();
        mSearchInput.setText("");
        mSearchClear.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mUnBinder.unbind();
        super.onDestroy();
    }
}
