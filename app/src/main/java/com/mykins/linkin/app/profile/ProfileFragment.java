package com.mykins.linkin.app.profile;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.mykins.linkin.R;
import com.mykins.linkin.app.BaseFragment;
import com.mykins.linkin.app.Router;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by jerry on 2017/9/6.
 */

public class ProfileFragment extends BaseFragment {
    private static final int REQUEST_CODE = 1001;
    Unbinder mUiBinder;
    private Disposable disposable;

    @OnClick(R.id.rl_statistics)
    public void goToStatics() {
        Router.actStatistics(mActivity);
    }

    @OnClick(R.id.rl_setting)
    public void goToSetting() {
        Router.actSetting(mActivity);
    }

//    @OnClick(R.id.rl_scan)
//    public void goToScan() {
//        Router.actScan(mActivity, REQUEST_CODE);
//    }

    @OnClick(R.id.rl_dashboard)
    public void goToGather() {
        Router.actGather(mActivity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.ui_profile_frag, null, false);
        mUiBinder = ButterKnife.bind(this, root);
        clickRequestPermisson(root);
        return root;
    }

    private void clickRequestPermisson(View root) {
        RxPermissions rxPermissions = new RxPermissions(this);
        disposable = RxView.clicks(root.findViewById(R.id.rl_scan))
                // Ask for permissions when button is clicked
                .compose(rxPermissions.ensureEach(Manifest.permission.CAMERA))
                .subscribe(permission -> {
                            Log.i(TAG, "Permission result " + permission);
                            if (permission.granted) {
                                Router.actScan(mActivity, REQUEST_CODE);
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                // Denied permission without ask never again
                                Toast.makeText(mActivity,
                                        "Denied permission without ask never again",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // Denied permission with ask never again
                                // Need to go to the settings
                                Toast.makeText(mActivity,
                                        "Permission denied, can't enable the camera",
                                        Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable t) {
                                Log.e(TAG, "onError", t);
                            }
                        },
                        new Action() {
                            @Override
                            public void run() {
                                Log.i(TAG, "OnComplete");
                            }
                        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUiBinder.unbind();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
