package com.mykins.linkin.app.kins.profile;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.internal.LinkedTreeMap;
import com.mykins.linkin.R;
import com.mykins.linkin.SnackBarHelper;
import com.mykins.linkin.app.BaseFragment;
import com.mykins.linkin.app.Router;
import com.mykins.linkin.app.kins.ItemsDialog;
import com.mykins.linkin.app.kins.KinsRelationPicker;
import com.mykins.linkin.app.kins.chat.ChatActivity;
import com.mykins.linkin.app.utils.PickerUtils;
import com.mykins.linkin.bean.KinsProfileBean;
import com.mykins.linkin.bean.ProvinceBean;
import com.mykins.linkin.injection.Injectable;
import com.mykins.linkin.util.GlideHelper;
import com.mykins.linkin.util.ResUtils;
import com.mykins.linkin.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;
import static com.mykins.linkin.app.kins.profile.KinsProfileActivity.TYPE_KINS;

//import com.mykins.linkin.app.kins.ItemsDialog;

/**
 * 亲资料
 * Created by yjn on 2017/12/19.
 */


public class KinsProfileFragment extends BaseFragment implements Injectable, KinsProfileContract.View {

    public static KinsProfileFragment newInstance(int type) {
        KinsProfileFragment profileFragment = new KinsProfileFragment();
        Bundle args = new Bundle();
        args.putInt("data", type);
        profileFragment.setArguments(args);
        return profileFragment;
    }

    Unbinder unbinder;

    KinsProfileBean mKinsProfile = new KinsProfileBean();

    private View mRoot;

    @BindView(R.id.kins_profile_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.kins_profile_title)
    TextView mTitle;

    @BindView(R.id.kins_profile_bg)
    ImageView mHeadBackground;

    @BindView(R.id.kins_profile_chat)
    ImageView mChatButton;

    @BindView(R.id.kins_profile_avatar)
    ImageView mAvatar;

    @BindView(R.id.kins_profile_item_name_value)
    TextView mNameValue;

    @BindView(R.id.kins_profile_item_relations_value)
    TextView mRelationValue;

    @BindView(R.id.kins_profile_item_arrow_arrow8_icon)
    ImageView mRelationArrowIcon;

    @BindView(R.id.kins_profile_item_arrow8_progressbar)
    ProgressBar mRelationLoadProgressbar;

    @BindView(R.id.kins_profile_item_appellation)
    RelativeLayout mAppellationLayout;

    @BindView(R.id.kins_profile_item_appellation_value)
    TextView mAppellationValue;

    @BindView(R.id.kins_profile_item_arrow_arrow2_icon)
    ImageView mAppellationArrowIcon;

    @BindView(R.id.kins_profile_item_arrow2_progressbar)
    ProgressBar mAppellationProgressbar;

    @BindView(R.id.kins_profile_item_zodiac_value)
    TextView mZodiacValue;

    @BindView(R.id.kins_profile_item_address_value)
    TextView mAddressValue;

    @BindView(R.id.kins_profile_item_phone_number)
    RelativeLayout mPhone;

    @BindView(R.id.kins_profile_item_phone_number_value)
    TextView mPhoneValue;

    @BindView(R.id.kins_profile_item_birthday)
    RelativeLayout mBirthday;

    @BindView(R.id.kins_profile_item_birthday_value)
    TextView mBirthdayValue;

    @BindView(R.id.kins_profile_item_years)
    RelativeLayout mYearsOld;

    @BindView(R.id.kins_profile_item_years_value)
    TextView mYearsOldValue;

    @BindView(R.id.kins_profile_item_email)
    RelativeLayout mEmail;

    @BindView(R.id.kins_profile_item_email_value)
    TextView mEmailValue;

    @BindView(R.id.kins_profile_item_mark_value)
    TextView mMarkValue;

    int type = 0;
    private KinsRelationPicker mRelationPickerDialog;

    //选择称谓
    private ItemsDialog mAppellationDialog;
    //关系定位值
    private LinkedList<Integer> mRelations;


    @Inject
    KinsProfileContract.Presenter mPresenter;

    private OptionsPickerView pvZodiacOptions;
    private List<String> zodiacList;
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            type = savedInstanceState.getInt("data");
        } else {
            type = getArguments().getInt("data");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.ui_kins_profile_frag, null);
        mRoot = root;

        unbinder = ButterKnife.bind(this, root);

        String url = "http://img.hb.aicdn.com/cd392e199f22b27f8d4acb4d4026a79eab46ceeed414-GM93zI_fw658";
        GlideHelper.loadUrlRound(this, url, mAvatar);

        mActivity.setSupportActionBar(mToolbar);
        mActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        getAddressData();
        mHeadBackground.setImageResource(type == TYPE_KINS ? R.mipmap.ic_thumbail2 : R.mipmap.ic_thumbail);
        mTitle.setText(ResUtils.string(type == TYPE_KINS ? R.string.title_kins_profile : R.string.title_dis_kins_profile));
        mChatButton.setVisibility(type == TYPE_KINS ? View.VISIBLE : View.GONE);
        mPhone.setVisibility(type == TYPE_KINS ? View.VISIBLE : View.GONE);
        mEmail.setVisibility(type == TYPE_KINS ? View.VISIBLE : View.GONE);
        mBirthday.setVisibility(type == TYPE_KINS ? View.GONE : View.VISIBLE);
        mYearsOld.setVisibility(type == TYPE_KINS ? View.GONE : View.VISIBLE);

        showData(mKinsProfile);

        return root;
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

    @Override
    public void onPause() {
        if (mRelationPickerDialog != null) {
            Dialog dialog = mRelationPickerDialog.getDialog();
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
        super.onPause();
    }

    /*boolean backPressed(){
       if (mRelationPickerDialog.isAdded()){
           return mRelationPickerDialog.backPressed();
       }
       return false;
    }*/

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Serializable val = data.getExtras().getSerializable("data");
            switch (requestCode) {
                case KinsProfileEditActivity.Editing.NAME: {
                    String[] pairsVal = (String[]) val;
                    mKinsProfile.setFirstName(pairsVal[0]);
                    mKinsProfile.setSecondName(pairsVal[1]);
                    break;
                }
                case KinsProfileEditActivity.Editing.ADDRESS: {
                    mKinsProfile.setAddress((String) val);
                    break;
                }
                case KinsProfileEditActivity.Editing.PHONE: {
                    mKinsProfile.setPhone((String) val);
                    break;
                }
                case KinsProfileEditActivity.Editing.EMAIL: {
                    mKinsProfile.setEmail((String) val);
                    break;
                }
                case KinsProfileEditActivity.Editing.MARK: {
                    mKinsProfile.setMark((String) val);
                    break;
                }
            }
            showData(mKinsProfile);
        }
    }

    @OnClick({
            R.id.kins_profile_chat,
            R.id.kins_profile_item_relations,
            R.id.kins_profile_item_name,
            R.id.kins_profile_item_appellation,
            R.id.kins_profile_item_zodiac,
            R.id.kins_profile_item_address,
            R.id.kins_profile_item_phone_number,
            R.id.kins_profile_item_email,
            R.id.kins_profile_item_mark,
            R.id.kins_profile_item_birthday,
            R.id.kins_profile_item_years
    })
    void onClick(View target) {
        int id = target.getId();
        switch (id) {
            //chat
            case R.id.kins_profile_chat: {
                Router.actChat(mActivity, ChatActivity.TYPE_SINGLE);
                break;
            }

            //edit name
            case R.id.kins_profile_item_name: {
                String firstName = mKinsProfile.getFirstName(), secondName = mKinsProfile.getSecondName();

                String[] name = null;
                if (!StringUtils.isEmpty(firstName) && !StringUtils.isEmpty(secondName)) {
                    name = new String[]{firstName, secondName};
                }
                Router.actKinsProfileEdit(mActivity, KinsProfileEditActivity.Editing.NAME, name);
                break;
            }
            case R.id.kins_profile_item_relations: {
                mPresenter.localizationRelation();
                break;
            }
            //appellation
            case R.id.kins_profile_item_appellation: {
                mPresenter.selectAppellation(this.mRelations);
                break;
            }

            case R.id.kins_profile_item_zodiac: {
                showZodiac();
                break;
            }

            case R.id.kins_profile_item_address: {
                PickerUtils.showAddressPicker(getActivity(), options1Items, options2Items);
//                Router.actKinsProfileEdit(mActivity, KinsProfileEditActivity.Editing.ADDRESS, mKinsProfile.getAddress());
                break;
            }

            case R.id.kins_profile_item_phone_number: {
                Router.actKinsProfileEdit(mActivity, KinsProfileEditActivity.Editing.PHONE, mKinsProfile.getPhone());
                break;
            }

            case R.id.kins_profile_item_email: {
                Router.actKinsProfileEdit(mActivity, KinsProfileEditActivity.Editing.EMAIL, mKinsProfile.getEmail());
                break;
            }
            case R.id.kins_profile_item_mark: {
                Router.actKinsProfileEdit(mActivity, KinsProfileEditActivity.Editing.MARK, mKinsProfile.getMark());
                break;
            }
            case R.id.kins_profile_item_birthday: {
                PickerUtils.showTimePicker(mActivity);
                break;
            }
            case R.id.kins_profile_item_years: {
                PickerUtils.showTimePicker(mActivity);
                break;
            }
        }

    }


    private void showZodiac() {
        PickerUtils.showTimePicker(mActivity);
//        zodiacList = new ArrayList<>();
//        zodiacList.add("鼠");
//        zodiacList.add("牛");
//        zodiacList.add("虎");
//        zodiacList.add("兔");
//        zodiacList.add("龙");
//        zodiacList.add("蛇");
//        zodiacList.add("马");
//        zodiacList.add("羊");
//        zodiacList.add("猴");
//        zodiacList.add("鸡");
//        zodiacList.add("狗");
//        zodiacList.add("猪");
//        pvZodiacOptions = new OptionsPickerBuilder(mActivity, new OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//
//            }
//        })
//                .setTitleText(getResources().getString(R.string.zodiac_choose))
//                .setContentTextSize(20)//设置滚轮文字大小
//                .setDividerColor(getResources().getColor(R.color.color_ebebeb))//设置分割线的颜色
//                .setSelectOptions(0)//默认选中项
//                .setBgColor(Color.WHITE)
//                .setTitleBgColor(Color.WHITE)
//                .setTitleColor(getResources().getColor(R.color.text_color_normal))
//                .setCancelColor(getResources().getColor(R.color.text_color_normal))
//                .setSubmitColor(getResources().getColor(R.color.text_color_normal))
//                .setTextColorCenter(getResources().getColor(R.color.text_color_normal))
//                .setCancelText(getResources().getString(R.string.cancel))
//                .setSubmitText(getResources().getString(R.string.sure))
//                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
//                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
////                .setLabels("性别")
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
//        pvZodiacOptions.setPicker(zodiacList);//二级选择器
//        pvZodiacOptions.show();
        /*pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器*/


    }

    void showData(KinsProfileBean data) {
        boolean is = type == TYPE_KINS;
        String firstName = mKinsProfile.getFirstName();
        String secondName = mKinsProfile.getSecondName();
        mNameValue.setText(StringUtils.valueOf(firstName == null ? null : (firstName + " " + secondName)
                , ResUtils.string(is ? R.string.hint_kins_name : R.string.hint_dis_kins_name)));

        mRelationValue.setText(StringUtils.valueOf(data.getRelations(), ResUtils.string(R.string.title_selection)));

        mAppellationValue.setText(StringUtils.valueOf(data.getAppellation(), ResUtils.string(R.string.title_selection)));

        mZodiacValue.setText(StringUtils.valueOf(data.getZodiac(), ResUtils.string(R.string.title_selection)));

        mAddressValue.setText(StringUtils.valueOf(data.getAddress()
                , ResUtils.string(is ? R.string.hint_address : R.string.hint_dis_kins_address)));
        mPhoneValue.setText(StringUtils.valueOf(data.getPhone(), ResUtils.string(R.string.hint_phone)));

        mEmailValue.setText(StringUtils.valueOf(data.getEmail(), ResUtils.string(R.string.hint_email)));

        mBirthdayValue.setText(StringUtils.valueOf(data.getBirthday(), ResUtils.string(R.string.text_select_birthday)));

        mYearsOldValue.setText(StringUtils.valueOf(data.getYearsOld(), ResUtils.string(R.string.text_select_die_day)));

        mMarkValue.setText(StringUtils.valueOf(data.getMark(), ResUtils.string(R.string.hint_mark)));
    }

    @Override
    public void startRelationProgress() {
        mRelationArrowIcon.setVisibility(View.GONE);
        mRelationLoadProgressbar.setVisibility(View.VISIBLE);
    }

    public void endRelationProgress() {
        mRelationArrowIcon.setVisibility(View.VISIBLE);
        mRelationLoadProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void startAppellationProgress() {
        mAppellationArrowIcon.setVisibility(View.GONE);
        mAppellationProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endAppellationProgress() {
        mAppellationArrowIcon.setVisibility(View.VISIBLE);
        mAppellationProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void showRelationPicker(LinkedTreeMap<Object, Object> relationTitleList) {
        if (mRelationPickerDialog == null) {
            mRelationPickerDialog = KinsRelationPicker.newInstance();
            mRelationPickerDialog.setPickListener((relationPathTitle, relations) -> {
                Log.d("relationPathTitle", relationPathTitle);
                for (Integer r : relations) {
                    Log.d("value", r + "");
                }
                mRelationValue.setText(relationPathTitle);
                mRelations = relations;
                mAppellationLayout.setVisibility(View.VISIBLE);
                mAppellationValue.setText(ResUtils.string(R.string.title_selection));

            });
        }
        mRelationPickerDialog.show(mActivity.getFragmentManager(), KinsProfileEditActivity.class.getName(), relationTitleList);
    }

    @Override
    public void showAppellationDialog(List<String> appellations) {
        if (mAppellationDialog == null) {
            mAppellationDialog = ItemsDialog.newInstance();
            mAppellationDialog.setOnItemClickedListener(value -> mAppellationValue.setText(value));
        }
        mAppellationDialog.showDialog(mActivity, appellations, this.mAppellationValue.getText().toString());
    }

    @Override
    public void showError(String error) {
        SnackBarHelper.showError(mRoot, error, SnackBarHelper.ERROR_DURATION);
    }
}
