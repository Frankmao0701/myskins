package com.mykins.linkin.emoticon;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.widget.EditText;

/**
 * Created by yjn on 2016/3/30.
 */
public class EmojiFilter extends EmoticonFilter {

    public static final int WRAP_DRAWABLE = -1;
    private int emoticonSize = -1;

    @Override
    public void filter(EditText editText, CharSequence text, int start, int lengthBefore, int lengthAfter) {
        emoticonSize = emoticonSize == -1 ? EmoticonsKeyboardUtils.getFontHeight(editText) : emoticonSize;
        clearSpan(editText.getText(), start, text.toString().length());
        //系统表情
        emoticonSystemDisplay(editText.getContext(), editText.getText(), emoticonSize);
    }

    private void clearSpan(Spannable spannable, int start, int end) {
        if (start == end) {
            return;
        }
        EmojiSpan[] oldSpans = spannable.getSpans(start, end, EmojiSpan.class);
        for (int i = 0; i < oldSpans.length; i++) {
            spannable.removeSpan(oldSpans[i]);
        }
        ForegroundColorSpan[] colorSpans = spannable.getSpans(start, end, ForegroundColorSpan.class);
        for (int i = 0; i < colorSpans.length; i++) {
            spannable.removeSpan(colorSpans[i]);
        }
    }

    public static void emoticonDisplay(Context context, Spannable spannable, String emoticonName, int fontSize, int start, int end) {
        Drawable drawable = getDrawableFromAssets(context, emoticonName);
        if (drawable != null) {
            int itemHeight;
            int itemWidth;
            if (fontSize == WRAP_DRAWABLE) {
                itemHeight = drawable.getIntrinsicHeight();
                itemWidth = drawable.getIntrinsicWidth();
            } else {
                itemHeight = fontSize;
                itemWidth = fontSize;
            }

            drawable.setBounds(0, 0, itemHeight, itemWidth);
            EmojiSpan imageSpan = new EmojiSpan(drawable);
            spannable.setSpan(imageSpan, start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }
    }


    public static void emoticonSystemDisplay(Context context, Spannable spannable, int fontSize){
        int skip;
        String content = spannable.toString();
        int length = content.length();
        for (int i = 0; i < length; i += skip) {
            skip = 0;
            int icon = 0;
            char c = content.charAt(i);
            if (isEmojiUnicode(c)) {
                icon = getEmojiResource(c);
                skip = icon == 0 ? 0 : 1;
            }
            if (icon == 0) {
                int unicode = Character.codePointAt(content, i);
                skip = Character.charCount(unicode);
                if (unicode > 0xff) {
                    icon = getEmojiResource(unicode);
                }
            }
            if(icon > 0){
                Drawable drawable = context.getResources().getDrawable(icon);
                if (drawable != null) {
                    int itemHeight = drawable.getIntrinsicHeight();
                    int itemWidth = drawable.getIntrinsicWidth();
                    //可以缩小显示，不放大显示
                    if (fontSize < itemHeight || fontSize < itemWidth) {
                        itemHeight = fontSize;
                        itemWidth = fontSize;
                    }

                    drawable.setBounds(0, 0, itemHeight, itemWidth);
                    EmojiSpan imageSpan = new EmojiSpan(drawable);
                    spannable.setSpan(imageSpan, i, i + skip, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }
        }
    }

    public static boolean isSystemEmoji(String content) {
        int skip;
        int length = content.length();
        boolean isSystemEmoji = false;
        for (int i = 0; i < length; i += skip) {
            skip = 0;
            int icon = 0;
            char c = content.charAt(i);
            if (isEmojiUnicode(c)) {
                icon = getEmojiResource(c);
                skip = icon == 0 ? 0 : 1;
            }
            if (icon == 0) {
                int unicode = Character.codePointAt(content, i);
                skip = Character.charCount(unicode);
                if (unicode > 0xff) {
                    icon = getEmojiResource(unicode);
                }
            }
            if(icon > 0) {
                isSystemEmoji = true;
                break;
            }
        }
        return isSystemEmoji;
    }
}
