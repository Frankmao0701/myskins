package com.mykins.linkin.app.kins.chat;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mykins.linkin.R;
import com.mykins.linkin.app.Router;
import com.mykins.linkin.app.kins.profile.KinsProfileActivity;
import com.mykins.linkin.bean.GroupBean;
import com.mykins.linkin.bean.GroupContactBean;
import com.mykins.linkin.util.ConvertUtils;
import com.mykins.linkin.util.GlideHelper;
import com.mykins.linkin.widget.GridDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by jerry on 2017/9/6.
 */

class GroupContactAdapter extends RecyclerView.Adapter {

    private static final int TYPE_INDEX = 1;
    private static final int TYPE_CONTACT = 0;
    //    private static final int TYPE_SPILT_LINE = 5;
    private LayoutInflater mLayoutInflater;

    private final Activity mActivity;
    private final ArrayList<GroupContactBean> mData = new ArrayList<>();

    public GroupContactAdapter(Activity activity) {
        this.mActivity = activity;
        mLayoutInflater = LayoutInflater.from(mActivity);
    }

    public void add(List<GroupContactBean> data) {
        this.mData.addAll(data);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_INDEX:
                return new IndexHolder(mLayoutInflater.inflate(R.layout.layout_kins_index_item, parent, false));
            case TYPE_CONTACT:
                GroupContactHolder holder = new GroupContactHolder(mLayoutInflater.inflate(R.layout.layout_group_contact_item, parent, false));
                holder.itemView.setOnClickListener(v -> Router.actKinsProfile(mActivity, KinsProfileActivity.TYPE_KINS));
                return holder;

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        int dataPos = position - 1;
        switch (type) {
            case TYPE_INDEX:
                final String index = mData.get(position).letter;
                ((IndexHolder) holder).kins_item_index.setText(index.toUpperCase());
                break;
            case TYPE_CONTACT:
                GroupContactHolder contactHolder = (GroupContactHolder) holder;
                GridDividerItemDecoration itemDecoration = new GridDividerItemDecoration(ConvertUtils.dp2px(mActivity, 10), Color.WHITE);
                contactHolder.recyle_inner_contact.addItemDecoration(itemDecoration);
                contactHolder.recyle_inner_contact.setLayoutManager(new GridLayoutManager(mActivity, 5));
                InnerGroupContactAdapter adapter = new InnerGroupContactAdapter(mActivity);
                contactHolder.recyle_inner_contact.setAdapter(adapter);
                adapter.add(mData.get(position).kins);
                break;

            default:
                //spilt line
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = 0;
        if (mData == null) {
            return type;
        }
        type = mData.get(position).type;
        return type;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    class GroupContactHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.recyle_inner_contact)
        RecyclerView recyle_inner_contact;


        public GroupContactHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class IndexHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.kins_item_index)
        TextView kins_item_index;

        public IndexHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
