package com.mdove.passwordguard.base.listlayout;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class BaseListAdapter<T> extends RecyclerView.Adapter<BaseListAdapter.BaseListViewHolder> {
    private List<T> mData;
    private Activity mActivity;

    public BaseListAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public BaseListAdapter.BaseListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseListViewHolder viewHolder = useCustomViewHolder();
        if (viewHolder == null) {
            return null;
        } else {
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(BaseListAdapter.BaseListViewHolder holder, int position) {
        holder.bind(holder.getItemView(), mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public BaseListViewHolder useCustomViewHolder() {
        return null;
    }

    public abstract class BaseListViewHolder extends RecyclerView.ViewHolder {
        private View mItemView;

        public BaseListViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
        }

        public abstract void bind(View itemView, T model);

        public View getItemView() {
            return mItemView;
        }
    }

    public void setData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
