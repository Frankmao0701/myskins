package com.mykins.linkin.app.feed.publish;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.mykins.linkin.R;
import com.mykins.linkin.app.profile.gather.GatherBean;
import com.mykins.linkin.bean.GroupBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KinsRangeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<GroupBean> data;
    private Activity mContext;
    private OnActionListener onActionListener;

    public KinsRangeAdapter(Activity context) {
        this.mContext = context;
    }

    public void setData(List<GroupBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setOnActionListener(OnActionListener onActionListener) {
        this.onActionListener = onActionListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = new KinsRangeViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_kins_range, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        KinsRangeViewHolder rangeViewHolder = (KinsRangeViewHolder) holder;
        rangeViewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (onActionListener != null) {
                        onActionListener.onCheckPosition(position);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
//        return data == null ? 0 : data.size();
        return 8;
    }


    class KinsRangeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.gather_active_content)
        @Nullable
        TextView gather_active_content;
        @BindView(R.id.checkbox)
        @Nullable
        CheckBox checkbox;

        public KinsRangeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnActionListener {
        void onCheckPosition(int position);
    }

}
