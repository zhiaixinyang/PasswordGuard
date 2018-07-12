package com.mdove.passwordguard.home.todayreview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemOuterAscribeBinding;
import com.mdove.passwordguard.home.todayreview.model.OuterAscribeModel;
import com.mdove.passwordguard.home.todayreview.model.vm.OuterAscribeModelVM;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/7/12.
 */

public class OuterAscribeAdapter extends RecyclerView.Adapter<OuterAscribeAdapter.ViewHolder> {
    private List<OuterAscribeModel> mData;
    private Context mContext;

    public OuterAscribeAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ItemOuterAscribeBinding) InflateUtils.bindingInflate(parent, R.layout.item_outer_ascribe));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemOuterAscribeBinding mBinding;

        public ViewHolder(ItemOuterAscribeBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(OuterAscribeModel model) {
            mBinding.setVm(new OuterAscribeModelVM(model));
        }
    }

    public void setDate(List<OuterAscribeModel> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
