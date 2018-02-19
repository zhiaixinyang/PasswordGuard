package com.mdove.passwordguard.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemMainRlvGroupBinding;
import com.mdove.passwordguard.main.model.MainGroupRlvModel;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/2/16.
 */

public class GroupRlvAdapter extends RecyclerView.Adapter<GroupRlvAdapter.ViewHolder> {
    private List<MainGroupRlvModel> mData;
    private Context mContext;

    public GroupRlvAdapter(Context context, List<MainGroupRlvModel> data) {
        this.mData = data;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ItemMainRlvGroupBinding) InflateUtils.bindingInflate(parent, R.layout.item_main_rlv_group));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bind(mData.get(position).mTvGroup);
        holder.mBinding.layoutItemGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mBinding.layoutItemGroup.setCheck(!holder.mBinding.layoutItemGroup.getCheck());
            }
        });
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

        public void bind(String title) {
            if (TextUtils.isEmpty(title)){
                return;
            }
            mBinding.layoutItemGroup.setTitle(title);
        }

    }
}
