package com.mykins.linkin.app.kins;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.mykins.linkin.R;
import com.mykins.linkin.util.ConvertUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class ItemsDialog extends DialogFragment {
    Unbinder unbinder;

    @BindView(R.id.items_dialog_list)
    RecyclerView mRecyclerView;

    OnItemClickedListener onItemClickedListener;
    private Context mContext;
    private ItemsAdapter mAdapter;
    private List<String> data;
    private String selected;
    private int mHeight;

    public void setOnItemClickedListener(OnItemClickedListener listener) {
        this.onItemClickedListener = listener;
    }

    public static ItemsDialog newInstance() {
        return new ItemsDialog();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.ui_dialog_list, container, false);
        unbinder = ButterKnife.bind(this, root);
        root.findViewById(R.id.items_dialog_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new ItemsAdapter(this.data);
        mRecyclerView.setAdapter(mAdapter);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Window window = getDialog().getWindow();
        int width = ConvertUtils.dp2px(getResources(), 300);
        window.setLayout(width, mHeight);
        window.setGravity(Gravity.CENTER);
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @OnClick({R.id.items_dialog_close, R.id.items_dialog_ok})
    void onClick(View view) {
        int id = view.getId();
        if (id == R.id.items_dialog_close) dismiss();
        if (id == R.id.items_dialog_ok && this.onItemClickedListener != null) {
            this.onItemClickedListener.onItemClicked(selected);
            dismiss();
        }
    }

    public void showDialog(AppCompatActivity activity, List<String> items, String selected) {
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
            ft.commit();
        }
        this.data = items;
        this.selected = selected;
        if (items != null && items.size() > 0) {
            Resources res = activity.getResources();
            mHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 42, res.getDisplayMetrics());
            mHeight *= items.size() + 1;
            mHeight += ConvertUtils.dp2px(res, 45);
        }
        // Create and show the dialog.
        show(ft, "dialog");
    }

    public interface OnItemClickedListener {
        void onItemClicked(String value);
    }

    class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
        List<String> data;

        public ItemsAdapter(List<String> data) {
            this.data = data;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_dialog_list_item, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final String v = this.data.get(position);
            holder.title.setText(v);
            holder.selected.setVisibility(v.equals(selected) ? View.VISIBLE : View.GONE);
            holder.itemView.setOnClickListener(view -> {
                selected = v;
                notifyDataSetChanged();
            });
        }

        @Override
        public int getItemCount() {
            return this.data != null ? this.data.size() : 0;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(com.mykins.linkin.R.id.items_dialog_item_title)
            TextView title;

            @BindView(com.mykins.linkin.R.id.items_dialog_item_selected)
            View selected;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
