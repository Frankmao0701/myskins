package com.mykins.linkin.app.kins.profile;

import android.app.Dialog;
import android.content.Intent;
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

import com.google.gson.internal.LinkedTreeMap;
import com.mykins.linkin.R;
import com.mykins.linkin.SnackBarHelper;
import com.mykins.linkin.app.BaseFragment;
import com.mykins.linkin.app.Router;
//import com.mykins.linkin.app.kins.ItemsDialog;
import com.mykins.linkin.app.kins.ItemsDialog;
import com.mykins.linkin.app.kins.KinsRelationPicker;
import com.mykins.linkin.bean.KinsProfileBean;
import com.mykins.linkin.injection.Injectable;
import com.mykins.linkin.util.GlideHelper;
import com.mykins.linkin.util.ResUtils;
import com.mykins.linkin.util.StringUtils;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;
import static com.mykins.linkin.app.kins.profile.KinsProfileActivity.TYPE_KINS;

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
            R.id.kins_profile_item_mark
    })
    void onClick(View target) {
        int id = target.getId();
        switch (id) {
            //chat
            case R.id.kins_profile_chat: {
                Router.actChat(mActivity);
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

                break;
            }

            case R.id.kins_profile_item_address: {
                Router.actKinsProfileEdit(mActivity, KinsProfileEditActivity.Editing.ADDRESS, mKinsProfile.getAddress());
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
        }

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

        mBirthdayValue.setText(StringUtils.valueOf(data.getBirthday(), ResUtils.string(R.string.title_selection)));

        mYearsOldValue.setText(StringUtils.valueOf(data.getYearsOld()));

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