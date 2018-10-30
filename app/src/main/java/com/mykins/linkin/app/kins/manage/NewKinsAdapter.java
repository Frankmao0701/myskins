package com.mykins.linkin.app.kins.manage;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mykins.linkin.R;
import com.mykins.linkin.bean.KinsBean;
import com.mykins.linkin.util.GlideHelper;
import com.mykins.linkin.util.ResUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yjn on 2017/11/4.
 */

class NewKinsAdapter extends RecyclerView.Adapter<NewKinsAdapter.NewKinsHolder> {
    final static int VIEW_TYPE_NEWKINS = 1,
            VIEW_TYPE_NEWKINS_RECOMMEND = 2,
            VIEW_TYPE_New_KINS_TITLE = 3,
            VIEW_TYPE_RECOMMEND_KINS_TITLE = 4;
    private final Activity mActivity;
    private final Fragment mFragment;
    private LayoutInflater mInflater;

    private ArrayList<Object> mNewKins = new ArrayList<>(), mNewRecommendKins = new ArrayList<>();

    NewKinsAdapter(Activity activity, Fragment fragment) {
        this.mActivity = activity;
        this.mFragment = fragment;
        this.mInflater = this.mActivity.getLayoutInflater();
    }

    void addNewKins(ArrayList<KinsBean> data) {
        if (data != null && data.size() > 0)
            mNewKins.add(ResUtils.string(R.string.title_kins_new));
        mNewKins.addAll(data);
        notifyDataSetChanged();
    }

    void addRecommenKins(ArrayList<KinsBean> data) {
        if (data != null && data.size() > 0)
            mNewRecommendKins.add(ResUtils.string(R.string.title_kins_new_recommend));
        mNewRecommendKins.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public NewKinsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        NewKinsHolder holder = null;
        View view = null;
        if (viewType == VIEW_TYPE_NEWKINS
                || viewType == VIEW_TYPE_NEWKINS_RECOMMEND) {
            view = mInflater.inflate(R.layout.ui_new_kins_item, null);
        } else if (viewType == VIEW_TYPE_New_KINS_TITLE
                || viewType == VIEW_TYPE_RECOMMEND_KINS_TITLE) {
            view = mInflater.inflate(R.layout.include_title, null);
        }
        holder = new NewKinsHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(NewKinsHolder holder, int position) {
        int type = getItemViewType(position);
        Object data = null;
        if (type == VIEW_TYPE_NEWKINS) {
            data = mNewKins.get(position);
            KinsBean kin = (KinsBean) data;
            GlideHelper.loadUrlRound(mFragment, kin.getAvatar(), holder.avatar);
            holder.name.setText(kin.getName());
            holder.desc.setText(kin.getDescription());
            holder.buttons.setVisibility(View.VISIBLE);
            holder.more.setVisibility(View.GONE);

        } else if (type == VIEW_TYPE_NEWKINS_RECOMMEND) {
            data = mNewRecommendKins.get(position - mNewKins.size());
            KinsBean kin = (KinsBean) data;
            GlideHelper.loadUrlRound(mFragment, kin.getAvatar(), holder.avatar);
            holder.name.setText(kin.getName());
            holder.desc.setText(kin.getDescription());
            holder.buttons.setVisibility(View.GONE);
            holder.more.setVisibility(View.VISIBLE);

        } else if (type == VIEW_TYPE_RECOMMEND_KINS_TITLE) {
            String title = (String) mNewRecommendKins.get(0);
            holder.title.setText(title);

        } else if (type == VIEW_TYPE_New_KINS_TITLE) {
            data = mNewKins.get(0);
            String title = (String) data;
            holder.title.setText(title);
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = -1;
        if (position < mNewKins.size()) {
            if (position == 0)
                type = VIEW_TYPE_New_KINS_TITLE;
            else
                type = VIEW_TYPE_NEWKINS;
        } else if (mNewRecommendKins.size() > 0) {
            if (position == mNewKins.size())
                type = VIEW_TYPE_RECOMMEND_KINS_TITLE;
            else
                type = VIEW_TYPE_NEWKINS_RECOMMEND;
        }
        return type;
    }

    @Override
    public int getItemCount() {
        int size = mNewKins.size() + mNewRecommendKins.size();
        return size;
    }

    class NewKinsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.kins_new_item_avatar)
        @Nullable
        ImageView avatar;
        @BindView(R.id.kins_new_item_name)
        @Nullable
        TextView name;
        @BindView(R.id.kins_new_item_desc)
        @Nullable
        TextView desc;
        @BindView(R.id.kins_new_item_accept)
        @Nullable
        Button accept;
        @BindView(R.id.kins_new_item_reject)
        @Nullable
        Button reject;
        @BindView(R.id.kins_new_item_buttons)
        @Nullable
        LinearLayout buttons;
        @BindView(R.id.kins_new_item_more)
        @Nullable
        ImageView more;
        @BindView(R.id.include_title)
        @Nullable
        TextView title;

        public NewKinsHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
