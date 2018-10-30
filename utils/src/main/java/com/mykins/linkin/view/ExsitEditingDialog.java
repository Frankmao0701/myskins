package com.mykins.linkin.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.mykins.linkin.util.ConvertUtils;
import com.mykins.linkin.utils.R;

/**
 * Created by yjn on 2017/11/25.
 */

public class ExsitEditingDialog extends DialogFragment {

    OnExsitingListener onExsitingListener;
    Button mCancel, mOk;

    public void setOnExsitingListener(OnExsitingListener listener) {
        this.onExsitingListener = listener;
    }

    public static ExsitEditingDialog newInstance() {
        return new ExsitEditingDialog();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.ui_dialog_exsit_editing, container);
        mCancel = root.findViewById(R.id.dialog_button_cancel);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onExsitingListener != null) onExsitingListener.onCancel();
            }
        });
        mOk = root.findViewById(R.id.dialog_button_ok);
        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onExsitingListener != null) onExsitingListener.onOk();
            }
        });
        root.findViewById(R.id.dialog_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        int width = ConvertUtils.dp2px(getResources(), 300);
        int height = ConvertUtils.dp2px(getResources(), 180);
        window.setLayout(width, height);
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void showDialog(AppCompatActivity activity) {
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
            ft.commit();
        }
        // Create and show the dialog.
        show(ft, "dialog");
    }

    public interface OnExsitingListener {
        void onCancel();

        void onOk();
    }
}
