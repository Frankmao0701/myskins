package com.mykins.linkin.app.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mykins.linkin.app.BaseFragment;
import com.mykins.linkin.R;
import com.mykins.linkin.app.Router;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by jerry on 2017/9/6.
 */

public class ProfileFragment extends BaseFragment {
    private static final int REQUEST_CODE = 1001;
    Unbinder mUiBinder;

    @OnClick(R.id.rl_statistics)
    public void goToStatics() {
        Router.actStatistics(mActivity);
    }

    @OnClick(R.id.rl_setting)
    public void goToSetting() {
        Router.actSetting(mActivity);
    }

    @OnClick(R.id.rl_scan)
    public void goToScan() {
        Router.actScan(mActivity, REQUEST_CODE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.ui_profile_frag, null, false);
        mUiBinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUiBinder.unbind();
    }
}
