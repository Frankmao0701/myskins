package com.mykins.linkin.emoticon;

import android.text.SpannableStringBuilder;
import android.text.util.Linkify;
import android.widget.TextView;

/**
 * Created by yjn on 2017/11/19.
 */

public final class EmoticonTextHelper {

    public static void emoticonEditText(TextView textView, CharSequence content, int emoticonSize){
        if (content == null) return;

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(content);
        EmojiFilter.emoticonSystemDisplay(textView.getContext(), spannableStringBuilder, emoticonSize);
        try {
            // add linkify to textview
            Linkify.addLinks(spannableStringBuilder , Linkify.EMAIL_ADDRESSES);
            Linkify.addLinks(spannableStringBuilder , Linkify.WEB_URLS);
//            URLSpan[] urls = spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), URLSpan.class);
//            for (URLSpan span : urls) {
//                com.qooapp.qoohelper.util.TextUtils.makeLinkClickable(textView.getContext(), spannableStringBuilder, span);
//            }
//            tv_content.setMovementMethod(TextViewFixTouchConsume.LocalLinkMovementMethod.getInstance());
//            tv_content.setLinkTextColor(ResUtils.color());
        } catch (Exception e) {
            //ignore
        }
        textView.setText(spannableStringBuilder);
    }
}
