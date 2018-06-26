package com.mdove.passwordguard.home.ettodayplan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemNewHomeTimeBinding;
import com.mdove.passwordguard.singleplan.adapter.EtSinglePlanAdapter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/6/26.
 */

public class EtTodayPlanTimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> mData;

    public EtTodayPlanTimeAdapter(List<String> data) {
        mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ItemNewHomeTimeBinding) InflateUtils.bindingInflate(parent, R.layout.item_new_home_time));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemNewHomeTimeBinding mBinding;

        public ViewHolder(ItemNewHomeTimeBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(String content) {
            mBinding.tvText.setText(content);
        }
    }
}
