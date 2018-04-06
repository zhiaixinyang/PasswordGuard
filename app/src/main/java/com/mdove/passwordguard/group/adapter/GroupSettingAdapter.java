package com.mdove.passwordguard.group.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemGroupSettingBinding;
import com.mdove.passwordguard.greendao.entity.GroupInfo;
import com.mdove.passwordguard.group.presenter.GroupSettingPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/2/21.
 */

public class GroupSettingAdapter extends RecyclerView.Adapter<GroupSettingAdapter.ViewHolder> {
    private List<GroupInfo> mData;
    private GroupSettingPresenter mPresenter;

    public GroupSettingAdapter(GroupSettingPresenter presenter, List<GroupInfo> data) {
        this.mData = data;
        mPresenter = presenter;
    }

    @Override
    public GroupSettingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GroupSettingAdapter.ViewHolder((ItemGroupSettingBinding) InflateUtils.bindingInflate(parent, R.layout.item_group_setting));
    }

    @Override
    public void onBindViewHolder(final GroupSettingAdapter.ViewHolder holder, final int position) {
        final GroupInfo info = mData.get(position);
        holder.bind(info, position);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemGroupSettingBinding mBinding;

        public ViewHolder(ItemGroupSettingBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(final GroupInfo info, final int position) {
            mBinding.tvGroup.setText(info.mTvGroup);
            mBinding.ivBtnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.deleteGroup(info, position);
                }
            });
        }
    }

    public void notifyDeleteGroup(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }

    public void setData(List<GroupInfo> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
