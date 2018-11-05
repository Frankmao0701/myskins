package com.mykins.linkin.app.profile;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.mykins.linkin.R;
import com.mykins.linkin.app.BaseActivity;
import com.mykins.linkin.app.Router;
import com.mykins.linkin.app.kins.profile.KinsProfileEditActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyInfoActivity extends BaseActivity {
    @BindView(R.id.profile_user_info_toolbar)
    Toolbar mToolbar;

    Unbinder mUiBinder;
    private OptionsPickerView pvSexsOptions, pvAgeOptions;
    private List<String> sexList;
    private List<String> ageList;

    @OnClick(R.id.profile_item_sex_rl)
    public void sexChoose() {
        pvSexsOptions.show();
    }

    @OnClick(R.id.profile_item_age_rl)
    public void ageChoose() {
        pvAgeOptions.show();
    }

    @OnClick(R.id.profile_phone_rl)
    public void editPhone() {
        Router.actKinsProfileEdit(this, KinsProfileEditActivity.Editing.PHONE, "13321232131");
    }

    @OnClick(R.id.profile_email_rl)
    public void editEmail() {
        Router.actKinsProfileEdit(this, KinsProfileEditActivity.Editing.EMAIL, "www.123@163.com");
    }

    @OnClick(R.id.profile_name_rl)
    public void editName() {
        Router.actKinsProfileEdit(this, KinsProfileEditActivity.Editing.NAME, new String[]{"猪", "八戒"});
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_info);
        super.onCreate(savedInstanceState);
        mUiBinder = ButterKnife.bind(this, this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        initSexAgeData();

    }

    private void initSexAgeData() {
        sexList = new ArrayList<>();
        ageList = new ArrayList<>();
        sexList.add("男");
        sexList.add("女");
        for (int i = 0; i < 100; i++) {
            if (i > 0) {
                ageList.add(i + "岁");
            }
        }
        initSexPicker();
        initAgePicker();
    }

    private void initSexPicker() {//条件选择器初始化

        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */

        pvSexsOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText()
//                        + options2Items.get(options1).get(options2)
                /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/
                ;
//                btn_Options.setText(tx);
            }
        })
                .setTitleText(getResources().getString(R.string.sex_choose))
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(getResources().getColor(R.color.color_ebebeb))//设置分割线的颜色
                .setSelectOptions(0)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.WHITE)
                .setTitleColor(getResources().getColor(R.color.text_color_normal))
                .setCancelColor(getResources().getColor(R.color.text_color_normal))
                .setSubmitColor(getResources().getColor(R.color.text_color_normal))
                .setTextColorCenter(getResources().getColor(R.color.text_color_normal))
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setLabels("性别")
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
        pvSexsOptions.setPicker(sexList);//二级选择器
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/
    }

    private void initAgePicker() {//条件选择器初始化


        pvAgeOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText()
//                        + options2Items.get(options1).get(options2)
                /* + options3Items.get(options1).get(options2).get(options3).getPickerViewText()*/
                ;
//                btn_Options.setText(tx);
            }
        })
                .setTitleText(getResources().getString(R.string.age_choose))
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(getResources().getColor(R.color.color_ebebeb))//设置分割线的颜色
                .setSelectOptions(0)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.WHITE)
                .setTitleColor(getResources().getColor(R.color.text_color_normal))
                .setCancelColor(getResources().getColor(R.color.text_color_normal))
                .setSubmitColor(getResources().getColor(R.color.text_color_normal))
                .setTextColorCenter(getResources().getColor(R.color.text_color_normal))
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
//                .setLabels("性别")
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
        pvAgeOptions.setPicker(ageList);//二级选择器
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/
    }

    @Override
    protected void onDestroy() {
        mUiBinder.unbind();
        super.onDestroy();
    }
}
