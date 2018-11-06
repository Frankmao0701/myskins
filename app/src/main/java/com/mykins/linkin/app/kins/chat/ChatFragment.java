package com.mykins.linkin.app.kins.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mykins.linkin.app.BaseFragment;
import com.mykins.linkin.R;
import com.mykins.linkin.app.Router;
import com.mykins.linkin.bean.ChatMessage;
import com.mykins.linkin.emoticon.EmoticonTextHelper;
import com.mykins.linkin.emoticon.EmoticonsKeyboardUtils;
import com.mykins.linkin.util.ConvertUtils;
import com.mykins.linkin.util.KeyboardUtils;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by yjn on 2017/11/12.
 */

public class ChatFragment extends BaseFragment {
    Unbinder unbinder;

    @BindView(R.id.chat_messages)
    RecyclerView mRecyclerView;
    @BindView(R.id.chat_input_text)
    EditText mEditText;
    @BindView(R.id.chat_emoticon)
    EmoticonView mEmoticonView;

    LinearLayoutManager mLinearLayoutManager;
    ChatAdapter mAdapter;
    ArrayList<ChatMessage> mData = new ArrayList<>();
    boolean markEmoticonVisible;
    private int chatType;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ui_chat_frag, null);
        unbinder = ButterKnife.bind(this, view);
        if (getArguments()!=null){
            chatType = getArguments().getInt("type",0);
        }
        mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, true);
        mAdapter = new ChatAdapter(mActivity, this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        final int emoticonSize = EmoticonsKeyboardUtils.getFontHeight(mEditText);
        mEmoticonView.setOnEmoticonClicked(new EmoticonView.OnEmoticonClicked() {
            @Override
            public void onEmoticonItemClicked(String value) {
                String editValue = mEditText.getText().toString();
                editValue += value;
                EmoticonTextHelper.emoticonEditText(mEditText, editValue, emoticonSize);
                mEditText.setSelection(editValue.length());
            }
        });
        LinearLayout.LayoutParams emoticonLayoutParams = (LinearLayout.LayoutParams) mEmoticonView.getLayoutParams();
        emoticonLayoutParams.height = ConvertUtils.dp2px(getResources(), 260);

        KeyboardVisibilityEvent.setEventListener(
                getActivity(),
                new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        if (isOpen) {
                            mEmoticonView.setVisibility(View.GONE);
                        } else {
                            if (markEmoticonVisible) {
                                markEmoticonVisible = false;
                                mEmoticonView.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_chat, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.chat_menu_more) {
            Router.actSingleChatSetting(mActivity,chatType);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

    public boolean onBackPressed() {
        if (mEmoticonView.getVisibility() == View.VISIBLE) {
            mEmoticonView.setVisibility(View.GONE);
            return true;
        }
        return false;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @OnClick(R.id.chat_input_emoticon)
    void onBarItemEmoticonClick() {
        if (mEmoticonView.getVisibility() == View.GONE) {
            if (KeyboardVisibilityEvent.isKeyboardVisible(mActivity)) {
                KeyboardUtils.hideSoftInput(mActivity);
                markEmoticonVisible = true;
            } else {
                mEmoticonView.setVisibility(View.VISIBLE);
            }

        } else {
            mEmoticonView.setVisibility(View.GONE);
        }

    }

    public void loadData() {
        mData.add(new ChatMessage("知秋", "明天去海边玩吗", 1, "2017-11-12"));
        mData.add(new ChatMessage("迎春", "哈哈，真有此意～海边玩海边玩海边玩海边玩海边玩", 1, "2017-11-12 12:33"));
        mData.add(new ChatMessage("知秋", "明天去海边玩吗", 2, "2017-11-12"));
        mData.add(new ChatMessage("迎春", "哈哈，真有此意～海边玩海边玩海边玩海边玩海边玩", 1, "2017-11-12 12:34"));
        mData.add(new ChatMessage("迎春", "哈哈，真有此意～海边玩海边玩海边玩海边玩海边玩", 1, "2017-11-12"));
        mData.add(new ChatMessage("知秋", "明天去海边玩吗", 2, "2017-11-12"));
        mData.add(new ChatMessage("知秋", "明天去海边玩吗", 1, "2017-11-12"));
        mData.add(new ChatMessage("迎春", "哈哈，真有此意～海边玩海边玩海边玩海边玩海边玩", 1, "2017-11-12"));
        mData.add(new ChatMessage("知秋", "明天去海边玩吗", 1, "2017-11-12"));
        mData.add(new ChatMessage("迎春", "哈哈，真有此意～海边玩海边玩海边玩海边玩海边玩", 1, "2017-11-12"));
        mData.add(new ChatMessage("知秋", "明天去海边玩吗", 1, "2017-11-12"));
        mData.add(new ChatMessage("知秋", "明天去海边玩吗", 1, "2017-11-12"));
        mData.add(new ChatMessage("知秋", "明天去海边玩吗", 1, "2017-11-12"));
        mAdapter.addData(this.mData);
    }
}
