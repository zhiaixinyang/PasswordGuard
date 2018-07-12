package com.mdove.passwordguard.home.todayreview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemInnerAscribeBinding;
import com.mdove.passwordguard.home.todayreview.model.InnerAscribeModel;
import com.mdove.passwordguard.home.todayreview.model.vm.InnerAscribeModelVM;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/7/12.
 */

public class InnerAscribeAdapter extends RecyclerView.Adapter<InnerAscribeAdapter.ViewHolder> {
    private List<InnerAscribeModel> mData;
    private Context mContext;

    public InnerAscribeAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ItemInnerAscribeBinding) InflateUtils.bindingInflate(parent, R.layout.item_inner_ascribe));
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
        private ItemInnerAscribeBinding mBinding;

        public ViewHolder(ItemInnerAscribeBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(InnerAscribeModel model) {
            mBinding.setVm(new InnerAscribeModelVM(model));
        }
    }

    public void setData(List<InnerAscribeModel> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
