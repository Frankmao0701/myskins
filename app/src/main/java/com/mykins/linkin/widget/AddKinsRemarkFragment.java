package com.mykins.linkin.widget;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.mykins.linkin.R;
import com.mykins.linkin.util.ConvertUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Frankmao on 2017/6/19.
 */

public class AddKinsRemarkFragment extends DialogFragment {
    Unbinder mUiBinder;

    private Bitmap mBitmap;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final Window window = getDialog().getWindow();
        View view = inflater.inflate(R.layout.dialog_addkins_remark, null, false);//需要用android.R.id.content这个view
        mUiBinder = ButterKnife.bind(this, view);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//注意此处
//        window.setLayout(ConvertUtils.dp2px(getActivity(), 196), ViewGroup.LayoutParams.WRAP_CONTENT);
        return view;

    }


    private OnDialogDismissListener onDialogDismissListener;
    private onDialogClick clickListener;

    public void setOnDialogClickListener(onDialogClick clickListener) {
        this.clickListener = clickListener;
    }

    public void setOnDialogDismissListener(OnDialogDismissListener onDialogDismissListener) {
        this.onDialogDismissListener = onDialogDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (this.onDialogDismissListener != null) {
            this.onDialogDismissListener.onDismiss();
        }
    }


    public interface onDialogClick {
        void onSureClick(boolean isCheck);

        void onLeaveClick();
    }

    public interface OnDialogDismissListener {
        void onDismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUiBinder.unbind();
    }
}
