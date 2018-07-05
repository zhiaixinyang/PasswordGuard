package com.mdove.passwordguard.home.todayreview.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemEmptyScheduleReviewBinding;
import com.mdove.passwordguard.databinding.ItemScheduleReviewBinding;
import com.mdove.passwordguard.home.todayreview.model.BaseTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.EmptyScheduleReViewModel;
import com.mdove.passwordguard.home.todayreview.model.ScheduleReViewModel;
import com.mdove.passwordguard.home.todayreview.model.handler.ScheduleReViewHandler;
import com.mdove.passwordguard.home.todayreview.model.vm.ScheduleReViewModelVM;
import com.mdove.passwordguard.home.todayreview.presenter.TodayReViewPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/7/2.
 */

public class ScheduleReViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_TYPE_EMPTY = 0;
    private static final int ITEM_TYPE_NORMAL = 1;
    private List<BaseTodayReViewModel> mData;
    private Context mContext;
    private TodayReViewPresenter mPresenter;

    public ScheduleReViewAdapter(Context context, List<BaseTodayReViewModel> data, TodayReViewPresenter presenter) {
        mContext = context;
        mData = data;
        mPresenter = presenter;
    }

    @Override
    public int getItemViewType(int position) {
        BaseTodayReViewModel model = mData.get(position);
        if (model instanceof EmptyScheduleReViewModel) {
            return ITEM_TYPE_EMPTY;
        } else {
            return ITEM_TYPE_NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TYPE_NORMAL: {
                return new ScheduleReViewViewHolder((ItemScheduleReviewBinding)
                        InflateUtils.bindingInflate(parent, R.layout.item_schedule_review));
            }
            case ITEM_TYPE_EMPTY: {
                return new EmptyViewHolder((ItemEmptyScheduleReviewBinding)
                        InflateUtils.bindingInflate(parent, R.layout.item_empty_schedule_review));
            }
            default: {
                return new ScheduleReViewViewHolder((ItemScheduleReviewBinding)
                        InflateUtils.bindingInflate(parent, R.layout.item_schedule_review));
            }
        }

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
            mBinding.setVm(new ScheduleReViewModelVM((ScheduleReViewModel) model));
            mBinding.setHandler(new ScheduleReViewHandler(mPresenter));

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


    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        private ItemEmptyScheduleReviewBinding mBinding;

        public EmptyViewHolder(ItemEmptyScheduleReviewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(){}
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
