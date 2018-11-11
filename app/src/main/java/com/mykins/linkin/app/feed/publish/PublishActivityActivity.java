package com.mykins.linkin.app.feed.publish;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mykins.linkin.R;
import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.app.Router;
import com.mykins.linkin.app.kins.profile.KinsProfileEditActivity;
import com.mykins.linkin.app.utils.PickerUtils;
import com.mykins.linkin.bean.ProvinceBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class PublishActivityActivity extends BaseActivity {
    @BindView(R.id.publish_activity_toolbar)
    Toolbar mToolbar;

    Unbinder mUiBinder;
    private TimePickerView pvTime;
    private OptionsPickerView pvAdressOptions;
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    @OnClick(R.id.activity_rang_rl)
    public void goToRange() {
        Router.actKinsRange(this);
    }

    @OnClick(R.id.rl_activity_title)
    public void editTitle() {
        Router.actKinsProfileEdit(this, KinsProfileEditActivity.Editing.TITLE, "");
    }

    @OnClick(R.id.rl_activity_content)
    public void editContent() {
        Router.actKinsProfileEdit(this, KinsProfileEditActivity.Editing.ACTIVITY_CONTENT, "");
    }

    @OnClick(R.id.rl_activity_address)
    public void editAddress() {
        Router.actKinsProfileEdit(this, KinsProfileEditActivity.Editing.ACTIVITY_ADDRESS, "");
    }

    @OnClick(R.id.rl_activity_time)
    public void timeSelect() {


        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        //startDate.set(2013,1,1);
        Calendar endDate = Calendar.getInstance();
        //endDate.set(2020,1,1);

        //正确设置方式 原因：注意事项有说明
        startDate.set(2013, 0, 1);
        endDate.set(2020, 11, 31);

        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
//                tvTime.setText(getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, true, false, false})// 默认全部显示
                .setContentTextSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText(getResources().getString(R.string.time_choose))//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setCancelText(getResources().getString(R.string.cancel))
                .setSubmitText(getResources().getString(R.string.sure))
                .setTitleColor(getResources().getColor(R.color.text_color_normal))
                .setCancelColor(getResources().getColor(R.color.text_color_normal))
                .setSubmitColor(getResources().getColor(R.color.text_color_normal))
                .setTextColorCenter(getResources().getColor(R.color.text_color_normal))
                .setDividerColor(getResources().getColor(R.color.color_ebebeb))//设置分割线的颜色
                .setTitleBgColor(Color.WHITE)//标题背景颜色
                .setBgColor(getResources().getColor(R.color.white))//滚轮背景颜色
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", null, null)//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .setOutSideCancelable(true)
                .build();
        pvTime.show();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_publish_activity);
        super.onCreate(savedInstanceState);
        mUiBinder = ButterKnife.bind(this, this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getAddressData();
//        initOptionPicker();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_publish, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.publish_menu_send) {
            Router.actActivityDetail(this);
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    private void getAddressData() {
        //选项1
        options1Items.add(new ProvinceBean(0, "广东", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(1, "湖南", "描述部分", "其他数据"));
        options1Items.add(new ProvinceBean(2, "广西", "描述部分", "其他数据"));

        //选项2
        ArrayList<String> options2Items_01 = new ArrayList<>();
        options2Items_01.add("广州");
        options2Items_01.add("佛山");
        options2Items_01.add("东莞");
        options2Items_01.add("珠海");
        ArrayList<String> options2Items_02 = new ArrayList<>();
        options2Items_02.add("长沙");
        options2Items_02.add("岳阳");
        options2Items_02.add("株洲");
        options2Items_02.add("衡阳");
        ArrayList<String> options2Items_03 = new ArrayList<>();
        options2Items_03.add("桂林");
        options2Items_03.add("玉林");
        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);
    }

    private void initOptionPicker() {//条件选择器初始化

        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */

//        pvAdressOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText()
//                        + options2Items.get(options1).get(options2)
//                        /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
////                btn_Options.setText(tx);
//            }
//        })
//                .setTitleText("城市选择")
//                .setContentTextSize(20)//设置滚轮文字大小
//                .setDividerColor(getResources().getColor(R.color.color_ebebeb))//设置分割线的颜色
//                .setSelectOptions(0, 1)//默认选中项
//                .setBgColor(Color.WHITE)
//                .setTitleBgColor(Color.WHITE)
//                .setTitleColor(getResources().getColor(R.color.text_color_normal))
//                .setCancelColor(getResources().getColor(R.color.text_color_normal))
//                .setSubmitColor(getResources().getColor(R.color.text_color_normal))
//                .setCancelText(getResources().getString(R.string.cancel))
//                .setSubmitText(getResources().getString(R.string.sure))
//                .setTextColorCenter(getResources().getColor(R.color.text_color_normal))
//                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
//                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setLabels("省", "市", "区")
//                .setBackgroundId(0x00000000) //设置外部遮罩颜色
//                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
//                    @Override
//                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
//                        String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
////                        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .build();
//
////        pvOptions.setSelectOptions(1,1);
//        /*pvOptions.setPicker(options1Items);//一级选择器*/
//        pvAdressOptions.setPicker(options1Items, options2Items);//二级选择器
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        mUiBinder.unbind();
        super.onDestroy();
    }
}
