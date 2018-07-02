package com.mdove.passwordguard.home.todayreview.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemNewHomeAddPlanBinding;
import com.mdove.passwordguard.databinding.ItemNewHomeMainTodayReviewBinding;
import com.mdove.passwordguard.databinding.ItemNewHomeSecondTodayReviewBinding;
import com.mdove.passwordguard.databinding.ItemScheduleReviewBinding;
import com.mdove.passwordguard.home.ettodayplan.EtTodayPlanActivity;
import com.mdove.passwordguard.home.todayreview.model.BaseTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.MainTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.ScheduleReViewModel;
import com.mdove.passwordguard.home.todayreview.model.SecondTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.handler.TodayReViewHandler;
import com.mdove.passwordguard.home.todayreview.model.vm.MainTodayReViewModelVM;
import com.mdove.passwordguard.home.todayreview.model.vm.ScheduleReViewModelVM;
import com.mdove.passwordguard.home.todayreview.model.vm.SecondTodayReViewModelVM;
import com.mdove.passwordguard.home.todayreview.presenter.TodayReViewPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/7/2.
 */

public class ScheduleReViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BaseTodayReViewModel> mData;
    private Context mContext;
    private TodayReViewPresenter mPresenter;

    public ScheduleReViewAdapter(Context context, List<BaseTodayReViewModel> data, TodayReViewPresenter presenter) {
        mContext = context;
        mData = data;
        mPresenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ScheduleReViewViewHolder((ItemNewHomeMainTodayReviewBinding)
                InflateUtils.bindingInflate(parent, R.layout.item_schedule_review));
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
        private ItemScheduleReviewBinding mBinding;

        public ScheduleReViewViewHolder(ItemScheduleReviewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(BaseTodayReViewModel model) {

            if (model.mIsSuc > 0) {
                mBinding.tvContent.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                mBinding.tvContent.setTextColor(ContextCompat.getColor(mContext, R.color.gray_light));

                mBinding.layoutBtn.setBackgroundResource(R.drawable.bg_item_self_task_btn_off);
                mBinding.tvBtn.setText("取消");
            } else {
                mBinding.tvContent.getPaint().setFlags(0);
                mBinding.tvContent.getPaint().setAntiAlias(true);
                mBinding.tvContent.setTextColor(ContextCompat.getColor(mContext, R.color.black));

                mBinding.layoutBtn.setBackgroundResource(R.drawable.bg_item_self_task_btn_on);
                mBinding.tvBtn.setText("完成");
            }
            mBinding.setVm(new ScheduleReViewModelVM((ScheduleReViewModel) model));
        }
    }

    public class SecondTodayReViewViewHolder extends RecyclerView.ViewHolder {
        private ItemNewHomeSecondTodayReviewBinding mBinding;

        public SecondTodayReViewViewHolder(ItemNewHomeSecondTodayReviewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(BaseTodayReViewModel model) {
            mBinding.setHandler(new TodayReViewHandler(mPresenter));

            if (model.mIsSuc > 0) {
                mBinding.tvSinglePlan.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                mBinding.tvSinglePlan.setTextColor(ContextCompat.getColor(mContext, R.color.gray_light));

                mBinding.layoutBtn.setBackgroundResource(R.drawable.bg_item_self_task_btn_off);
                mBinding.tvBtn.setText("取消");
            } else {
                mBinding.tvSinglePlan.getPaint().setFlags(0);
                mBinding.tvSinglePlan.getPaint().setAntiAlias(true);
                mBinding.tvSinglePlan.setTextColor(ContextCompat.getColor(mContext, R.color.black));

                mBinding.layoutBtn.setBackgroundResource(R.drawable.bg_item_self_task_btn_on);
                mBinding.tvBtn.setText("完成");
            }
            mBinding.setVm(new SecondTodayReViewModelVM((SecondTodayReViewModel) model));
        }
    }

    public class AddTodayPlanViewHolder extends RecyclerView.ViewHolder {
        private ItemNewHomeAddPlanBinding mBinding;

        public AddTodayPlanViewHolder(ItemNewHomeAddPlanBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind() {
            mBinding.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EtTodayPlanActivity.start(mContext);
                }
            });
        }
    }

    public void setData(List<BaseTodayReViewModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void insertData(BaseTodayReViewModel model) {
        mData.add(model);
        notifyItemChanged(mData.size());
    }
}
