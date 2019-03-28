package com.mykins.linkin.app;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;

import com.mykins.linkin.app.diskins.DisKinsActivity;
import com.mykins.linkin.app.feed.publish.ActivityDetailActivity;
import com.mykins.linkin.app.feed.publish.KinsRangeActivity;
import com.mykins.linkin.app.feed.publish.PublishActivityActivity;
import com.mykins.linkin.app.feed.publish.PublishShareActivity;
import com.mykins.linkin.app.feed.publish.ShareDetailActivity;
import com.mykins.linkin.app.kins.chat.ChatActivity;
import com.mykins.linkin.app.kins.chat.GroupContactActivity;
import com.mykins.linkin.app.kins.chat.ChatSettingActivity;
import com.mykins.linkin.app.kins.manage.KinsManageActivity;
import com.mykins.linkin.app.kins.profile.KinsProfileActivity;
import com.mykins.linkin.app.kins.profile.KinsProfileEditActivity;
import com.mykins.linkin.app.login.ForgetPwdActivity;
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
import com.mykins.linkin.app.utils.MyGlideEngine;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

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

    public static void actChat(Activity activity, int type) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, ChatActivity.class).putExtra("type", type));
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

    /**
     * 个人中心页面
     *
     * @param activity
     */
    public static void actUserInfo(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, MyInfoActivity.class));
    }

    /**
     * 添加亲友页面
     *
     * @param activity activity
     */
    public static void actAddKin(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, AddKinActivity.class));
    }

    /**
     * 汇总页面
     *
     * @param activity activity
     */
    public static void actGather(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, GatherActivity.class));
    }

    /**
     * 单聊设置页面
     *
     * @param activity activity
     */
    public static void actSingleChatSetting(Activity activity, int type) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, ChatSettingActivity.class).putExtra("type", type));
    }

    /**
     * 发布分享页面
     *
     * @param activity activity
     */
    public static void actPublishShare(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, PublishShareActivity.class));
    }

    /**
     * 发布活动页面
     *
     * @param activity activity
     */
    public static void actPublishActivity(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, PublishActivityActivity.class));
    }

    /**
     * 活动范围
     *
     * @param activity activity
     */
    public static void actKinsRange(Activity activity,int requestCode) {
        checkActivity(activity);
        activity.startActivityForResult(new Intent(activity, KinsRangeActivity.class),requestCode);
    }

    /**
     * 群组通讯录
     *
     * @param activity activity
     */
    public static void actGroupContact(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, GroupContactActivity.class));
    }

    /**
     * 活动详情页面
     *
     * @param activity activity
     */
    public static void actActivityDetail(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, ActivityDetailActivity.class));
    }

    /**
     * 分享详情页面
     *
     * @param activity activity
     */
    public static void actShareDetail(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, ShareDetailActivity.class));
    }

    /**
     * 忘记密码页面
     *
     * @param activity activity
     */
    public static void actForgetActivity(Activity activity) {
        checkActivity(activity);
        activity.startActivity(new Intent(activity, ForgetPwdActivity.class));
    }
    public static void actSysUlbum(Activity activity,int requestCode){
        Matisse.from(activity)
                .choose(MimeType.ofAll(), false) // 选择 mime 的类型
                .countable(true)
                .maxSelectable(1) // 图片选择的最多数量
                .gridExpectedSize(320)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new MyGlideEngine()) // 使用的图片加载引擎
                .forResult(requestCode); // 设置作为标记的请求码
    }

}
