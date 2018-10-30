package com.mykins.linkin.app.diskins;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mykins.linkin.R;
import com.mykins.linkin.bean.KinsBean;
import com.mykins.linkin.util.GlideHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yjn on 2017/11/5.
 */

class DiskinsAdapter extends RecyclerView.Adapter<DiskinsAdapter.ViewHolder> {
    Activity mActivity;
    Fragment mFragment;
    ArrayList<Object[]> mData = new ArrayList<>();

    DiskinsAdapter(Activity activity, Fragment fragment) {
        this.mActivity = activity;
        this.mFragment = fragment;
    }

    void updateData(ArrayList<Object[]> data) {
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        View view = null;
        if (viewType == 0) {
            view = mActivity.getLayoutInflater()
                    .inflate(R.layout.layout_kins_index_item, parent, false);
        } else if (viewType == 1) {
            view = mActivity.getLayoutInflater()
                    .inflate(R.layout.ui_diskins_item, parent, false);
        }
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == 0) {
            String index = (String) mData.get(position)[0];
            holder.indexTitle.setText(index.toUpperCase());
        } else if (viewType == 1) {
            KinsBean kinsBean = (KinsBean) mData.get(position)[1];
            GlideHelper.loadUrlRound(mFragment, kinsBean.getAvatar(), holder.avatar);
            holder.name.setText(kinsBean.getName());
            holder.desc.setText(kinsBean.getDescription());
            holder.kinsTitle.setText(kinsBean.getKinsTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        int len = mData.get(position).length;
        //字母索引
        if (len == 1) {
            return 0;
        } else {
            //联系人
            return 1;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.diskins_item_avatar)
        @Nullable
        ImageView avatar;
        @BindView(R.id.diskins_item_name)
        @Nullable
        TextView name;
        @BindView(R.id.diskins_kins_title)
        @Nullable
        TextView kinsTitle;
        @BindView(R.id.diskins_desc)
        @Nullable
        TextView desc;
        @BindView(R.id.kins_item_index)
        @Nullable
        TextView indexTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
