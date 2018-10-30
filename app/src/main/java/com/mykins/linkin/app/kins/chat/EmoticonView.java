package com.mykins.linkin.app.kins.chat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mykins.linkin.R;
import com.mykins.linkin.emoticon.DefAssetsEmoticons;
import com.mykins.linkin.emoticon.EmoticonTextHelper;
import com.mykins.linkin.emoticon.EmoticonsKeyboardUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yjn on 2017/11/20.
 */

public class EmoticonView extends FrameLayout {
    private Context mContext;
    private LayoutInflater mInflater;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    GridLayoutManager mGridLayoutManager;
    private EmoticonAdapter mAdapter;
    private ArrayList<Object[]> mData;
    private OnEmoticonClicked onEmoticonClicked;

    public void setOnEmoticonClicked(OnEmoticonClicked onEmoticonClicked) {
        this.onEmoticonClicked = onEmoticonClicked;
    }

    public EmoticonView(@NonNull Context context) {
        this(context, null, 0);
    }

    public EmoticonView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmoticonView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        initView();
    }

    void initView() {
        mInflater.inflate(R.layout.include_emociton, this);
        ButterKnife.bind(this, this);
        mData = parseSystemEmojis(DefAssetsEmoticons.sEmojisMap);
        mGridLayoutManager = new GridLayoutManager(mContext, 7);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
//        LinearSnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(mRecyclerView);
        mAdapter = new EmoticonAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    private static ArrayList<Object[]> parseSystemEmojis(SparseIntArray emojisMap) {
        ArrayList<Object[]> emotionList = new ArrayList<>();
        for (int i = 0; i < emojisMap.size(); i++) {
            int unicode = emojisMap.keyAt(i);
            int resId = emojisMap.valueAt(i);
            String content = new String(Character.toChars(unicode));
            emotionList.add(new Object[]{resId, content});
        }
        return emotionList;
    }

    class EmoticonAdapter extends RecyclerView.Adapter<EmoticonAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.layout_emoticon_item, null, false);
            ViewHolder holder = new ViewHolder(view);
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onEmoticonClicked != null) {
                        String value = (String) v.getTag();
                        onEmoticonClicked.onEmoticonItemClicked(value);
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Object[] data = mData.get(position);
            String content = (String) data[1];
            int emoticonSize = EmoticonsKeyboardUtils.getFontHeight(holder.textView);
            EmoticonTextHelper.emoticonEditText(holder.textView, content, emoticonSize);
            holder.itemView.setTag(content);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.text)
            @Nullable
            TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    interface OnEmoticonClicked {
        void onEmoticonItemClicked(String value);
    }
}
