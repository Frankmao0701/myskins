package com.mykins.linkin.app.feed;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mykins.linkin.R;
import com.mykins.linkin.app.Router;
import com.mykins.linkin.app.kins.chat.ChatActivity;
import com.mykins.linkin.bean.FeedBean;
import com.mykins.linkin.util.GlideHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yjn on 2017/11/5.
 */

class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    Activity mActivity;
    Fragment mFragment;
    ArrayList<FeedBean> mData = new ArrayList<>();

    FeedAdapter(Activity activity, Fragment fragment) {
        this.mActivity = activity;
        this.mFragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = this.mActivity.getLayoutInflater()
                .inflate(R.layout.layout_feed_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Router.actChat(mActivity, ChatActivity.TYPE_SINGLE);
            }
        });
        String url = "http://img.hb.aicdn.com/cd392e199f22b27f8d4acb4d4026a79eab46ceeed414-GM93zI_fw658";
//        String url = "http://img.hb.aicdn.com/1bb534a3c66ceef02f2ad0ce0368c05966437cc819d23-cRA0rz_fw658";
        GlideHelper.loadUrlRound(mFragment, url, holder.avatar);
        if (position % 2 != 0) {
            holder.typeFlag.setImageResource(R.mipmap.ic_event_flag);
        }
    }

    @Override
    public int getItemCount() {
        return 20;
//        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.feed_item_avatar)
        @Nullable
        ImageView avatar;
        @BindView(R.id.feed_item_name)
        @Nullable
        TextView name;
        @BindView(R.id.feed_type_flag)
        @Nullable
        ImageView typeFlag;
        @BindView(R.id.feed_item_content)
        @Nullable
        TextView content;
        @BindView(R.id.feed_item_event_date)
        TextView eventDate;
        @BindView(R.id.feed_item_event_time)
        TextView eventTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
