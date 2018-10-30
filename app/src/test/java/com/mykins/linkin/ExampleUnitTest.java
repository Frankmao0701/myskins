package com.mykins.linkin;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void chinese2pinyin() {
        String str = "你好趙子龍";
        try {
            String sh = PinyinHelper.getShortPinyin(str); // nhsj
            assertEquals("nhzzl", sh);
        } catch (PinyinException e) {
            e.printStackTrace();
        }
    }
}