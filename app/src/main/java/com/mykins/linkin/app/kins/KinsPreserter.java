package com.mykins.linkin.app.kins;

import android.arch.lifecycle.LifecycleOwner;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.mykins.linkin.bean.GroupBean;
import com.mykins.linkin.component.AppSchedulers;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by yjn on 2018/1/12.
 */

public class KinsPreserter implements KinsContract.Presenter {
    private final AutoDisposeConverter<ArrayList<String[]>> autoDisposeConverter;
    private AppSchedulers appSchedulers;
    private KinsContract.View view;
    private LifecycleOwner lifecycleOwner;

    final HashMap<String, Integer> lettersMappingPosition = new HashMap<>();
    final ArrayList<String[]> data = new ArrayList<>();
    final ArrayList<String> letters = new ArrayList<>();
    final ArrayList<GroupBean> groups = new ArrayList<>();

    @Inject
    public KinsPreserter(AppSchedulers appSchedulers,
                         KinsContract.View view,
                         LifecycleOwner lifecycleOwner) {
        this.appSchedulers = appSchedulers;
        this.view = view;
        this.lifecycleOwner = lifecycleOwner;
        this.autoDisposeConverter = AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner));
    }

    @Override
    public void resume() {
        if (data.size() > 0) {
            return;
        }

        GroupBean groupBean = new GroupBean();
        groupBean.setMember_avatars(new String[]{
                "http://img.hb.aicdn.com/2b71a46e67af4b3a18e778db456d4afd4a41ba346a6b25-m1iiQl_fw658",
                "http://img.hb.aicdn.com/ecb247f3cc10d10b54b406715c144cc2ee34b98b301d9-7dhS3O_fw658"
        });
        groupBean.setName("Group1");
        groups.add(groupBean);
        if (view != null) {
            view.showGroup(groups);
        }

        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> e) throws Exception {
                List<String> rawData = Arrays.asList(names);
                Collections.sort(rawData, new KinsFragment.PinyinComparator());

                try {
                    String lastLetter = null;
                    int position = 0;

                    for (String s : rawData) {
                        if (s == null || s.trim().length() == 0) continue;
                        String letter = PinyinHelper.getShortPinyin(s).substring(0, 1);

                        if (!letter.equals(lastLetter)) {
                            lastLetter = letter;

                            lettersMappingPosition.put(letter, position + letters.size());
                            letters.add(letter);
                            data.add(new String[]{letter});
                        }
                        data.add(new String[]{letter, s});
                        position++;
                    }
                    e.onNext(data);
                    e.onNext(letters);
                    e.onNext(lettersMappingPosition);
                } catch (PinyinException error) {
                    error.printStackTrace();
                } finally {
                    e.onComplete();
                }
            }
        }).subscribeOn(appSchedulers.diskIO())
                .observeOn(appSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        //ignore events
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        show();
                    }
                });
    }

    void show() {
        if (view != null) {
            view.showData(data);
            view.showIndexer(letters, lettersMappingPosition);
        }
    }

    String[] names = new String[]{
            "李彦龙",
            "李浩鹏",
            "李天一",
            "李铁刚",
            "李君昊",
            "李国艳",
            "李恩德",
            "王满",
            "王琳",
            "王锐",
            "张观博",
            "张欣竹",
            "张欣阳",
            "刘佳乐",
            "刘慧娴",
            "刘嘉源",
            "刘盈锐",
            "陈鸿琳",
            "陈鸿玉",
            "陈鸿玲",
            "陈玲彤",
            "杨文锦",
            "杨泽晨",
            "杨博瀚",
            "杨伊珂",
            "赵泽晨",
            "赵子桐",
            "赵建川",
            "黄奕轩",
            "黄天罡",
            "黄亦琰",
            "黄亦琦",
            "吴皓龙",
            "吴雨萱",
            "吴雨晴",
            "吴亚吴",
            "徐玮",
            "徐艺鸣",
            "徐昊勇",
            "徐徫",
            "孙子淇",
            "孙一丹",
            "孙宛彤",
            "孙菱彤",
            "胡泽洋",
            "胡藁跃",
            "胡煜羲",
            "胡煜曦",
            "高悦跃",
            "高铭烁",
            "高爱敏",
            "高骏臣",
            "高歌",
            "林绮君",
            "林兴钰",
            "林兴易",
            "林兴渲",
            "何瑞",
            "何昊宇",
            "何昊霖",
            "何雨轩",
            "邓安翔",
            "邓安晏",
            "邓安宜",
            "柴波光",
            "柴波鸿",
            "柴波峻",
            "柴波涛",
            "傅炜博",
            "傅炜皓",
            "傅向阳",
            "蒋思渊",
            "蒋欣尉",
            "蒋兆聪",
            "蒋兆沿",
            "彭建林",
            "彭荣轩",
            "彭瑜轩",
            "彭钰轩"
    };
}
