package com.mdove.passwordguard.addoralter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.addoralter.model.AddDailySelfGroupRlvModel;
import com.mdove.passwordguard.addoralter.model.AddPasswordGroupRlvModel;
import com.mdove.passwordguard.databinding.ItemMainRlvGroupBinding;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/3/21.
 */

public class AddDailySelfGroupAdapter extends RecyclerView.Adapter<AddDailySelfGroupAdapter.ViewHolder> {
    private List<AddDailySelfGroupRlvModel> mData;
    private Context mContext;

    public AddDailySelfGroupAdapter(Context context, List<AddDailySelfGroupRlvModel> data) {
        this.mData = data;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ItemMainRlvGroupBinding) InflateUtils.bindingInflate(parent, R.layout.item_main_rlv_group));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bind(mData.get(position));
        holder.mBinding.layoutItemGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onCheck(!holder.mBinding.layoutItemGroup.getCheck(), mData.get(position).mTvGroup);
                    for (AddDailySelfGroupRlvModel model : mData) {
                        model.mIsCheck = false;
                    }
                    mData.get(position).mIsCheck = !holder.mBinding.layoutItemGroup.getCheck();
                    notifyDataSetChanged();
                }
            }
        });
    }

    public void initCheck(String tvGroup) {
        if (mData == null || mData.size() == 0) {
            return;
        }
        for (AddDailySelfGroupRlvModel model : mData) {
            if (TextUtils.equals(model.mTvGroup, tvGroup)) {
                model.mIsCheck = true;
                continue;
            }
            model.mIsCheck=false;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMainRlvGroupBinding mBinding;

        public ViewHolder(ItemMainRlvGroupBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(AddDailySelfGroupRlvModel model) {
            mBinding.layoutItemGroup.setCheck(model.mIsCheck);
            mBinding.layoutItemGroup.setTitle(model.mTvGroup);
        }
    }

    public void addData(List<AddDailySelfGroupRlvModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    private OnCheckListener mListener;

    public void setOnCheckListener(OnCheckListener listener) {
        mListener = listener;
    }

    public interface OnCheckListener {
        void onCheck(boolean isCheck, String selectTitle);
    }
}
