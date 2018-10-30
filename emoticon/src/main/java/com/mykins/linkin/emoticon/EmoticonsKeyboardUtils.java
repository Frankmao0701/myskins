package com.mykins.linkin.emoticon;

import android.content.Context;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yjn on 2016/3/30.
 */
public class EmoticonsKeyboardUtils {
    //表情是字體大小的多少倍
    private static final float EMOTION_ROATE = 2.2f;
    private static final String EXTRA_DEF_KEYBOARDHEIGHT = "DEF_KEYBOARDHEIGHT";
    private static final int DEF_KEYBOARD_HEAGH_WITH_DP = 300;
    private static int mDefKeyboardHeight = -1;
    //表情
    public static final Pattern EMO_RANGE = Pattern.compile("\\[[a-zA-Z0-9\\u4e00-\\u9fa5]+\\]");
    //圖片文件
    public static final Pattern IMAGE_PATTERN = Pattern
            .compile("\\[image\\s*\\](.+?)\\[\\s*/\\s*image\\]");

    public static String getImage(String text){
        return "[image]" + text + "[/image]";
    }

    public static Matcher getEmojiMatcher(CharSequence matchStr) {
        return EMO_RANGE.matcher(matchStr);
    }

    public static Matcher getImageMatcher(CharSequence matchStr) {
        return IMAGE_PATTERN.matcher(matchStr);
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int getFontHeight(TextView textView) {
        Paint paint = new Paint();
        paint.setTextSize(textView.getTextSize());
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (int) Math.ceil(fm.bottom - fm.top);
    }

    /**
     * 获取表情键盘高度
     * @param context
     * @return
     */
    public static int getDefKeyboardHeight(Context context) {
        if (mDefKeyboardHeight < 0) {
            mDefKeyboardHeight = dip2px(context, DEF_KEYBOARD_HEAGH_WITH_DP);
        }
        int height = PreferenceManager.getDefaultSharedPreferences(context).getInt(EXTRA_DEF_KEYBOARDHEIGHT, 0);
        return mDefKeyboardHeight = height > 0 && mDefKeyboardHeight != height ? height : mDefKeyboardHeight;
    }

    /**
     * 设置表情键盘高度
     * @param context
     * @param height
     */
    public static void setDefKeyboardHeight(Context context, int height) {
        if (mDefKeyboardHeight != height) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(EXTRA_DEF_KEYBOARDHEIGHT, height).commit();
            mDefKeyboardHeight = height;
        }
    }

    /**
     * 开启软键盘
     */
    public static void openSoftKeyboard(EditText et) {
        if (et != null) {
            et.setFocusable(true);
            et.setFocusableInTouchMode(true);
            et.requestFocus();
            InputMethodManager inputManager = (InputMethodManager) et.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(et, 0);
        }
    }

    /**
     * 关闭软键盘
     */
    public static void closeSoftKeyboard(View view) {
        if (view == null) {
            return;
        }
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
