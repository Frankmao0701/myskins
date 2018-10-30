package com.mykins.linkin.app.kins.chat;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mykins.linkin.R;
import com.mykins.linkin.bean.ChatMessage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yjn on 2017/11/12.
 */

class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    Activity mActivity;
    Fragment mFragment;

    ChatAdapter(Activity activity, Fragment fragment) {
        this.mActivity = activity;
        this.mFragment = fragment;
    }

    ArrayList<ChatMessage> mData;
    long my_owner_id = 2;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        if (viewType == 0) {
            holder = new ViewHolder(mActivity.getLayoutInflater().inflate(R.layout.layout_chat_left, parent, false));
        } else if (viewType == 1) {
            holder = new ViewHolder(mActivity.getLayoutInflater().inflate(R.layout.layout_chat_right, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int type = getItemViewType(position);
        ChatMessage message = mData.get(position);
        holder.msgText.setText(message.getMsg_text());
        holder.datetime.setText(message.getDatetime());
        if (type == 0) {
            holder.name.setText(message.getName());
        } else if (type == 1) {
            //status
        }
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return mData.get(position).getOwner_id() == this.my_owner_id ? 1 : 0;
    }

    public void addData(ArrayList<ChatMessage> data) {
        mData = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chat_item_name)
        @Nullable
        TextView name;
        @BindView(R.id.chat_item_msg_text)
        @Nullable
        TextView msgText;
        @BindView(R.id.chat_item_datetime)
        @Nullable
        TextView datetime;
        @BindView(R.id.chat_item_status)
        @Nullable
        View status;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
