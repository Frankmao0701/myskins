package com.mykins.linkin.app.kins;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.mykins.linkin.R;
import com.mykins.linkin.bean.Gender;
import com.mykins.linkin.util.ConvertUtils;
import com.mykins.linkin.util.ResUtils;
import com.mykins.linkin.util.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by yjn on 2017/12/16.
 */

public class KinsRelationPicker extends DialogFragment {
    Unbinder unbinder;
    Context mContext;

    @BindView(R.id.relation_dialog_test)
    View test;

    @BindView(R.id.relation_selection_bar)
    LinearLayout mRelationSelectionBar_ctl;

    @BindView(R.id.relation_dialog_relation_controls)
    LinearLayout mRelationControls_ctl;

    @BindView(R.id.relation_dialog_relation_titles)
    FrameLayout mRelationTitles_ctl;

    @BindView(R.id.relation_dialog_relation_titles_list)
    RecyclerView mRelationTitleList_ctl;

    @BindView(R.id.relation_dialog_gender)
    LinearLayout mGenderLayout_ctl;

    @BindView(R.id.relation_dialog_gender_male)
    TextView mGenderMale_ctl;

    @BindView(R.id.relation_dialog_gender_female)
    TextView mGenderFemale_ctl;

    TextView mGenderSelected_ctl;

    RelationListAdapter mAdapter;

    LinkedList<Integer> mRelationLinked = new LinkedList<>();

    String mRelationPathTitle;

    LinkedList<LinkedTreeMap<Object, Object>> mRelationRawLinked = new LinkedList<>();

    LinkedTreeMap<Object, Object> mRelationsTitleList;

    static LinkedTreeMap<Integer, String> mDefaultData;

    SparseArray<Integer> maxLevel = new SparseArray();

    static {
        if (mDefaultData == null) {
            mDefaultData = new LinkedTreeMap<>();
            mDefaultData.put(RelationDefintion.FATHER.value, ResUtils.string(R.string.title_relation_def_father));
            mDefaultData.put(RelationDefintion.MOTHER.value, ResUtils.string(R.string.title_relation_def_mother));
            mDefaultData.put(RelationDefintion.BROTHER.value, ResUtils.string(R.string.title_relation_def_brother));
            mDefaultData.put(RelationDefintion.BROTHER_ELDER.value, ResUtils.string(R.string.title_relation_def_brother_elder));
            mDefaultData.put(RelationDefintion.SISTER.value, ResUtils.string(R.string.title_relation_def_sister));
            mDefaultData.put(RelationDefintion.SISTER_ELDER.value, ResUtils.string(R.string.title_relation_def_sister_elder));
            mDefaultData.put(RelationDefintion.HUSBAND.value, ResUtils.string(R.string.title_relation_def_husband));
            mDefaultData.put(RelationDefintion.WIFE.value, ResUtils.string(R.string.title_relation_def_wife));
            mDefaultData.put(RelationDefintion.SON.value, ResUtils.string(R.string.title_relation_def_son));
            mDefaultData.put(RelationDefintion.DAUGHTER.value, ResUtils.string(R.string.title_relation_def_daughter));
        }

    }

    private int mGender = Gender.MALE.getValue();

    private KinsRelationPickListener pickListener;

    public void setPickListener(KinsRelationPickListener pickListener) {
        this.pickListener = pickListener;
    }

    public static KinsRelationPicker newInstance() {
        return new KinsRelationPicker();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        maxLevel.put(RelationDefintion.FATHER.value, 4);
        maxLevel.put(RelationDefintion.MOTHER.value, 4);
        maxLevel.put(RelationDefintion.BROTHER.value, 3);
        maxLevel.put(RelationDefintion.BROTHER_ELDER.value, 3);
        maxLevel.put(RelationDefintion.SISTER.value, 3);
        maxLevel.put(RelationDefintion.SISTER_ELDER.value, 3);
        maxLevel.put(RelationDefintion.HUSBAND.value, 3);
        maxLevel.put(RelationDefintion.WIFE.value, 3);
        maxLevel.put(RelationDefintion.SON.value, 3);
        maxLevel.put(RelationDefintion.DAUGHTER.value, 3);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        View view = inflater.inflate(R.layout.dialog_kins_relation_picker, null);
        unbinder = ButterKnife.bind(this, view);


        mRelationTitleList_ctl.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mAdapter = new RelationListAdapter();
        mRelationTitleList_ctl.setAdapter(mAdapter);
        mAdapter.setData(mRelationsTitleList);

        //add default
        addBarItem(ResUtils.string(R.string.title_gender), -1);
        showControls();

        return view;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @OnClick({R.id.relation_dialog_picker_content,
            R.id.relation_dialog_picker_frame,
            R.id.dialog_button_close,
            R.id.dialog_button_ok,
            R.id.relation_dialog_selection_button,
            R.id.relation_dialog_gender_female,
            R.id.relation_dialog_gender_male})
    void onClickOutside(View target) {
        switch (target.getId()) {
            case R.id.relation_dialog_picker_frame: {
                dismiss();
                break;
            }

            //关闭、完成
            case R.id.dialog_button_close: {
                dismiss();
                break;
            }

            case R.id.dialog_button_ok: {
                if (pickListener != null) {
                    LinkedList<Integer> values = new LinkedList<>();
                    for (Integer r : mRelationLinked) {
                        values.add(r % 2 == 0 ? r / 2 : r);
                    }
                    pickListener.onPicked(mRelationPathTitle, values);
                    dismiss();
                }
                break;
            }

            //选择性别
            case R.id.relation_dialog_gender_female: {
                mGender = Gender.FEMALE.getValue();
                mGenderFemale_ctl.setTextColor(ResUtils.color(R.color.color_4dbd3d));
                mGenderFemale_ctl.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

                mGenderMale_ctl.setTextColor(ResUtils.color(R.color.color_787878));
                mGenderMale_ctl.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

                mGenderSelected_ctl.setText(ResUtils.string(R.string.title_female));

                mRelationControls_ctl.setVisibility(View.VISIBLE);
                mRelationLinked.clear();
                resetBar();
                showControls();
                mAdapter.refreshGender();
                break;
            }
            case R.id.relation_dialog_gender_male: {
                mGender = Gender.MALE.getValue();
                mGenderFemale_ctl.setTextColor(ResUtils.color(R.color.color_787878));
                mGenderFemale_ctl.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);

                mGenderMale_ctl.setTextColor(ResUtils.color(R.color.color_4dbd3d));
                mGenderMale_ctl.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

                mGenderSelected_ctl.setText(ResUtils.string(R.string.title_male));

                mRelationControls_ctl.setVisibility(View.VISIBLE);
                mRelationLinked.clear();
                resetBar();
                showControls();
                mAdapter.refreshGender();
                break;
            }

            //显示
            case R.id.relation_dialog_selection_button: {
                mAdapter.clearSelected();
                //addBarItemSplit(ResUtils.string(R.string.title_is_my));
                showSectionList();

                mGenderLayout_ctl.setVisibility(View.GONE);
                if (mGender == Gender.MALE.getValue()) {
                    mGenderSelected_ctl.setText(ResUtils.string(R.string.title_male));
                } else if (mGender == Gender.FEMALE.getValue()) {
                    mGenderSelected_ctl.setText(ResUtils.string(R.string.title_female));
                }
                resetBar();
                break;
            }
        }
    }

    private void showSectionList() {
        mRelationTitles_ctl.setVisibility(View.VISIBLE);
        mRelationControls_ctl.setVisibility(View.GONE);
    }

    private void showControls() {
        mRelationTitles_ctl.setVisibility(View.GONE);
        mRelationControls_ctl.setVisibility(View.VISIBLE);
    }

    private void showGenderOptions() {
        mGenderLayout_ctl.setVisibility(View.VISIBLE);
        showControls();
    }

    void addBarItem(final String title, final int index) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);

        final int padding = ConvertUtils.dp2px(mContext.getResources(), 8);

        TextView label = new TextView(mContext);
        label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        label.setPadding(padding, 0, padding, 0);
        label.setText(title);

        boolean genderCtl = title.equals(ResUtils.string(R.string.title_male))
                || title.equals(ResUtils.string(R.string.title_female))
                || title.equals(ResUtils.string(R.string.title_gender));

        label.setOnClickListener(l -> {
            if (genderCtl) {
                mRelationLinked.clear();
                mAdapter.setData(new LinkedTreeMap<>());
                resetBar();
            } else {
                LinkedTreeMap<Object, Object> data = mRelationRawLinked.get(index);
                LinkedList<Integer> temp = new LinkedList<>();
                LinkedList<LinkedTreeMap<Object, Object>> temp1 = new LinkedList<>();
                for (int i = 0; i < mRelationLinked.size(); i++) {
                    int v = mRelationLinked.get(i);
                    temp.add(v);
                    temp1.add(mRelationRawLinked.get(i));
                    if (mRelationLinked.get(index) == mRelationLinked.get(i)) {
                        break;
                    }
                }
                mRelationLinked = temp;
                mRelationRawLinked = temp1;
                mAdapter.setSelected(index);
                mAdapter.setData(data);
                showControls();
                resetBar();
            }

        });
        mRelationSelectionBar_ctl.addView(label, layoutParams);

        if (genderCtl) mGenderSelected_ctl = label;
        if (mGenderSelected_ctl != null) {
            mGenderSelected_ctl.setOnClickListener(l -> {
                showGenderOptions();
                showControls();
                mRelationLinked.clear();
                mRelationRawLinked.clear();
                resetBar();
                mGenderSelected_ctl.setText(ResUtils.string(R.string.title_gender));
            });
        }
    }

    void addBarItemSplit(String title) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT);
        if (StringUtils.isNotEmpty(title)) {
            TextView spiltText = new TextView(mContext);
            spiltText.setText(title);
            spiltText.setTextColor(ResUtils.color(R.color.color_a2a2a2));
            mRelationSelectionBar_ctl.addView(spiltText, layoutParams);
        }
    }

    void resetBar() {
        mRelationSelectionBar_ctl.removeAllViews();
        addBarItem(ResUtils.string(mGender == Gender.MALE.getValue()
                ? R.string.title_male : ((mGender == Gender.FEMALE.getValue()) ? R.string.title_female : R.string.title_male)), -1);
        addBarItemSplit(ResUtils.string(R.string.title_is_my));

        if (mRelationLinked.size() == 0) {
            return;
        }
        StringBuffer relationTitles = new StringBuffer();
        for (int i = 0; i < mRelationLinked.size(); i++) {
            int v = mRelationLinked.get(i);
            boolean next = v % 2 == 0;
            v = next ? v / 2 : v;

            String title0 = "", title1 = "", title2 = "";
            if (i > 0 && !(mRelationLinked.get(i - 1) % 2 == 0)) {
                addBarItemSplit(title0 = ResUtils.string(R.string.title_de));
            }
            addBarItem(title1 = mDefaultData.get(v), i);
            if (next && i < 3) {
                addBarItemSplit(title2 = ResUtils.string(R.string.title_de));
            }

            relationTitles.append(title0).append(title1).append(title2);
        }
        mRelationPathTitle = relationTitles.toString();
    }

    public void show(FragmentManager fragmentManager, String tag, LinkedTreeMap<Object, Object> relationTitleList) {
        super.show(fragmentManager, tag);
        if (relationTitleList == null || relationTitleList.size() == 0)
            throw new IllegalArgumentException("relationTitleList == null");
        this.mRelationsTitleList = relationTitleList;
        mRelationLinked.clear();
        mRelationRawLinked.clear();
        mGender = Gender.MALE.getValue();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {
                if (!backPressed()) {
                    //do your stuff
                    super.onBackPressed();
                }
            }
        };
    }

    public boolean backPressed() {
        if (mRelationLinked.size() > 0 && mRelationRawLinked.size() > 0) {
            mRelationLinked.removeLast();
            mRelationRawLinked.removeLast();

            mAdapter.setData(mRelationRawLinked.size() > 0 ? mRelationRawLinked.getLast() : mRelationsTitleList);
            showSectionList();

            resetBar();
            mAdapter.setSelected(-1);
            return true;
        } else if (mGenderLayout_ctl.getVisibility() == View.GONE) {
            showControls();
            showGenderOptions();
            return true;
        }
        return false;
    }

    class RelationListAdapter extends RecyclerView.Adapter<RelationListAdapter.ViewHolder> {

        ArrayList<Integer> data = new ArrayList();
        LinkedTreeMap<Object, Object> rawData;
        private int mSelected;
        private boolean isChange;

        public void setData(LinkedTreeMap<Object, Object> raw) {
            this.rawData = raw;
            this.data.clear();
            for (Object key : raw.keySet()) {
                if (!"label".equals(key)) {
                    try {
                        int gender = Integer.valueOf((key.toString()).substring(1));
                        int value = Integer.valueOf(key + "");
                        if (mGender == gender) {
                            this.data.add(value);
                        }
                        try {
                            LinkedTreeMap<Object, Object> childNode = (LinkedTreeMap<Object, Object>) raw.get(key);
                            int childCount = 0;
                            for (Object key1 : childNode.keySet()) {
                                if (!"label".equals(key1)) {
                                    int keyInt = Integer.parseInt(key1.toString().substring(1));
                                    if (keyInt == mGender) {
                                        childCount++;
                                    }
                                }
                            }
                            if (childCount > 0) {
                                this.data.add(value * 2);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (NumberFormatException e) {
                        Log.e(KinsRelationPicker.RelationListAdapter.class.getName(), e.getMessage());
                    }
                }
            }
            isChange = true;
            notifyDataSetChanged();
        }

        public void refreshGender() {
            setData(this.rawData);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder holder = null;
            View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_kins_relation_list_item, parent, false);
            holder = new ViewHolder(view);
            view.setOnClickListener(view1 -> {

                int value = (int) view1.getTag();
                boolean next = value % 2 == 0;
                int realValue = next ? value / 2 : value;
                LinkedTreeMap saveState = null;
                if (!isChange || mRelationLinked.size() == 4) {
                    mRelationLinked.removeLast();
                    mRelationRawLinked.removeLast();
                }
                mRelationLinked.add(value);
                if (next) {
                    saveState = (LinkedTreeMap<Object, Object>) rawData.get(realValue + "");
                    if (saveState != null && saveState.size() > 0 && mRelationLinked.size() < 4) {
                        showControls();
                        setData(saveState);
                    }
                } else {
                    isChange = false;
                    saveState = rawData;
                }
                mRelationRawLinked.add(saveState);
                mSelected = value;
                notifyDataSetChanged();
                resetBar();

            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            int value = this.data.get(position);
            holder.selected.setVisibility(value == mSelected ? View.VISIBLE : View.GONE);
            boolean next = value % 2 == 0;
            String title = mDefaultData.get(next ? value / 2 : value);
            holder.title.setText(title + (next ? ResUtils.string(R.string.title_de) : ""));
            holder.itemView.setTag(value);
        }


        @Override
        public int getItemCount() {
            return data != null ? data.size() : 0;
        }

        public ArrayList<Integer> getData() {
            return this.data;
        }

        public LinkedTreeMap<Object, Object> getRawData() {
            return rawData;
        }

        public void clearSelected() {
            this.mSelected = -1;
        }

        public void setSelected(int mSelected) {
            this.mSelected = mSelected;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.relation_dialog_list_item_title)
            TextView title;

            @BindView(R.id.relation_dialog_list_item_selected)
            View selected;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
