package com.mykins.linkin.app;

import android.app.Activity;
import android.content.Intent;

import com.mykins.linkin.app.diskins.DisKinsActivity;
import com.mykins.linkin.app.feed.publish.KinsRangeActivity;
import com.mykins.linkin.app.feed.publish.PublishShareActivity;
import com.mykins.linkin.app.kins.chat.ChatActivity;
import com.mykins.linkin.app.kins.chat.SingleChatSettingActivity;
import com.mykins.linkin.app.kins.manage.KinsManageActivity;
import com.mykins.linkin.app.kins.profile.KinsProfileActivity;
import com.mykins.linkin.app.kins.profile.KinsProfileEditActivity;
import com.mykins.linkin.app.login.LoginActivity;
import com.mykins.linkin.app.profile.AddKinActivity;
import com.mykins.linkin.app.profile.MyInfoActivity;
import com.mykins.linkin.app.profile.PrivacyActivity;
import com.mykins.linkin.app.profile.ResetPasswordActivity;
import com.mykins.linkin.app.profile.SettingActivity;
import com.mykins.linkin.app.profile.StatisticsActivity;
import com.mykins.linkin.app.profile.gather.GatherActivity;
import com.mykins.linkin.app.search.SearchActivity;
import com.mykins.linkin.app.start.StartActivity;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import java.io.Serializable;

/**
 * Created by yjn on 2017/11/3.
 * 负责Activity 、Fragment的跳转
 */

public final class Router {

    private static void checkActivity(Activity activity) {
        if (activity == null) throw new IllegalArgumentException("activity == null");
    }

    public static void actKinsManage(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(
                activity,
                KinsManageActivity.class
        ));
    }

    public static void actLogin(StartActivity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }

    public static void actSearch(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, SearchActivity.class));
    }

    public static void actDiskins(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, DisKinsActivity.class));
    }

    public static void actChat(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, ChatActivity.class));
    }

    public static void actKinsProfile(Activity activity, int type) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, KinsProfileActivity.class).putExtra("data", type));
    }

    public static void actKinsProfileEdit(Activity activity, final int category, Serializable data) {
        checkActivity(activity);
        activity.startActivityForResult(new Intent(activity, KinsProfileEditActivity.class)
                .putExtra("category", category)
                .putExtra("data", data), category);
    }

    public static void actStatistics(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, StatisticsActivity.class));
    }

    public static void actSetting(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, SettingActivity.class));
    }

    public static void actScan(Activity activity, int requestCode) {
        checkActivity(activity);
        activity.startActivityForResult(new Intent(activity, CaptureActivity.class), requestCode);
    }

    public static void actResetPwd(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, ResetPasswordActivity.class));
    }

    public static void actPrivacy(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, PrivacyActivity.class));
    }

    public static void actUserInfo(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, MyInfoActivity.class));
    }

    public static void actAddKin(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, AddKinActivity.class));
    }

    public static void actGather(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, GatherActivity.class));
    }
    public static void actSingleChatSetting(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, SingleChatSettingActivity.class));
    }
    public static void actPublishShare(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, PublishShareActivity.class));
    }
    public static void actKinsRange(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, KinsRangeActivity.class));
    }

}
