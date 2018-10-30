package com.mykins.linkin.app.kins.manage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mykins.linkin.app.BaseFragment;
import com.mykins.linkin.R;

import butterknife.ButterKnife;

/**
 * Created by yjn on 2017/11/3.
 */

public class AddingKinsFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ui_adding_kins_frag, null);
        ButterKnife.bind(this, view);
        return view;
    }
}
