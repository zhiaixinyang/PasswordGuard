package com.mdove.passwordguard.home.ettodayplan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemNewHomeMainTodayPlanBinding;
import com.mdove.passwordguard.databinding.ItemNewHomeSecondTodayPlanBinding;
import com.mdove.passwordguard.databinding.ItemNewHomeTimeBinding;
import com.mdove.passwordguard.home.ettodayplan.model.BaseTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.MainTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.SecondTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.vm.MainTodayPlanModelVM;
import com.mdove.passwordguard.home.ettodayplan.model.vm.SecondTodayPlanModelVM;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/6/27.
 */

public class EtTodayPlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_MAIN_TODAY_PLAN = 0;
    private static final int TYPE_SECOND_TODAY_PLAN = 1;
    private List<BaseTodayPlanModel> mData;
    private Context mContext;

    public EtTodayPlanAdapter(Context context, List<BaseTodayPlanModel> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getItemViewType(int position) {
        BaseTodayPlanModel model = mData.get(position);
        if (model instanceof MainTodayPlanModel) {
            return TYPE_MAIN_TODAY_PLAN;
        } else if (model instanceof SecondTodayPlanModel) {
            return TYPE_SECOND_TODAY_PLAN;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_MAIN_TODAY_PLAN: {
                return new MainTodayPlanViewHolder((ItemNewHomeMainTodayPlanBinding)
                        InflateUtils.bindingInflate(parent, R.layout.item_new_home_main_today_plan));
            }
            case TYPE_SECOND_TODAY_PLAN: {
                return new SecondTodayPlanViewHolder((ItemNewHomeSecondTodayPlanBinding)
                        InflateUtils.bindingInflate(parent, R.layout.item_new_home_second_today_plan));
            }
            default: {
                return new MainTodayPlanViewHolder((ItemNewHomeMainTodayPlanBinding)
                        InflateUtils.bindingInflate(parent, R.layout.item_new_home_main_today_plan));
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MainTodayPlanViewHolder) {
            ((MainTodayPlanViewHolder) holder).bind(mData.get(position));
        } else if (holder instanceof SecondTodayPlanViewHolder) {
            ((SecondTodayPlanViewHolder) holder).bind(mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MainTodayPlanViewHolder extends RecyclerView.ViewHolder {
        private ItemNewHomeMainTodayPlanBinding mBinding;

        public MainTodayPlanViewHolder(ItemNewHomeMainTodayPlanBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(BaseTodayPlanModel model) {
            mBinding.setVm(new MainTodayPlanModelVM((MainTodayPlanModel) model));
        }
    }

    public class SecondTodayPlanViewHolder extends RecyclerView.ViewHolder {
        private ItemNewHomeSecondTodayPlanBinding mBinding;

        public SecondTodayPlanViewHolder(ItemNewHomeSecondTodayPlanBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(BaseTodayPlanModel model) {
            mBinding.setVm(new SecondTodayPlanModelVM((SecondTodayPlanModel) model));
        }
    }

    public void setData(List<BaseTodayPlanModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void insertData(BaseTodayPlanModel model) {
        mData.add(model);
        notifyItemChanged(mData.size());
    }
}
