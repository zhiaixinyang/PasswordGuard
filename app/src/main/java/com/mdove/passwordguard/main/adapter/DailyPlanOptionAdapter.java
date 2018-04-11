package com.mdove.passwordguard.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemDailyPlanOptionRlvBinding;
import com.mdove.passwordguard.databinding.ItemMainOptionRlvBinding;
import com.mdove.passwordguard.main.model.DailyPlanOptionInfo;
import com.mdove.passwordguard.main.model.MainOptionInfo;
import com.mdove.passwordguard.main.model.handler.ItemDailyPlanOptionHandler;
import com.mdove.passwordguard.main.model.handler.ItemMainOptionHandler;
import com.mdove.passwordguard.main.model.vm.ItemDailyPlanOptionVM;
import com.mdove.passwordguard.main.model.vm.ItemMainOptionVM;
import com.mdove.passwordguard.main.presenter.DailyPlanOptionPresenter;
import com.mdove.passwordguard.main.presenter.MainPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/4/11.
 */

public class DailyPlanOptionAdapter extends RecyclerView.Adapter<DailyPlanOptionAdapter.ViewHolder> {
    private DailyPlanOptionPresenter mPresenter;
    private List<DailyPlanOptionInfo> mData;

    public DailyPlanOptionAdapter(List<DailyPlanOptionInfo> data) {
        mData = data;
    }

    public DailyPlanOptionAdapter(List<DailyPlanOptionInfo> data, DailyPlanOptionPresenter presenter) {
        mData = data;
        mPresenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ItemDailyPlanOptionRlvBinding) InflateUtils.bindingInflate(parent, R.layout.item_daily_plan_option_rlv));
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
        private ItemDailyPlanOptionRlvBinding mBinding;

        public ViewHolder(ItemDailyPlanOptionRlvBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(DailyPlanOptionInfo mainOptionInfo) {
            mBinding.setViewModel(new ItemDailyPlanOptionVM(mainOptionInfo));
            if (mPresenter != null) {
                mBinding.setActionHandler(new ItemDailyPlanOptionHandler(mPresenter));
            }
        }
    }

    public void setData(List<DailyPlanOptionInfo> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
