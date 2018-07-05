package com.mdove.passwordguard.home.todayreview.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemCustomReviewBinding;
import com.mdove.passwordguard.databinding.ItemScheduleReviewBinding;
import com.mdove.passwordguard.greendao.entity.CustomReView;
import com.mdove.passwordguard.home.todayreview.model.BaseTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.CustomReViewModel;
import com.mdove.passwordguard.home.todayreview.model.ScheduleReViewModel;
import com.mdove.passwordguard.home.todayreview.model.handler.ScheduleReViewHandler;
import com.mdove.passwordguard.home.todayreview.model.vm.CustomReViewModelVM;
import com.mdove.passwordguard.home.todayreview.model.vm.ScheduleReViewModelVM;
import com.mdove.passwordguard.home.todayreview.presenter.TodayReViewPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/7/5.
 */

public class CustomReViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CustomReViewModel> mData;
    private Context mContext;

    public CustomReViewAdapter(Context context, List<CustomReViewModel> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ScheduleReViewViewHolder((ItemCustomReviewBinding)
                InflateUtils.bindingInflate(parent, R.layout.item_custom_review));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ScheduleReViewViewHolder) {
            ((ScheduleReViewViewHolder) holder).bind(mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ScheduleReViewViewHolder extends RecyclerView.ViewHolder {
        private ItemCustomReviewBinding mBinding;

        public ScheduleReViewViewHolder(ItemCustomReviewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(CustomReViewModel model) {
            mBinding.setVm(new CustomReViewModelVM(model));
        }
    }

    public void setData(List<CustomReViewModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void insertData(CustomReViewModel model) {
        mData.add(model);
        notifyItemChanged(mData.size());
    }
}
