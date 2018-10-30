package com.mykins.linkin.emoticon;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.widget.EditText;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yjn on 2016/3/29.
 */
public abstract class EmoticonFilter {

    private static final Pattern mEmojiPattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]|[?]|[@]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

    public abstract void filter(EditText editText, CharSequence text, int start, int lengthBefore, int lengthAfter);

    public static Drawable getDrawableFromAssets(Context context, String emoticonName) {
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(context.getAssets().open(emoticonName));
            return new BitmapDrawable(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Drawable getDrawableFromFile(String filePath) {
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeFile(filePath);
            return new BitmapDrawable(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Drawable getDrawable(Context context, String emojiName) {
        if(TextUtils.isEmpty(emojiName)){
            return null;
        }
        if(emojiName.indexOf(".") >= 0){
            emojiName = emojiName.substring(0,emojiName.indexOf("."));
        }
        int resID = context.getResources().getIdentifier(emojiName, "drawable", context.getPackageName());
        try {
            if(Build.VERSION.SDK_INT >= 21)
                return context.getResources().getDrawable(resID, (Resources.Theme) null);
            return context.getResources().getDrawable(resID);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getEmojiResource(int codePoint) {
        int resId = DefAssetsEmoticons.sEmojisMap.get(codePoint);
        if(resId == 0){
            resId = DefAssetsEmoticons.sOtherMap.get(codePoint);
        }
        return resId;
    }

    public static boolean isEmojiUnicode(char c) {
        return ((c >> 12) == 0xe);
    }

    /**
     * @param text
     * @return
     */
    public static String replaceEmoji(String text) {
        Matcher emojiMatcher = mEmojiPattern.matcher(text);
        return emojiMatcher.replaceAll("");
    }
}
