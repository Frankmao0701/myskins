package com.mykins.linkin;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by yjn on 2017/12/10.
 */

public final class SnackBarHelper {
    public final static int ERROR_DURATION = 10 * 1000;

    public static void showError(View attachView, String error, int duration){
        Snackbar.make(attachView, error, duration).show();
    }
}
