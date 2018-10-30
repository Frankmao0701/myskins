package com.mykins.linkin.app.search;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mykins.linkin.R;
import com.mykins.linkin.bean.SearchResultBean;
import com.mykins.linkin.util.GlideHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yjn on 2017/11/5.
 */

class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private final Fragment mFragment;
    Activity mActivity;
    ArrayList<Object> mData = new ArrayList<>();

    SearchAdapter(Activity activity, Fragment fragment){
        this.mActivity = activity;
        this.mFragment = fragment;
    }

    void addData(ArrayList<Object> data){
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        View view = null;
        if (viewType == -1){
            view = this.mActivity.getLayoutInflater().inflate(R.layout.include_title, null);
        }else {
            view = this.mActivity.getLayoutInflater().inflate(R.layout.layout_search_result_item, null);
        }
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        Object data = mData.get(position);
        if (viewType == -1){
            String title = (String) data;
           holder.title.setText(title);
        }else {
            SearchResultBean resultBean = (SearchResultBean) data;
            GlideHelper.loadUrlRound(mFragment, resultBean.getAvatar(), holder.avatar);
            holder.name.setText(resultBean.getName());
            holder.desc.setText(resultBean.getDesc());
            switch (viewType){
                case SearchResultBean.SEARCH_IM:
                    holder.flag.setVisibility(View.GONE);
                    break;
                case SearchResultBean.SEARCH_SHARE:
                    holder.flag.setVisibility(View.VISIBLE);
                    holder.flag.setImageResource(R.mipmap.ic_camera);
                    break;
                case SearchResultBean.SEARCH_EVENT:
                    holder.flag.setVisibility(View.VISIBLE);
                    holder.flag.setImageResource(R.mipmap.ic_event);

            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object data = mData.get(position);
        if (data instanceof SearchResultBean){
            SearchResultBean resultBean = (SearchResultBean) data;
            return resultBean.getType();
        }else { //title
            return -1;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.search_result_item_avatar) @Nullable
        ImageView avatar;
        @BindView(R.id.search_result_item_name)@Nullable
        TextView name;
        @BindView(R.id.search_result_item_desc)@Nullable
        TextView desc;
        @BindView(R.id.search_type_flag)@Nullable
        ImageView flag;
        @BindView(R.id.include_title)@Nullable
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
