package com.mykins.linkin.app.kins.chat;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mykins.linkin.R;
import com.mykins.linkin.app.Router;
import com.mykins.linkin.app.kins.profile.KinsProfileActivity;
import com.mykins.linkin.bean.GroupContactBean;
import com.mykins.linkin.bean.KinsBean;
import com.mykins.linkin.util.GlideHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jerry on 2017/9/6.
 */

class InnerGroupContactAdapter extends RecyclerView.Adapter {


    private LayoutInflater mLayoutInflater;

    private final Activity mActivity;
    private final ArrayList<KinsBean> mData = new ArrayList<>();

    public InnerGroupContactAdapter(Activity activity) {
        this.mActivity = activity;
        mLayoutInflater = LayoutInflater.from(mActivity);
    }

    public void add(List<KinsBean> data) {
        this.mData.addAll(data);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InnerGroupContactHolder(mLayoutInflater.inflate(R.layout.layout_inner_contact_item, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

                InnerGroupContactHolder contactHolder = (InnerGroupContactHolder) holder;
                String url = "http://img.hb.aicdn.com/cd392e199f22b27f8d4acb4d4026a79eab46ceeed414-GM93zI_fw658";
                GlideHelper.loadUrlRound(mActivity, url, (contactHolder).img_contact);
    }



    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    class InnerGroupContactHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_contact)
        ImageView img_contact;
        @BindView(R.id.checkbox_contact)
        CheckBox checkbox_contact;


        public InnerGroupContactHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }




}
