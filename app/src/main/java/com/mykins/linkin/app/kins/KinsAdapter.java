package com.mykins.linkin.app.kins;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lqr.ninegridimageview.LQRNineGridImageView;
import com.lqr.ninegridimageview.LQRNineGridImageViewAdapter;
import com.mykins.linkin.R;
import com.mykins.linkin.app.Router;
import com.mykins.linkin.app.kins.chat.ChatActivity;
import com.mykins.linkin.app.kins.profile.KinsProfileActivity;
import com.mykins.linkin.bean.GroupBean;
import com.mykins.linkin.util.ConvertUtils;
import com.mykins.linkin.util.GlideHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by jerry on 2017/9/6.
 */

class KinsAdapter extends RecyclerView.Adapter {

    private static final int TYPE_INDEX = 1;
    private static final int TYPE_CONTACT = 2;
    private static final int TYPE_GROUP = 3;
    private static final int TYPE_ADD = 4;
    //    private static final int TYPE_SPILT_LINE = 5;
    private LayoutInflater mLayoutInflater;

    private final Activity mActivity;
    private final Fragment mFragment;
    private final ArrayList<String[]> mData = new ArrayList<>();
    private final ArrayList<GroupBean> mGroups = new ArrayList<>();
    private int mRadius;

    public KinsAdapter(Activity activity, Fragment fragment) {
        this.mActivity = activity;
        this.mFragment = fragment;
        mLayoutInflater = LayoutInflater.from(mActivity);
        mRadius = ConvertUtils.dp2px(mActivity.getResources(), 8);
    }

    public void add(ArrayList<String[]> data) {
        this.mData.addAll(data);
        notifyDataSetChanged();
    }

    public void addGroup(ArrayList<GroupBean> groupBeans) {
        this.mGroups.addAll(groupBeans);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
//            case TYPE_SPILT_LINE:
//                return new SpiltLineHolder(mLayoutInflater.inflate(R.layout.layout_spilt_line, parent, false));
            case TYPE_INDEX:
                return new IndexHolder(mLayoutInflater.inflate(R.layout.layout_kins_index_item, parent, false));
            case TYPE_ADD:
                return new AddHolder(mLayoutInflater.inflate(R.layout.layout_kins_add_item, parent, false));
            case TYPE_CONTACT:
                RecyclerView.ViewHolder holder = new ContactHolder(mLayoutInflater.inflate(R.layout.layout_kins_contact_item, parent, false));
                holder.itemView.setOnClickListener(v -> Router.actKinsProfile(mActivity, KinsProfileActivity.TYPE_KINS));
                return holder;
            case TYPE_GROUP:
                GroupHolder groupHolder =
                        new GroupHolder(mLayoutInflater.inflate(R.layout.layout_kins_group_item, parent, false));
                return groupHolder;

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        int dataPos = position - 1;
        switch (type) {
            case TYPE_INDEX:
                final String index = mData.get(dataPos)[0];
                ((IndexHolder) holder).kins_item_index.setText(index.toUpperCase());
                break;
            case TYPE_ADD:
                break;
            case TYPE_CONTACT:
                ContactHolder contactHolder = (ContactHolder) holder;
                int pos = dataPos - mGroups.size();
                String n = mData.get(pos)[1];
                contactHolder.kins_item_name.setText(n);
                String url = "http://img.hb.aicdn.com/cd392e199f22b27f8d4acb4d4026a79eab46ceeed414-GM93zI_fw658";
                GlideHelper.loadUrlRound(mFragment, url, ((ContactHolder) holder).kins_item_avatar);
                contactHolder.kins_item_link.setImageResource(position % 2 == 0 ? R.mipmap.ic_contact_link : R.mipmap.ic_contact_linked);
                break;
            case TYPE_GROUP:
                GroupHolder groupHolder = (GroupHolder) holder;
                String urlGroup = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1553795272322&di=6c62180c365deb3b1b920a02fd84cde6&imgtype=0&src=http%3A%2F%2Fbpic.588ku.com%2Felement_origin_min_pic%2F00%2F91%2F20%2F7656f16057ce9da.jpg";
                GlideHelper.loadUrlRound(mFragment, urlGroup, ((GroupHolder) holder).kins_item_group_avatar);
                groupHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Router.actGroupContact(mActivity);
                    }
                });
                GroupBean groupBean = mGroups.get(dataPos);
                groupHolder.kins_item_groupName.setText(groupBean.getName());
                break;
            default:
                //spilt line
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = 0;
        //添群组
        if (position == 0) {
            type = TYPE_ADD;
        } else if (position - 1 < mGroups.size()) {
            type = TYPE_GROUP;
        } else {
            int pos = position - mGroups.size() - 1;
            int len = mData.get(pos).length;
            //字母索引
            if (len == 1) {
                type = TYPE_INDEX;
            } else if (len == 2) {
                //联系人
                type = TYPE_CONTACT;
            }
        }
        Log.d("kinsAdapter", "type ->" + type);
        return type;
    }

    @Override
    public int getItemCount() {
        int countAdd = 1;
        int count = mData.size();
        int groupCount = mGroups.size();
        return countAdd + groupCount + count;
    }

    class AddHolder extends RecyclerView.ViewHolder {
        private Badge badge;

        public AddHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Router.actKinsManage(mActivity);
                }
            });
            badge = new QBadgeView(mActivity).bindTarget(itemView.findViewById(R.id.kins_item_badge_parent));
            badge.setBadgeGravity(Gravity.CENTER | Gravity.END);
            badge.setBadgeTextSize(14, true);
            badge.setBadgePadding(6, true);
            badge.setBadgeNumber(3);
        }
    }

    class GroupHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.kins_contact_item_groupName)
        TextView kins_item_groupName;
        @BindView(R.id.kins_contact_item_avatar)
        ImageView kins_item_group_avatar;

        public GroupHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ContactHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.kins_contact_item_avatar)
        ImageView kins_item_avatar;
        @BindView(R.id.kins_contact_item_name)
        TextView kins_item_name;
        @BindView(R.id.kins_contact_item_nickName)
        TextView kins_item_nikName;
        @BindView(R.id.kins_contact_item_location)
        TextView kins_item_location;
        @BindView(R.id.kins_contact_item_link)
        ImageView kins_item_link;

        public ContactHolder(View itemView) {
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

    class SpiltLineHolder extends RecyclerView.ViewHolder {

        public SpiltLineHolder(View itemView) {
            super(itemView);
        }
    }
}
