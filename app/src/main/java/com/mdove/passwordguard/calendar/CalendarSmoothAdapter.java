package com.mdove.passwordguard.calendar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemCalendarDailyPlanRlvBinding;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.main.model.vm.DailyPlanModelVM;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/4/10.
 */

public class CalendarSmoothAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<DailyPlanModel> mData;

    public CalendarSmoothAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<DailyPlanModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ItemCalendarDailyPlanRlvBinding) InflateUtils.bindingInflate(parent, R.layout.item_calendar_daily_plan_rlv));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DailyPlanModel model = mData.get(position);
        ((ViewHolder) holder).bind(model, position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemCalendarDailyPlanRlvBinding mBinding;

        public ViewHolder(ItemCalendarDailyPlanRlvBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(DailyPlanModel dailyPlanModel, int position) {
            mBinding.setViewModel(new DailyPlanModelVM(dailyPlanModel, position));
        }
    }
}
