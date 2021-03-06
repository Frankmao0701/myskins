package com.mykins.linkin.app.profile.gather;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mykins.linkin.R;
import com.mykins.linkin.app.Router;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<GatherBean> data;
    private Activity mContext;

    public GatherAdapter(Activity context) {
        this.mContext = context;
    }

    public void setData(List<GatherBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == GatherBean.TYPE_SHARE) {
            holder = new ActivityViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_gather_share, parent, false));
        } else {
            holder = new ShareViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_gather_active, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder instanceof ActivityViewHolder) {
                    Router.actActivityDetail(mContext);
                } else {
                    Router.actShareDetail(mContext);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (data == null || data.size() == 0) {
            return GatherBean.TYPE_ACTIVITY;
        } else {
            return data.get(position).type;
        }
    }

    class ActivityViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.gather_active_content)
        @Nullable
        TextView gather_active_content;

        public ActivityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ShareViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.gather_share_content)
        @Nullable
        View gather_share_content;

        public ShareViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
