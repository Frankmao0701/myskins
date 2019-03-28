package com.mykins.linkin.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.mykins.linkin.utils.R;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by yjn on 2017/11/4.
 */

public final class GlideHelper {
    private static int radius;
    private static boolean isInitials;

    private void checkInit(Context context) {
        if (!isInitials) {
            throw new RuntimeException("GlideHelper not initial");
        }
    }

    public static void init(Context context) {
        radius = ConvertUtils.dp2px(context.getResources(), 12);
        isInitials = true;
    }

    public static void loadUrlRound(Fragment fragment, final String url, final ImageView target) {
        loadUrlRound(null, fragment, url, target);
    }

    public static void loadUrlRound(Activity activity, final String url, final ImageView target) {
        loadUrlRound(activity, null, url, target);
    }

    public static void loadUrlRound(Activity activity, Fragment fragment, final String url, final ImageView target) {
        MultiTransformation multi = new MultiTransformation(
                new RoundedCornersTransformation(radius, 0, RoundedCornersTransformation.CornerType.ALL));

        RequestManager rm = fragment != null ? Glide.with(fragment) : Glide.with(activity);
        rm.load(url).apply(RequestOptions.bitmapTransform(multi)).into(target);
    }

    public static void loadUrlRound(Activity activity, Fragment fragment, final String url, final ImageView target, int width, int height) {
        MultiTransformation multi = new MultiTransformation(
                new RoundedCornersTransformation(radius, 0, RoundedCornersTransformation.CornerType.ALL));

        RequestManager rm = fragment != null ? Glide.with(fragment) : Glide.with(activity);
        rm.load(url).apply(RequestOptions.bitmapTransform(multi).overrideOf(width, height).centerCrop()).into(target);
    }

    public static void loadUrl(Context context, final String url, final ImageView target) {
        Glide.with(context)
                .load(url)
                .apply(RequestOptions.fitCenterTransform())
                .into(target);
    }
}
