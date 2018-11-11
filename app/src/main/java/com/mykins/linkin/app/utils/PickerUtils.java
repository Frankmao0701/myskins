package com.mykins.linkin.app.utils;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mykins.linkin.R;
import com.mykins.linkin.bean.ProvinceBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PickerUtils {
    public static void showTimePicker(Activity activity) {

        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        //startDate.set(2013,1,1);
        Calendar endDate = Calendar.getInstance();
        //endDate.set(2020,1,1);

        //正确设置方式 原因：注意事项有说明
        startDate.set(2013, 0, 1);
        endDate.set(2020, 11, 31);

        TimePickerView pvTime = new TimePickerBuilder(activity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
//                tvTime.setText(getTime(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setContentTextSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText(activity.getResources().getString(R.string.time_choose))//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setCancelText(activity.getResources().getString(R.string.cancel))
                .setSubmitText(activity.getResources().getString(R.string.sure))
                .setTitleColor(activity.getResources().getColor(R.color.text_color_normal))
                .setCancelColor(activity.getResources().getColor(R.color.text_color_normal))
                .setSubmitColor(activity.getResources().getColor(R.color.text_color_normal))
                .setTextColorCenter(activity.getResources().getColor(R.color.text_color_normal))
                .setDividerColor(activity.getResources().getColor(R.color.color_ebebeb))//设置分割线的颜色
                .setTitleBgColor(Color.WHITE)//标题背景颜色
                .setBgColor(activity.getResources().getColor(R.color.white))//滚轮背景颜色
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .setOutSideCancelable(true)
                .build();
        pvTime.show();
    }

    public static void showAddressPicker(Activity activity, List<ProvinceBean> options1Items, List<ArrayList<String>> options2Items) {
        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */

        OptionsPickerView pvAdressOptions = new OptionsPickerBuilder(activity, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(options2)
                        /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/;
//                btn_Options.setText(tx);
            }
        })
                .setTitleText("城市选择")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(activity.getResources().getColor(R.color.color_ebebeb))//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.WHITE)
                .setTitleColor(activity.getResources().getColor(R.color.text_color_normal))
                .setCancelColor(activity.getResources().getColor(R.color.text_color_normal))
                .setSubmitColor(activity.getResources().getColor(R.color.text_color_normal))
                .setCancelText(activity.getResources().getString(R.string.cancel))
                .setSubmitText(activity.getResources().getString(R.string.sure))
                .setTextColorCenter(activity.getResources().getColor(R.color.text_color_normal))
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("省", "市", "区")
                .setBackgroundId(0x00000000) //设置外部遮罩颜色
                .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                    @Override
                    public void onOptionsSelectChanged(int options1, int options2, int options3) {
                        String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;
//                        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
                    }
                })
                .build();

//        pvOptions.setSelectOptions(1,1);
        /*pvOptions.setPicker(options1Items);//一级选择器*/
        pvAdressOptions.setPicker(options1Items, options2Items);//二级选择器
        pvAdressOptions.show();
    }
}
