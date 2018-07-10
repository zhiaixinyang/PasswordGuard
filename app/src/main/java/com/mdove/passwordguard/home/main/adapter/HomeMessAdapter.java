package com.mdove.passwordguard.home.main.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemAddScheduleShortBinding;
import com.mdove.passwordguard.databinding.ItemHomeCustomReviewBinding;
import com.mdove.passwordguard.databinding.ItemHomeLongPlanBinding;
import com.mdove.passwordguard.databinding.ItemHomeScheduleBinding;
import com.mdove.passwordguard.home.main.model.BaseHomeMessModel;
import com.mdove.passwordguard.home.main.model.HomeCustomReViewModel;
import com.mdove.passwordguard.home.main.model.HomeLongPlanModel;
import com.mdove.passwordguard.home.main.model.HomeScheduleModel;
import com.mdove.passwordguard.home.main.model.vm.HomeCustomReViewModelVM;
import com.mdove.passwordguard.home.main.model.vm.HomeLongPlanModelVM;
import com.mdove.passwordguard.home.main.model.vm.HomeScheduleModelVM;
import com.mdove.passwordguard.home.schedule.EtScheduleActivity;
import com.mdove.passwordguard.utils.DensityUtil;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/7/10.
 */

public class HomeMessAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int ITEM_TYPE_ADD_SCHEDULE = 0;
    private final static int ITEM_TYPE_SCHEDULE = 1;
    private final static int ITEM_TYPE_LONG_PLAN = 2;
    private final static int ITEM_TYPE_CUSTOM_REVIEW = 3;

    private List<BaseHomeMessModel> mData;
    private Context mContext;

    public HomeMessAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        BaseHomeMessModel model = mData.get(position);
        if (model instanceof HomeScheduleModel) {
            return ITEM_TYPE_SCHEDULE;
        } else if (model instanceof HomeLongPlanModel) {
            return ITEM_TYPE_LONG_PLAN;
        } else if (model instanceof HomeCustomReViewModel) {
            return ITEM_TYPE_CUSTOM_REVIEW;
        } else {
            return ITEM_TYPE_SCHEDULE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TYPE_ADD_SCHEDULE: {
                return new AddViewHolder((ItemAddScheduleShortBinding) InflateUtils.bindingInflate(parent, R.layout.item_add_schedule_short));
            }
            case ITEM_TYPE_SCHEDULE: {
                return new ScheduleViewHolder((ItemHomeScheduleBinding) InflateUtils.bindingInflate(parent, R.layout.item_home_schedule));
            }
            case ITEM_TYPE_LONG_PLAN: {
                return new LongPlanViewHolder((ItemHomeLongPlanBinding) InflateUtils.bindingInflate(parent, R.layout.item_home_long_plan));
            }
            case ITEM_TYPE_CUSTOM_REVIEW: {
                return new CustomReViewViewHolder((ItemHomeCustomReviewBinding) InflateUtils.bindingInflate(parent, R.layout.item_home_custom_review));
            }
            default: {
                return new ScheduleViewHolder((ItemHomeScheduleBinding) InflateUtils.bindingInflate(parent, R.layout.item_home_schedule));
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
            layoutParams.setMargins(DensityUtil.dip2px(mContext, 8),
                    DensityUtil.dip2px(mContext, 1),
                    DensityUtil.dip2px(mContext, 8),
                    DensityUtil.dip2px(mContext, 8));
        }
        if (holder instanceof LongPlanViewHolder) {
            ((LongPlanViewHolder) holder).bind((HomeLongPlanModel) mData.get(position));
        } else if (holder instanceof ScheduleViewHolder) {
            ((ScheduleViewHolder) holder).bind((HomeScheduleModel) mData.get(position));
        } else if (holder instanceof CustomReViewViewHolder) {
            ((CustomReViewViewHolder) holder).bind((HomeCustomReViewModel) mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class CustomReViewViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeCustomReviewBinding mBinding;

        public CustomReViewViewHolder(ItemHomeCustomReviewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(HomeCustomReViewModel model) {
            mBinding.setVm(new HomeCustomReViewModelVM(model));
        }
    }

    public class ScheduleViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeScheduleBinding mBinding;

        public ScheduleViewHolder(ItemHomeScheduleBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(HomeScheduleModel model) {
            mBinding.setVm(new HomeScheduleModelVM(model));
        }
    }

    public class LongPlanViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeLongPlanBinding mBinding;

        public LongPlanViewHolder(ItemHomeLongPlanBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(HomeLongPlanModel model) {
            mBinding.setVm(new HomeLongPlanModelVM(model));
            if (model.mImportant >= 60) {
                mBinding.tvImportant.setTextColor(ContextCompat.getColor(mContext, R.color.red_600));
            }

            if (model.mUrgent >= 60) {
                mBinding.tvUrgent.setTextColor(ContextCompat.getColor(mContext, R.color.red_600));
            }
        }
    }


    public class AddViewHolder extends RecyclerView.ViewHolder {
        private ItemAddScheduleShortBinding mBinding;

        public AddViewHolder(ItemAddScheduleShortBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind() {
            mBinding.layoutAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EtScheduleActivity.start(mContext);
                }
            });
        }
    }

    public void setData(List<BaseHomeMessModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void notifyDelete(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
}
